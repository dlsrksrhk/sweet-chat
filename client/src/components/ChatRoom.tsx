import { useEffect, useState } from "react";
import ChatInput from "./ChatInput";
import ChatRoomHeader from "./ChatRoomHeader";
import MessageList from "./MessageList";
import { getChatMessages } from "../hooks/api";
import type { ChatMessage } from "../types/chat-message";
import type { ChatRoom } from "../types/chat-room";

function ChatRoom({ chatRoom }: { chatRoom: ChatRoom }) {
  const [messages, setMessages] = useState<ChatMessage[]>([]);
  const chatRoomId = chatRoom?.id;

  useEffect(() => {
    if (!chatRoomId) {
      console.warn("No chat room selected");
      return;
    }

    // 예: 채팅 메시지 로드, WebSocket 연결 등
    getChatMessages(chatRoomId).then((messages) => {
      setMessages(messages);
    });
  }, [chatRoomId]);

  if (!chatRoom) {
    return (
      <div className="w-full h-screen flex justify-center items-center">
        <p className="text-gray-500">채팅방을 생성하거나 선택하세요.</p>
      </div>
    );
  }

  return (
    <div className="w-full h-screen flex flex-col">
      {/* Active 유저 영역 */}
      <ChatRoomHeader chatRoom={chatRoom} />

      {/* 채팅 영역 */}
      <MessageList messages={messages} />

      {/* 채팅창 영역 */}
      <ChatInput />
    </div>
  );
}

export default ChatRoom;
