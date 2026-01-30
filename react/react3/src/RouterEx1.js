import {BrowserRouter, Link, Route, Routes} from 'react-router-dom';
import Main from './router1/Main';
import Posts from './router1/Posts';
import PostInsert from './router1/PostInsert';
import PostDetail from './router1/PostDetail';

function RouterEx1(){
  return(
    <BrowserRouter>
      <ul>
        <li><Link to={"/"}>메인</Link></li>
        <li><Link to={"/posts"}>게시글 목록</Link></li>
        <li><Link to={"/post/insert"}>게시글 등록</Link></li> 
      </ul>
      <Routes>
        <Route path="/" exact element={<Main/>} />
        <Route path="/posts" element={<Posts/>} />
        <Route path="/post/insert" element={<PostInsert/>} />
        <Route path="/post/detail/:num" element={<PostDetail/>} />
      </Routes>
    </BrowserRouter>
  )
}

export default RouterEx1;