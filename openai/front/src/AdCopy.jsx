import { Link } from "react-router-dom";


function AdCopy(){

  return(
    <div>
      <Link to={"/list"}>뒤로가기</Link>
      <h1>광고 문구 제작</h1>
    </div>

  )

}

export default AdCopy;