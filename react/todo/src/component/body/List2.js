import { useEffect, useState } from "react";
import "./List.css"

// 강사님 ver.

/*
비동기 통신으로 할일 목록 전체를 요청해서 가져온 후 화면에 출력
url : /api/v1/todos
method : GET
*/

function List(){
	let [todos, setTodos] = useState([]);
	//목록을 새로고침할 때 사용할 변수
	let [isReload, setIsReload] = useState(true);
	let [date, setDate] = useState("");

	useEffect(()=>{
		
		if(isReload){
			getTodos();
		}

	}, [isReload]);

	//비동기 통신으로 할일 목록 전체를 요청해서 가져오는 함수 선언
	const getTodos = async (date)=>{
		try{
			//선택한 날짜를 가져옴. 전체조회이면 빈문자열을, 날짜를 선택하면 선택한 날짜를 전송
			if(!date){
				date = "";
			}
			const response = await fetch("/api/v1/todos?date=" + date,{
				method : "GET",
				// headers : {
				// 	"Content-Type" : "application/json"
				// },
				// body : { date : "선택한 날짜"}
			});

			if(response.ok){
				const result = await response.json();
				setTodos(result);
				setDate(date);
				setIsReload(false);
			}
		}catch(e){
			console.error(e);
		}
	}

	const btnClick = (num)=>{
		
		//비동기 통신으로 삭제 요청하는 함수를 선언
		const sendDeleteTodo = async()=>{
			try{
				const response = await fetch("/api/v1/todos/" + num, {
					method : "DELETE"
				});

				if(response.status === 200){
					const result = await response.json();
					if(result){
						alert("할일을 삭제했습니다.");
						//화면에서 목록 삭제
						//삭제한 할일 번호와 일치하지 않은 할일들만 찾음
						//화면에서 바로 제거
						setTodos(todos.filter(item=>item.num !== num));
						//db에서 가져와서 다시 재배치
						setIsReload(true);
					}else{
						alert("할일을 삭제하지 못햇습니다.");
					}
				}
			}catch(e){
				console.error(e);
			}
		}

		//함수 호출
		sendDeleteTodo();
	}

	const dateTodosClick = (date)=>{
		setDate(date);
		setIsReload(true);
		getTodos(date);
	}

	return(
		<div>
			<h1>할일 목록</h1>
			{/* 날짜와 상관없이 전체 조회 */}
			<button onClick={()=>dateTodosClick("")}>전체 조회</button>
			{/* 날짜를 선택하면 선택한 날짜에 맞는 할일을 조회 */}
			<input type="date" onChange={e=>dateTodosClick(e.target.value)} value={date} />
			
			<Todos todos={todos} delBtnClick={btnClick} date={date} 
      setIsReload={setIsReload} />
			
		</div>
	)
}

function Todos({todos, delBtnClick, date, setIsReload}){
	return (
		<ul className="todo-list">
			{
				date === "" ? 
				<h2>전체</h2>:
				<h2>{date}</h2>
			}
			{
			todos.length === 0 ? 
			<li><h3>등록된 할일이 없습니다.</h3></li>:
			todos.map(todo=>{
					return (
						<Todo 
              date={date} 
              todo={todo} 
              delBtnClick={delBtnClick} 
              key={todo.num}
              setIsReload={setIsReload} />
					)
				})
			}
		</ul>
	);
}

function Todo({todo, date, delBtnClick, setIsReload}){
  let [isUpdate, setIsUpdate] = useState(false);
  let [text, setText] = useState(todo.text);

  const fetchUpdate = async(num) => {

    try{

      const response = await fetch("/api/v1/todos/" + num,{
        method : "PUT",
        headers : {
          "Content-Type" : "application/json"
        },
        body : JSON.stringify( { text, num } )
      });

      if(response.ok){
        const result = await response.json();
        if(result){
          alert("수정했습니다");
          setIsUpdate(false);
          setIsReload(true);
        }else{
          alert("수정하지 못했습니다.");
        }
      }

    }catch(e){
      console.error(e);
    }
  }
  return(
   
    <li className="todo-item">
      {
        isUpdate ? 
        <div>
          <input type ="text" value={text} onChange={(e)=>setText(e.target.value)} />
          <button onClick={()=>fetchUpdate(todo.num)}>수정</button>
          <button onClick={()=>setIsUpdate(false)}>취소</button>
        </div>
        :
        <div>
          {
            date === "" ?
            <span className="todo-date">{todo.date}</span> : 
            null
          }
          <span className="todo-text">{todo.text}</span>
          <button className="todo-btn" onClick={()=>delBtnClick(todo.num)}>&times;</button>
          <button onClick={()=>setIsUpdate(true)}>수정</button>
        </div>
      }
    </li>
  )
}

export default List;