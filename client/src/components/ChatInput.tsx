function ChatInput() {
  return (
    <div className="flex">
      <input
        className="p-3 w-full border-2 border-light-blue-600 outline-0"
        placeholder="메시지를 입력하세요."
      />

      <button
        className="min-w-20 p-3 bg-blue-600 text-white"
        color="light-blue"
      >
        <span>전송</span>
      </button>
    </div>
  );
}

export default ChatInput;
