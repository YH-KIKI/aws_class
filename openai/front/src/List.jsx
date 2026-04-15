import { Link } from "react-router-dom";

function List(){

  return(

    <div>
      <h1>기능들 모음</h1>
      <ul>
        <li>
          <Link to = {"/ask"}>기본 ai 테스트</Link>
        </li>
      </ul>
    </div>
  );


}


export default List;