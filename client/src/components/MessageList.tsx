import type { ChatMessage } from "../types/chat-message";
import Message from "./Message";

function MessageList({ messages }: { messages: ChatMessage[] }) {
  return (
    <div className="w-full overflow-y-scroll flex-1 flex flex-col p-4 gap-3">
      {messages?.map((message, index) => (
        <Message
          key={index}
          message={message.content}
          type={message.sender === "testUser" ? "send" : "receive"}
        />
      ))}
      {/* 예시 메시지 */}
    </div>
  );
}

export default MessageList;
