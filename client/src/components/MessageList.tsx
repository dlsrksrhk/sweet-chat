import type { ChatMessage } from "../types/chat-message";
import type { SessionUserInfo } from "../types/user";
import Message from "./Message";

function MessageList({
  messages,
  userInfo,
}: {
  messages: ChatMessage[];
  userInfo: SessionUserInfo;
}) {
  return (
    <div className="w-full overflow-y-scroll flex-1 flex flex-col p-4 gap-3">
      {messages?.map((message, index) => (
        <Message
          key={index}
          message={message.content}
          type={message.sender === userInfo.username ? "send" : "receive"}
        />
      ))}
    </div>
  );
}

export default MessageList;
