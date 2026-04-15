import { Link } from "react-router-dom";


function AdCopy(){

  return(
    <div>
      <h1>광고</h1>
      <Link to={"/list"}>뒤로가기</Link>
    </div>

  )

}

export default AdCopy;