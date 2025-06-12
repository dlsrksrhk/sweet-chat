import { Logout, Send } from "@mui/icons-material";
import HomeIcon from "@mui/icons-material/Home";
import { Link } from "react-router-dom";

function Side() {
  return (
    <aside className="w-fit h-screen p-6 border-r border-gray-300 flex flex-col justify-between">
      {/* Home버튼 + People Page ~ Chat Page */}
      <div className="flex flex-col gap-4">
        <Link to="/home">
          <HomeIcon className="text-2xl mb-6" />
        </Link>
        <Link to="/chat">
          <Send className="text-2xl" />
        </Link>
      </div>

      {/* Logout Button */}
      <div>
        <button onClick={() => console.log("logout")}>
          <Logout className="text-2xl text-deep-purple-900" />
        </button>
      </div>
    </aside>
  );
}

export default Side;
