import { Link } from "react-router-dom";
import { useAuth } from "../../AuthContext";
import "./Navbar.css";


const NAV_ITEMS = [
  {label : '메인', path : '/', auth : 'any'},
  {label : '회원가입', path : '/signup', auth : 'guest'},
  {label : '로그인', path : '/login', auth : 'guest'},
  {label : '마이페이지', path: '/mypage', auth : 'user'}
];


function Navbar() {
    const {user} = useAuth();

    //로그인 상태에 따라 보여지는 메뉴를 다르게 설정
    const navItems = NAV_ITEMS.filter(item=>{
      if(item.auth === 'any') return true;
      if(item.auth === 'guest') return !user;
      if(item.auth === 'user') return user;
    });
    return (
        <nav className="navbar">
            <ul className="menu-list">
                {
                navItems.map(item =>{
                  return(
                      <li className="menu-item" key={item.path}>
                        <Link className="menu-link" to={item.path}>{item.label}</Link>
                      </li>
                  );
                })
                }
            </ul>
            {
              user == null ? null : <h2>{user.id}님 반갑습니다.</h2>
            }
        </nav>
    );
}

export default Navbar;