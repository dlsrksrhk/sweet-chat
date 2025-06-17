import { useState } from "react";
import ChatRoom from "../components/ChatRoom";
import ChatRoomList from "../components/ChatRoomList";
import MainLayout from "../components/MainLayout";
import type { ChatRoom as ChatRoomInfo } from "../types/chat-room";

function Chat() {
  const [selectedRoom, setSelectedRoom] = useState<ChatRoomInfo | null>(null);

  return (
    <MainLayout>
      <div className="w-full h-screen flex justify-center items-center">
        <ChatRoomList setSelectedRoom={setSelectedRoom} />
        <ChatRoom chatRoom={selectedRoom} />
      </div>
    </MainLayout>
  );
}

export default Chat;
