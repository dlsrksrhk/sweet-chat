import { useEffect, useRef, useState } from "react";
import ChatInput from "./ChatInput";
import ChatRoomHeader from "./ChatRoomHeader";
import MessageList from "./MessageList";
import { getChatMessages, getSessionUserInfo } from "../hooks/api";
import type { ChatMessage } from "../types/chat-message";
import type { ChatRoom } from "../types/chat-room";
import type { SessionUserInfo } from "../types/user";
import { useStompClient } from "../hooks/useStompClient";
import type { Client } from "@stomp/stompjs";

function ChatRoom({ chatRoom }: { chatRoom: ChatRoom }) {
  const [messages, setMessages] = useState<ChatMessage[]>([]);
  const [userInfo, setUserInfo] = useState<SessionUserInfo>({
    id: 0,
    username: "",
  });
  const clientRef = useRef<Client | null>(null);
  const chatRoomId = chatRoom.id;
  const jwtToken = sessionStorage.getItem("jwt");

  useEffect(() => {
    if (!chatRoomId) {
      console.warn("No chat room selected");
      return;
    }

    //이전 채팅 메시지 목록 가져오기
    getChatMessages(chatRoomId).then((messages) => {
      setMessages(messages);
    });

    //세션유저
    getSessionUserInfo().then((info) => {
      setUserInfo(info);
    });
  }, [chatRoom]);

  useStompClient({
    clientRef: clientRef,
    roomId: chatRoomId,
    jwtToken: jwtToken,
    onRecievedMessage: (message) => {
      console.log("수신받은 메시지~~~", message);
      setMessages((prev) => [...prev, message]);
    },
  });

  const onSubmitMessage = (message: string) => {
    clientRef.current?.publish({
      destination: `/app/chat/${chatRoomId}`,
      body: JSON.stringify({ content: message }),
      headers: {
        Authorization: `Bearer ${jwtToken}`,
      },
    });
  };

  return (
    <div className="w-full h-screen flex flex-col">
      {/* Active 유저 영역 */}
      <ChatRoomHeader chatRoom={chatRoom} />

      {/* 채팅 영역 */}
      <MessageList messages={messages} userInfo={userInfo} />

      {/* 채팅창 영역 */}
      <ChatInput onSubmitMessage={onSubmitMessage} />
    </div>
  );
}

export default ChatRoom;
