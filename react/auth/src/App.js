import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Navbar } from "./component/Navbar";
import { Login } from "./pages/Login";
import { Main } from "./pages/Main";
import { MyPage } from "./pages/MyPage";
import { Signup } from "./pages/Signup";

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" exact element={<Main/>} />
        <Route path="/login" element={<Login/>} />
        <Route path="/signup" element={<Signup/>} />
        <Route path="/mypage" element={<MyPage/>} />
      </Routes>
    </BrowserRouter>
  );
}



export default App;
