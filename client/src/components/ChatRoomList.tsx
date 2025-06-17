import { useEffect, useState } from "react";
import { getChatRoomList } from "../hooks/api";
import ChatCreateButton from "./ChatCreateButton";
import ChatRoomItem from "./ChatRoomItem";
import type { ChatRoom } from "../types/chat-room";

function ChatRoomList({
  setSelectedRoom,
}: {
  setSelectedRoom: (room: ChatRoom) => void;
}) {
  const [chatRooms, setChatRooms] = useState<ChatRoom[]>([]);

  useEffect(() => {
    getChatRoomList().then((rooms) => {
      setChatRooms(rooms);
    });
  }, []);

  const handleRoomItemClick = (room: ChatRoom) => {
    setSelectedRoom(room);
  };

  return (
    <div className="h-screen min-w-60 flex flex-col bg-gray-50">
      {chatRooms.map((room) => (
        <ChatRoomItem
          key={room.id}
          chatRoom={room}
          onClickCallback={handleRoomItemClick}
        />
      ))}
      <ChatCreateButton setChatRooms={setChatRooms} />
    </div>
  );
}

export default ChatRoomList;
