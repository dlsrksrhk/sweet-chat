import { useNavigate } from "react-router-dom";
import MainLayout from "../components/MainLayout";
import { useEffect } from "react";

function Home() {
  const navigate = useNavigate();

  useEffect(() => {
    const token = sessionStorage.getItem("jwt");
    if (!token) {
      navigate("/login");
    }
  }, []);

  return (
    <MainLayout>
      <main className="w-full h-screen flex flex-col gap-2 items-center justify-center">
        <h1 className="font-bold text-xl">Welcome</h1>
      </main>
    </MainLayout>
  );
}

export default Home;
