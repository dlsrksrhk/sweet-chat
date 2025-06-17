import { useEffect, useRef, useState } from "react";
import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";

const ChatTest = () => {
  const roomId = 1; // 기본 채팅방 ID 설정
  const jwtToken = sessionStorage.getItem("jwt");
  const [messages, setMessages] = useState<string[]>(["하위"]);
  const clientRef = useRef<Client | null>(null);

  useEffect(() => {
    const socket = new SockJS(
      `http://localhost:8080/ws/chat?token=${jwtToken}`
    );

    const stompClient = new Client({
      webSocketFactory: () => socket,
      connectHeaders: {
        Authorization: "Bearer " + jwtToken,
      },
      debug: (str) => console.log("[STOMP DEBUG]:", str),
      reconnectDelay: 5000,
      onConnect: () => {
        console.log("✅ STOMP connected");

        // 채팅방 구독
        stompClient.subscribe(`/topic/chatroom.${roomId}`, (message) => {
          const payload = JSON.parse(message.body);
          setMessages((prev) => [...prev, payload.content]);
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
  }, [roomId, jwtToken]);

  const sendMessage = (content: string) => {
    const payload = {
      content: content,
    };

    clientRef.current?.publish({
      destination: `/app/chat/${roomId}`,
      body: JSON.stringify(payload),
      headers: {
        Authorization: `Bearer ${jwtToken}`,
      },
    });
  };

  return (
    <div>
      <button onClick={() => sendMessage("안녕하세요!")}>메시지 보내기</button>
      <ul>
        {messages.map((msg, i) => (
          <li key={i}>{msg}</li>
        ))}
      </ul>
    </div>
  );
};

export default ChatTest;
