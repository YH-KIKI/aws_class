import { useEffect, useState } from "react";
import "./List.css"

/*
비동기 통신으로 할일 목록 전체를 요청해서 가져온 후 화면에 출력
url : /api/v1/todos
method : GET
*/

function List() {
  let [todos, setTodos] = useState([
    { num : 1, text : "점심", date : "2026-01-29", order : 1},
    { num : 2, text : "4교시", date : "2026-01-29", order : 2}
  ]);

  useEffect(()=>{
    //비동기 통신으로 할일 목록 전체를 요청해서 가져오는 함수 선언
    const getTodos = async () => {
      //함수 호출
      try{
          const response = await fetch("/api/v1/todos");

          if(response.status == 200){
            const result = await response.json();
            setTodos(result);
          }
        }catch(e){
          console.error(e);
        }
      }
    getTodos();
  }, []);

  const btnClick = async (num) => {
    try{
      const response = await fetch("/api/v1/todos/" + num, {
        method : 'DELETE',
      });

      if(response.status == 200){
				const result = await response.json();
				if(result){
						alert("할일을 삭제했습니다.");
				}else{
						alert("할일을 삭제하지 못했습니다.");
				}
			}
    }catch(e){
			console.error(e);
		}
  }

  return (
    <div>
      <h1>할일 목록</h1>
      <ul className="todo-list">
        {
          todos.map(todo=>{
            return(
              <li key={todo.num} className="todo-item">
                <span className="todo-date">{todo.date}</span>
                <span className="todo-text">{todo.text}</span>
                <button className="todo-btn" onClick={()=>btnClick(todo.num)}>&times;</button>
              </li>
            )
          })
        }
      </ul>
    </div>
  );
}

export default List;