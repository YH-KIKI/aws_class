import { useNavigate } from "react-router-dom";
import { authFetch } from "../../api/authFetch";
import { useAuth } from "../../AuthContext";

function MyPage(){

  const {setUser} = useAuth();
  const navigate = useNavigate();

  const logout = async () => {

    await authFetch("/api/v1/auth/logout", {method : "POST"});
    setUser(null); //회원정보 null로 변경
    localStorage.removeItem("accessToken"); //토큰 제거(서버에서)
    navigate("/")
  }
  return(
    <div>
      <h1>마이 페이지</h1>
      <button onClick={logout}>로그아웃</button>
    </div>
  )

}

export default MyPage;