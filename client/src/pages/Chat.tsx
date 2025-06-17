import { useState } from "react";
import ChatRoomList from "../components/ChatRoomList";
import MainLayout from "../components/MainLayout";
import type { ChatRoom as ChatRoomInfo } from "../types/chat-room";
import ChatRoom from "../components/ChatRoom";

function Chat() {
  const [selectedRoom, setSelectedRoom] = useState<ChatRoomInfo | null>(null);

  return (
    <MainLayout>
      <div className="w-full h-screen flex justify-center items-center">
        <ChatRoomList setSelectedRoom={setSelectedRoom} />
        {selectedRoom ? (
          <ChatRoom chatRoom={selectedRoom} />
        ) : (
          <div className="w-full h-screen flex justify-center items-center">
            <p className="text-gray-500">채팅방을 생성하거나 선택하세요.</p>
          </div>
        )}
      </div>
    </MainLayout>
  );
}

export default Chat;
