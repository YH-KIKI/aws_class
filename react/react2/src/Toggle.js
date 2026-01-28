import { useState } from 'react';
import './toggle.css';


function Toggle(){

  const menus = ["메뉴1", "메뉴2", "메뉴3"];
  const [activeMenu, setActiveMenu] = useState("");

  return(
    <div>
      <ul className="menu-list">
        {
          menus.map(menu=>{
            return(
              <li
              onClick={()=> setActiveMenu(menu)} 
              className={`menu-item ${activeMenu === menu ? "active" : ""}`}
              key={menu}>{menu}</li>
            );
          })
        }
      </ul>
    </div>
  )

}

export default Toggle;