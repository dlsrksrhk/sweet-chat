import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

type LoginRequest = {
  username: string;
  password: string;
};

function Login() {
  const navigate = useNavigate();

  const [request, setRequest] = useState<LoginRequest>({
    username: "",
    password: "",
  });

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setRequest({ ...request, [event.target.name]: event.target.value });
  };

  const handleLogin = () => {
    axios
      .post(import.meta.env.VITE_API_BASE_URL + "/login", request, {
        headers: { "Content-Type": "application/json" },
      })
      .then((res) => {
        console.log("로그인 성공", res);
        const jwtToken = res.data.token;
        if (jwtToken !== null) {
          sessionStorage.setItem("jwt", jwtToken);
          navigate(`/home`);
        }
      });
  };

  return (
    <main className="h-screen w-screen flex flex-col justify-center items-center bg-gradient-to-br from-purple-50 to-light-blue-50">
      <div className="flex flex-col gap-4 w-100">
        <div className="pt-10 pb-6 px-10 w-full flex flex-col items-center justify-center max-w-lg border border-gray-400 bg-white gap-2">
          <h1 className="text-5xl mb-10 text-green-400">Sweet Chat</h1>
          <label>
            계정 :
            <input
              type="text"
              name="username"
              className="w-50 rounded-md p-1 ml-2 outline-0 border-gray-400 border-1"
              onChange={handleChange}
            />
          </label>
          <label>
            비밀번호 :
            <input
              type="password"
              name="password"
              className="w-50 rounded-md p-1 ml-2 outline-0 border-gray-400 border-1"
              onChange={handleChange}
            />
          </label>
          <button
            className="w-45 bg-blue-500 text-white rounded-md p-2 hover:bg-blue-600 mt-5 cursor-pointer"
            onClick={handleLogin}
          >
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
