import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Navbar from './component/nav/Navbar';
import Main from './component/body/Main';
import List2 from './component/body/List2'
import Insert2 from './component/body/Insert2';



function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" exact element={<Main/>} />
        <Route path="/todo/list" element={<List2/>} />
        <Route path="/todo/insert" element={<Insert2/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
