import { useRef, useState } from "react";
import { createChatRoom } from "../hooks/api";
import type { ChatRoom } from "../types/chat-room";
import Modal from "./Modal";

function ChatCreateButton({
  setChatRooms,
}: {
  setChatRooms: (value: React.SetStateAction<ChatRoom[]>) => void;
}) {
  const roomNameRef = useRef<HTMLInputElement>(null);
  const [isOpen, setIsOpen] = useState(false);
  const handleOpenCreateRoomModal = () => {
    setIsOpen(true);
  };

  const handleCreateRoom = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();
    const roomName = roomNameRef.current?.value.trim();
    if (!roomName) {
      alert("방제목을 입력해주세요.");
      return;
    }

    createChatRoom(roomName).then((room) => {
      setChatRooms((prevRooms) => [...prevRooms, room]);
      setIsOpen(false);
    });
  };

  return (
    <>
      <div className="mt-auto mb-0.5 text-center p-0.5">
        <button
          className="w-full p-3 bg-blue-500 rounded-md cursor-pointer text-white"
          onClick={handleOpenCreateRoomModal}
        >
          채팅방 생성
        </button>
      </div>

      <Modal isOpen={isOpen} onClose={() => setIsOpen(false)}>
        <h2 className="text-xl font-bold mb-4">채팅방 생성</h2>
        <p className="text-gray-700">
          <label>
            방제목 :
            <input
              ref={roomNameRef}
              type="text"
              className="w-50 rounded-md p-1 ml-2 outline-0 border-gray-400 border-1"
            />
          </label>
        </p>
        <div className="flex justify-center mt-4">
          <button
            className="w-18 p-2 bg-blue-500 rounded-md cursor-pointer text-white"
            onClick={handleCreateRoom}
          >
            생성
          </button>
          <button
            className="w-18 p-2 bg-red-400 rounded-md cursor-pointer text-white ml-10"
            onClick={() => setIsOpen(false)}
          >
            취소
          </button>
        </div>
      </Modal>
    </>
  );
}

export default ChatCreateButton;
