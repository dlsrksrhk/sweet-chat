import { createChatRoom } from "../hooks/api";
import type { ChatRoom } from "../types/chat-room";

function ChatCreateButton({
  setChatRooms,
}: {
  setChatRooms: (value: React.SetStateAction<ChatRoom[]>) => void;
}) {
  const handleClick = async () => {
    const room: ChatRoom = await createChatRoom("새로운 채팅방");
    console.log("ChatRoom", room);
    setChatRooms((prevRooms) => [...prevRooms, room]);
  };

  return (
    <div className="mt-auto mb-0.5 text-center p-0.5">
      <button
        className="w-full p-3 bg-blue-500 rounded-md cursor-pointer text-white"
        onClick={handleClick}
      >
        채팅방 생성
      </button>
    </div>
  );
}

export default ChatCreateButton;
