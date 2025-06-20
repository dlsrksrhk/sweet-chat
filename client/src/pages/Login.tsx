import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

type LoginRequest = {
  userLoginId: string;
  password: string;
};

function Login() {
  const navigate = useNavigate();

  useEffect(() => {
    const token = sessionStorage.getItem("jwt");
    if (token) {
      navigate("/"); // JWT 있으면 홈으로 리디렉트
    }
  }, []);

  const [request, setRequest] = useState<LoginRequest>({
    userLoginId: "",
    password: "",
  });

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setRequest({ ...request, [event.target.name]: event.target.value });
  };

  const handleLogin = () => {
    axios
      .post(import.meta.env.VITE_API_BASE_URL + "/api/login", request, {
        headers: { "Content-Type": "application/json" },
      })
      .then((res) => {
        console.log("로그인 성공", res);
        const jwtToken = res.data.token;
        if (jwtToken !== null) {
          sessionStorage.setItem("jwt", jwtToken);
          navigate(`/`);
        }
      });
  };

  return (
    <main className="h-screen w-screen flex flex-col justify-center items-center bg-gradient-to-br from-purple-50 to-light-blue-50">
      <div className="flex flex-col gap-4 w-100">
        <div className="pt-10 pb-6 px-10 w-full flex flex-col items-center justify-center max-w-lg border border-gray-400 bg-white gap-2">
          <h1 className="text-5xl mb-10">
            <span className="text-transparent bg-clip-text bg-gradient-to-r from-purple-400 to-blue-400">
              Sweet Chat
            </span>
          </h1>
          <div className="flex items-center w-full">
            <label className="w-17">
              계&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;정 :
            </label>
            <input
              type="text"
              name="userLoginId"
              className="w-50 rounded-md p-1 ml-2 outline-0 border-gray-400 border-1"
              onChange={handleChange}
            />
          </div>
          <div className="flex items-center w-full">
            <label className="w-17">비밀번호 :</label>
            <input
              type="password"
              name="password"
              className="w-50 rounded-md p-1 ml-2 outline-0 border-gray-400 border-1"
              onChange={handleChange}
            />
          </div>
          <button
            className="w-45 bg-gradient-to-r from-purple-400 to-blue-400 text-white rounded-md p-2 
            hover:from-purple-500 hover:to-blue-500 mt-5 cursor-pointer"
            onClick={handleLogin}
          >
            로그인
          </button>
        </div>
      </div>

      <div className="flex justify-center items-center w-100 h-10 text-center border border-gray-400 bg-white mt-5">
        계정이 없으신가요?
        <button
          className="text-blue-600 pl-1 cursor-pointer"
          onClick={() => navigate("/signup")}
        >
          회원가입하기
        </button>
      </div>
    </main>
  );
}

export default Login;
