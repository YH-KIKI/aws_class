import { useState } from "react"

/*
- 접기 버튼을 누르면 글자가 더보기로 바뀌고, 내용이 감춰짐
- 더보기 버튼을 누르면 글자가 접기로 바뀌고, 내용이 보임
*/

function App7(){
  
  //isOpen이 true : 내용이 보임(접기 버튼)
  //isOpen이 false: 내용이 안보임(더보기 버튼)
  let [isOpen, setOpen] = useState(true);

  const btnClick = () => {setOpen(!isOpen)}
   
  return(
    <div>
      <button onClick={btnClick}>{isOpen ? "접기" : "더보기"}</button>
      {
        isOpen ? <div>내용입니다.</div> : null
      } 
    </div>
  );
}

export default App7;