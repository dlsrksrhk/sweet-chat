import { useEffect } from "react";
import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";
import type { ChatMessage } from "../types/chat-message";

type UseStompClientParams = {
  clientRef: React.MutableRefObject<Client | null>;
  roomId: number;
  jwtToken: string | null;
  onRecievedMessage: (message: ChatMessage) => void;
};

export const useStompClient = ({
  clientRef,
  roomId,
  jwtToken,
  onRecievedMessage,
}: UseStompClientParams) => {
  useEffect(() => {
    if (!jwtToken) return;

    const socket = new SockJS(
      `http://localhost:8080/ws/chat?token=${jwtToken}`
    );

    const stompClient = new Client({
      webSocketFactory: () => socket,
      connectHeaders: {
        Authorization: `Bearer ${jwtToken}`,
      },
      debug: (str) => console.log("[STOMP DEBUG]:", str),
      reconnectDelay: 5000,
      onConnect: () => {
        console.log("✅ STOMP connected");

        stompClient.subscribe(`/topic/chatroom.${roomId}`, (message) => {
          const payload = JSON.parse(message.body);
          const recievedMessage: ChatMessage = {
            roomId: payload.roomId,
            sender: payload.sender,
            content: payload.content,
            timestamp: payload.timestamp,
          };

          onRecievedMessage(recievedMessage);
        });
      },
      onStompError: (frame) => {
        console.error("STOMP error", frame.headers, frame.body);
      },
    });

    stompClient.activate();
    clientRef.current = stompClient;

    return () => {
      stompClient.deactivate();
    };
  }, [roomId]);
};
