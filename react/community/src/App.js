import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from './component/body/Login';
import Main from './component/body/Main';
import Signup from './component/body/Signup';
import Navbar from './component/nav/Navbar';
import MyPage from './component/body/MyPage';



function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" exact element={<Main/>} />
        <Route path="/signup" element={<Signup/>} />
        <Route path="/login" element={<Login/>} />
        <Route path="/mypage" element={<MyPage/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
