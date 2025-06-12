import Side from "./Side";

function MainLayout({ children }: { children: React.ReactNode }) {
  return (
    <main className="w-full h-screen flex items-center justify-center">
      <Side />
      {children}
    </main>
  );
}

export default MainLayout;
