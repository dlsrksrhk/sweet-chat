function Login() {
  return (
    <main className="h-screen w-screen flex flex-col justify-center items-center bg-gradient-to-br from-purple-50 to-light-blue-50">
      <div className="flex flex-col gap-4 w-100">
        <div className="pt-10 pb-6 px-10 w-full flex flex-col items-center justify-center max-w-lg border border-gray-400 bg-white gap-2">
          <h1 className="text-5xl mb-10 text-green-400">Sweet Chat</h1>
          <label>
            계정 :
            <input className="w-50 rounded-md p-1 ml-2 outline-0 border-gray-400 border-1" />
          </label>
          <label>
            비밀번호 :
            <input className="w-50 rounded-md p-1 ml-2 outline-0 border-gray-400 border-1" />
          </label>
          <button className="w-45 bg-blue-500 text-white rounded-md p-2 hover:bg-blue-600 mt-5 cursor-pointer">
            로그인
          </button>
        </div>
      </div>

      <div className="flex justify-center items-center w-100 h-10 text-center border border-gray-400 bg-white mt-5">
        계정이 없으신가요?
        <button className="text-blue-600 pl-1 cursor-pointer">
          회원가입하기
        </button>
      </div>
    </main>
  );
}

export default Login;
