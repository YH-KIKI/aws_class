import { Link } from "react-router-dom";

function List(){

  return(

    <div>
      <h1>기능들 모음</h1>
      <ul>
        <li>
          <Link to = {"/ask"}>기본 ai 테스트</Link>
        </li>
        <li>
          <Link to = {"/translate"}>번역</Link>
        </li>
        <li>
          <Link to = {"/ad-copy"}>광고</Link>
          {/* 컴포넌트명 AdCopy */}
        </li>
      </ul>
    </div>
  );


}


export default List;