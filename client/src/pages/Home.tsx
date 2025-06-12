import { Logout, Send } from "@mui/icons-material";
import HomeIcon from "@mui/icons-material/Home";
import MainLayout from "../components/MainLayout";

function Home() {
  return (
    <MainLayout>
      <main className="w-full h-screen flex flex-col gap-2 items-center justify-center">
        <h1 className="font-bold text-xl">Welcome</h1>
      </main>
    </MainLayout>
  );
}

export default Home;
