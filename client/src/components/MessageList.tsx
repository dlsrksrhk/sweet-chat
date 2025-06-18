import { useEffect, useRef } from "react";
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
  const messageListRef = useRef<HTMLDivElement | null>(null);
  const messageEndRef = useRef<HTMLDivElement | null>(null);

  const isLookingLastMessage = () => {
    const el = messageListRef.current;
    if (!el) return false;
    return el.scrollTop + el.clientHeight >= el.scrollHeight * 0.9;
  };

  useEffect(() => {
    if (isLookingLastMessage()) {
      messageEndRef.current?.scrollIntoView({ behavior: "smooth" });
    }
  }, [messages]);

  return (
    <div
      className="w-full overflow-y-scroll flex-1 flex flex-col p-4 gap-3"
      ref={messageListRef}
    >
      {messages?.map((message, index) => (
        <Message
          key={index}
          message={message.content}
          type={message.sender === userInfo.username ? "send" : "receive"}
        />
      ))}
      <div ref={messageEndRef} />
    </div>
  );
}

export default MessageList;
