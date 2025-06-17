import { useRef } from "react";

function ChatInput({
  onSubmitMessage,
}: {
  onSubmitMessage: (message: string) => void;
}) {
  const messageInputRef = useRef<HTMLInputElement>(null);

  const handleInputEnter = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === "Enter") {
      e.preventDefault();
      const message = messageInputRef.current?.value.trim();
      if (message) {
        onSubmitMessage(message);
        messageInputRef.current!.value = "";
      }
    }
  };

  const handleClickSubmit = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    const message = messageInputRef.current?.value.trim();
    if (message) {
      onSubmitMessage(message);
      messageInputRef.current!.value = "";
    }
  };

  return (
    <div className="flex">
      <input
        ref={messageInputRef}
        className="p-3 w-full border-2 border-light-blue-600 outline-0"
        placeholder="메시지를 입력하세요."
        onKeyDown={handleInputEnter}
      />

      <button
        className="min-w-20 p-3 bg-blue-600 text-white"
        color="light-blue"
        onClick={handleClickSubmit}
      >
        <span>전송</span>
      </button>
    </div>
  );
}

export default ChatInput;
