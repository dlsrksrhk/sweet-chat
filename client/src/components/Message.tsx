function Message({
  message,
  type,
}: {
  message: string;
  type: "send" | "receive";
}) {
  return (
    <div
      className={`w-fit p-3 rounded-md ${
        type === "send"
          ? "ml-auto bg-blue-600 text-white"
          : "bg-gray-100 text-black"
      } `}
    >
      <p>{message}</p>
    </div>
  );
}

export default Message;
