import { Link } from "react-router-dom";

import "./Navbar.css";

const NAV_ITEMS = [
  {label : '메인', path : '/'},
  {label : '조회', path : '/todo/list'},
  {label : '등록', path : '/todo/insert'}

];


function Navbar() {
  return (
    <nav className="navbar">
      <ul className="menu-list">
        {
        NAV_ITEMS.map(item =>{
          return(
            <li className="menu-item" key={item.path}>
              <Link className="menu-link" to={item.path}>{item.label}</Link>
            </li>
          )
        })
        }
      </ul>
       
    </nav>
  );
}

export default Navbar;