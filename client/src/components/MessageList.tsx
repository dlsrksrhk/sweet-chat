import { useEffect, useRef } from "react";
import type { ChatMessage } from "../types/chat-message";
import type { SessionUserInfo } from "../types/user";
import Message from "./Message";

function MessageList({
  chatRoomId,
  messages,
  userInfo,
}: {
  chatRoomId: number;
  messages: ChatMessage[];
  userInfo: SessionUserInfo;
}) {
  const scrollTopRef = useRef<HTMLDivElement | null>(null);
  const scrollBottomRef = useRef<HTMLDivElement | null>(null);

  const isScrollAtBottom = () => {
    const el = scrollTopRef.current;
    if (!el) return false;
    return el.scrollTop + el.clientHeight >= el.scrollHeight * 0.9;
  };

  useEffect(() => {
    if (isScrollAtBottom()) {
      scrollBottomRef.current?.scrollIntoView({ behavior: "smooth" });
    }
  }, [messages]);

  useEffect(() => {
    console.log("채팅방이 변경되었습니다:", chatRoomId, messages.length);
    setTimeout(() => {
      scrollTopRef.current?.scrollTo({
        top: scrollTopRef.current.scrollHeight,
        behavior: "smooth",
      });
    }, 500);
  }, [chatRoomId]);

  return (
    <div
      className="w-full overflow-y-scroll flex-1 flex flex-col p-4 gap-3"
      ref={scrollTopRef}
    >
      {messages?.map((message, index) => (
        <Message
          key={message.id}
          message={message.content}
          type={message.sender === userInfo.username ? "send" : "receive"}
        />
      ))}
      <div ref={scrollBottomRef} />
    </div>
  );
}

export default MessageList;
