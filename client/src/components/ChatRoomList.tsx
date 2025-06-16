import { useEffect, useState } from "react";
import { getChatRoomList } from "../hooks/api";
import ChatCreateButton from "./ChatCreateButton";
import ChatRoom from "./ChatRoom";
import type { ChatRoom as ChatRoomInfo } from "../types/chat-room";

function ChatRoomList() {
  const [chatRooms, setChatRooms] = useState<ChatRoomInfo[]>([]);

  useEffect(() => {
    getChatRoomList().then((rooms) => {
      console.log("ChatRoomList render", rooms);
      setChatRooms(rooms);
    });
  }, []);

  return (
    <div className="h-screen min-w-60 flex flex-col bg-gray-50">
      <ChatRoom roomName="한경선" lastMessageTime="3d ago" />
      {chatRooms.map((room) => (
        <ChatRoom
          key={room.id}
          roomName={room.name}
          lastMessageTime={room.createdAt}
        />
      ))}
      <ChatCreateButton setChatRooms={setChatRooms} />
    </div>
  );
}

export default ChatRoomList;
