import { Logout, Send } from "@mui/icons-material";
import HomeIcon from "@mui/icons-material/Home";
import { Link, useNavigate } from "react-router-dom";

function Side() {
  const navigate = useNavigate();

  return (
    <aside className="w-fit h-screen p-6 border-r border-gray-300 flex flex-col justify-between">
      {/* Home버튼 + People Page ~ Chat Page */}
      <div className="flex flex-col gap-4">
        <Link to="/">
          <HomeIcon className="text-2xl mb-6" />
        </Link>
        <Link to="/chat">
          <Send className="text-2xl" />
        </Link>
      </div>

      {/* Logout Button */}
      <div>
        <button
          onClick={() => {
            sessionStorage.removeItem("jwt");
            navigate("/");
          }}
        >
          <Logout className="text-2xl text-deep-purple-900 cursor-pointer" />
        </button>
      </div>
    </aside>
  );
}

export default Side;
