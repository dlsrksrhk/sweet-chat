import type { ChatRoom } from "../types/chat-room";
import { formatDistanceToNow } from "date-fns";
import { ko } from "date-fns/locale";

function ChatRoomItem({
  chatRoom,
  onClickCallback,
}: {
  chatRoom: ChatRoom;
  onClickCallback: (chatRoom: ChatRoom) => void;
}) {
  const lastMessageTime = !chatRoom.lastMessageInfo
    ? "채팅 없음"
    : formatDistanceToNow(new Date(chatRoom.lastMessageInfo.timestamp), {
        addSuffix: true,
        locale: ko,
      });

  return (
    <div
      onClick={() => onClickCallback(chatRoom)}
      className={`flex w-full min-w-60 cursor-pointer gap-4 items-center p-4 bg-light-blue-50bg-gray-50`}
    >
      <img
        src={"/src/assets/emotion1.png"}
        className="w-10 h-10 rounded-full"
      />
      <div>
        <p className="text-black font-bold text-lg">{chatRoom.name}</p>
        <p className="text-gray-500 text-sm">{lastMessageTime}</p>
      </div>
    </div>
  );
}

export default ChatRoomItem;
