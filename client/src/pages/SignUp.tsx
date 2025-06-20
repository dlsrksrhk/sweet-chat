import { useState } from "react";
import { useNavigate } from "react-router-dom";
import type { UserSignUpRequest } from "../types/user";
import { userSingUp } from "../hooks/api";
import ProfileImageUploader from "../components/ProfileImageUploader";

function SignUp() {
  const navigate = useNavigate();

  const [form, setForm] = useState<UserSignUpRequest>({
    loginId: "",
    userName: "",
    password: "",
    email: "",
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSignUp = () => {
    //검증
    if (
      !form.loginId.trim() ||
      !form.userName.trim() ||
      !form.password.trim() ||
      !form.email.trim()
    ) {
      alert("모든 필드를 입력해주세요.");
      return;
    }

    //전송
    userSingUp(form)
      .then((res) => {
        console.log("회원가입 성공", res);
        navigate("/login");
      })
      .catch((error) => {
        console.error("회원가입 실패", error);
        alert("회원가입에 실패했습니다. 다시 시도해주세요.");
      });
  };

  return (
    <main className="h-screen w-screen flex flex-col justify-center items-center bg-gradient-to-br from-purple-50 to-light-blue-50">
      <div className="flex flex-col gap-4 w-100">
        <div className="pt-10 pb-6 px-10 w-full flex flex-col items-center justify-center max-w-lg border border-gray-400 bg-white gap-2">
          <h1 className="text-5xl mb-2 text-transparent bg-clip-text bg-gradient-to-r from-purple-400 to-blue-400">
            Sweet Chat
          </h1>
          <h2 className="text-lg mb-5 text-transparent bg-clip-text bg-gradient-to-r from-orange-400 to-purple-400 text-center">
            친구들과 함께 채팅하고 새 친구도 사귀고 싶다면 가입하세요
          </h2>
          <div className="flex items-center w-full">
            <label className="w-17">
              계&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;정 :
            </label>
            <input
              type="text"
              name="loginId"
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
          <div className="flex items-center w-full">
            <label className="w-17">
              이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름 :
            </label>
            <input
              type="text"
              name="userName"
              className="w-50 rounded-md p-1 ml-2 outline-0 border-gray-400 border-1"
              onChange={handleChange}
            />
          </div>
          <div className="flex items-center w-full">
            <label className="w-17">이 메 일&nbsp;&nbsp;:</label>
            <input
              type="email"
              name="email"
              className="w-50 rounded-md p-1 ml-2 outline-0 border-gray-400 border-1"
              onChange={handleChange}
            />
          </div>
          <button
            className="w-45 bg-gradient-to-r from-purple-400 to-blue-400 text-white rounded-md p-2 
              hover:from-purple-500 hover:to-blue-500 mt-5 cursor-pointer"
            onClick={handleSignUp}
          >
            회원가입
          </button>
        </div>
      </div>

      <div className="flex justify-center items-center w-100 h-10 text-center border border-gray-400 bg-white mt-5">
        이미 계정이 있어요.
        <button
          className="text-blue-600 pl-1 cursor-pointer"
          onClick={() => navigate("/login")}
        >
          로그인하기
        </button>
      </div>
    </main>
  );
}

export default SignUp;
