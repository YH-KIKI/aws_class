import { useState } from "react";

function ToDoList(){

  //할일을 저장할 state 변수를 선언. 문자열
  const [todo, setTodo] = useState('');

  //할일 목록을 저장할 state 변수를 선언. 객체 배열
  // id: 번호, todo :할일
  const [todos, setTodos] = useState([
    { id : 1 , todo : '할일1' },
    { id : 2 , todo : '할일2' }
  ]);

  //번호를 지정할 변수를 선언
  let [num, setNum] = useState(3);

  //할일을 입력하면 state 변수에 저장하는 함수 선언 및 이벤트 등록
  const todoChange = (e) => {setTodo(e.target.value)}

  //등록을 클릭하면 입력된 할일을 할일 목록에 추가하는 함수를 선언 및 이벤트 등록
    const addTodo = (e) => {
      e.preventDefault(); //form태그의 submit 이벤트가 발생되어 서버에 전송되지 않도록 하기 위해
      //추가할 객체
      let todoObj ={
        id : num,
        todo : todo,
      }
      setTodos([...todos, todoObj]);
      setNum(num+1);
      setTodo("");
  }

  //num : 삭제할 할일 id
  const delTodo = num => {
    //할일 목록 중 id가 num과 같지 않은 목록만 가져옴
    const newTodos = todos.filter((item)=>{
      return item.id !== num;
    });

    const newTodos2 = todos.filter(item=>item.id !== num);
    //위에서 가져온 목록으로 수정
    setTodos(newTodos);

  }
  return(
    <div>
      <form onSubmit={addTodo}>
        <input type="text" placeholder="할일을 입력하세요." onChange={todoChange} value={todo}/>
        <button>등록</button>
      </form>
      <h1>할 일 목록</h1>
      <ul>
      {
      todos.length === 0 ?
      <li>등록된 할일이 없습니다.</li> : 
      todos.map((item) => {
        return (
							<li key={"todo"+item.id}>
                {item.todo}
                <button style={{fontSize:'20px'}} onClick={e=>delTodo(item.id)}>&times;
                </button>  
              </li>
						);
        })
      }
      </ul>
    </div>
  )
}

export default ToDoList;