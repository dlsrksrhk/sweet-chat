import type { ChatRoom } from "../types/chat-room";

function ChatRoomHeader({ chatRoom }: { chatRoom: ChatRoom }) {
  return (
    <div
      className={`flex w-full min-w-60 cursor-pointer gap-4 items-center p-4 bg-light-blue-50bg-gray-50`}
    >
      <img
        src={"/src/assets/emotion1.png"}
        className="w-10 h-10 rounded-full"
      />
      <div>
        <p className="text-black font-bold text-lg">{chatRoom.name}</p>
      </div>
    </div>
  );
}

export default ChatRoomHeader;
