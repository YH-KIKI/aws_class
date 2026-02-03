import { useState } from "react";


// 강사님ver. -> Login.js로 활용

function Insert2() {
	//name을 VO필드와 맞출 것
  let [date, setDate] = useState("");
	let [text, setText] = useState("");


  const addTodo = (e) =>{
    e.preventDefault(); //form태그 전송 기능 막기
		//비동기 통신으로 날짜, 할일 전송해서 추가하는 함수를 선언
		const sendInsertTodo = async () =>{ 
			try{
					const response = await fetch("/api/v1/todos", {
						method : "POST",
						headers : {
							"Content-Type" : "application/json"
						},
						body : JSON.stringify({
							date : date,
							text : text
					})
			});

			if(response.status == 200){
				const result = await response.json();
				if(result){
						alert("할일을 등록했습니다.");
						setText("");
				}else{
						alert("할일을 등록하지 못했습니다.");
				}
			}
		}catch(e){
			console.error(e);
		}
  }

	//함수 호출
	sendInsertTodo();
}

  
  return (
    	<div>
			  <h1>할일 등록</h1>
        <form className="insert-form" onSubmit={addTodo}>
					{/*name을 VO필드와 맞출 것*/}
					<div>
						<label htmlFor="date"> 날짜: </label>
						<input type="date" name="date" id="date" 
									onChange={(e)=>{ setDate(e.target.value) }}
									value={date}/>
					</div>
					<div>
          <label htmlFor="text"> 할일: </label>
					<input type="text" name="text" id="text" 
								onChange={(e)=>{ setText(e.target.value) }}
								value={text}/>
					</div>
					<button>등록</button>
        </form>
		</div>
  );
}
export default Insert2;