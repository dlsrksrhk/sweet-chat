import MainLayout from "../components/MainLayout";

function Chat() {
  return (
    <MainLayout>
      <div className="w-full h-screen flex justify-center items-center">
        {/* ChatPeopleList start */}
        <div className="h-screen min-w-60 flex flex-col bg-gray-50">
          {/* Person start */}
          <div
            className={`flex w-full min-w-60 cursor-pointer gap-4 items-center p-4 bg-light-blue-50bg-gray-50`}
          >
            <img
              src={"/src/assets/emotion1.png"}
              className="w-10 h-10 rounded-full"
            />
            <div>
              <p className="text-black font-bold text-lg">한경선</p>
              <p className="text-gray-500 text-sm">3d ago</p>
            </div>
          </div>
          {/* Person end */}
        </div>
        {/* ChatPeopleList end */}

        {/* ChatScreen start */}
        <div className="w-full h-screen flex flex-col">
          {/* Active 유저 영역 */}
          {/* Person start */}
          <div
            className={`flex w-full min-w-60 cursor-pointer gap-4 items-center p-4 bg-light-blue-50bg-gray-50`}
          >
            <img
              src={"/src/assets/emotion1.png"}
              className="w-10 h-10 rounded-full"
            />
            <div>
              <p className="text-black font-bold text-lg">한경선</p>
              <p className="text-gray-500 text-sm">3d ago</p>
            </div>
          </div>
          {/* Person end */}

          {/* 채팅 영역 */}
          <div className="w-full overflow-y-scroll flex-1 flex flex-col p-4 gap-3">
            {/* Message1 */}
            <div
              className={`w-fit p-3 rounded-md ml-auto bg-blue-600 text-white`}
            >
              <p>하위!</p>
            </div>

            {/* Message2 */}
            <div className={`w-fit p-3 rounded-md bg-gray-100 text-black`}>
              <p>어키 하위~~</p>
            </div>
          </div>

          {/* 채팅창 영역 */}
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
        </div>
        {/* ChatScreen end */}
      </div>
    </MainLayout>
  );
}

export default Chat;
