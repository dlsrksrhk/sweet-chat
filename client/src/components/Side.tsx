import { Logout, Send } from "@mui/icons-material";
import HomeIcon from "@mui/icons-material/Home";
import { Avatar } from "@mui/material";
import { Link, useNavigate } from "react-router-dom";

function Side() {
  const navigate = useNavigate();

  return (
    <aside className="w-fit h-screen p-4 border-r border-gray-300 flex flex-col justify-between">
      <div className="flex flex-col gap-4">
        <Link
          to="/profile"
          className="w-10 h-10 mb-2 rounded-full overflow-hidden flex items-center justify-center cursor-pointer border-blue-100 border-1"
        >
          <Avatar
            src={"src/assets/emotion1.png"}
            alt="프로필 이미지"
            sx={{
              width: 30,
              height: 30,
            }}
          />
        </Link>

        <Link to="/" className="flex items-center justify-center">
          <HomeIcon className="mb-5" sx={{ fontSize: 30 }} />
        </Link>

        <Link to="/chat" className="flex items-center justify-center">
          <Send sx={{ fontSize: 30 }} />
        </Link>
      </div>

      {/* Logout Button */}
      <div
        onClick={() => {
          sessionStorage.removeItem("jwt");
          navigate("/login"); // 로그인 페이지로 리디렉션
        }}
      >
        <button>
          <Logout
            className="text-deep-purple-900 cursor-pointer"
            sx={{ fontSize: 30 }}
          />
        </button>
      </div>
    </aside>
  );
}

export default Side;
