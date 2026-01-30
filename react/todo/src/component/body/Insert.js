import { useState } from "react";


function Insert() {
  let [todo, setTodo] = useState({
    date : "",
    text : "",
  });

  const inputChange = (e) => {
    setTodo({
			...todo,
			[e.target.name] : e.target.value
		});

  }

  const addTodo = (e) =>{
    e.preventDefault();

    if(!todo.date.trim()){
			alert("날짜를 입력하세요.");
			return;
		}
		if(!todo.text.trim()){
			alert("할일을 입력하세요.");
			return;
		}
     sendTodo();
  }

  //비동기 통신으로 게시글을 등록
	const sendTodo = async () =>{
		try{
			const response = await fetch("/api/v1/todos", {
				method : "POST",
				headers : {
					"Content-Type" : "application/json"
				},
				body : JSON.stringify(todo)
			});
			console.log(JSON.stringify(todo))
			if(response.status == 200){
				const result = await response.json();
				if(result){
					alert("할일을 등록했습니다.");
          setTodo({text : ""}) //text만 비우기
				}else{
					alert("할일을 등록하지 못했습니다.");
				}
			}
		}catch(e){
			console.error(e);
		}
	}

  
  return (
    	<div>
			  <h1>할일 등록</h1>
        <form onSubmit={addTodo}>
          <input type="date" name="date" onChange={inputChange} value={todo.date}/>
          <br/>
          <input type="text" name="text" placeholder="할일을 입력하세요" onChange={inputChange} value={todo.text}/>
          <br/>
          <button>등록</button>
          <hr/>
        </form>
		</div>
  );
}

export default Insert;