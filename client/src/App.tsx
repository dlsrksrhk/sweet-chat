import { Route, Routes } from "react-router-dom";
import "./App.css";
import Login from "./pages/Login";
import Home from "./pages/Home";
import Chat from "./pages/Chat";
import ChatTest from "./pages/ChatTest";

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/chat" element={<Chat />} />
        {/* <Route path="/chat" element={<ChatTest />} /> */}
        {/* <Route path="*" element={<Notfound />} /> */}
      </Routes>
    </>
  );
}

export default App;
