import { useState } from "react";


function App() {
  return (
    <div className="App">
      <Main />
    </div>
  );
}

function Main() {

  let [isOpen1, setIsOpen1] = useState(false);
  let [isOpen2, setIsOpen2] = useState(false);

  const showEx = (label) => {
    //label이 예제1이면 isOpen1을 토글하고(true => false, false=> true), isOpen2를 false로
    if(label === "예제1"){
      setIsOpen1(!isOpen1);
      setIsOpen2(false);
    }else{
    //아니면(예제2이면) isOpen2를 토글하고, isOpen1을 false로
    setIsOpen2(!isOpen2)
    setIsOpen1(false)
    }
  }

  return (
    <div>
     <h1>메인</h1>
     <div>
        <button onClick={()=>showEx("예제1")}>
          예제1 : {isOpen1 ? "접기" : "보기"}</button>
        <button onClick={()=>showEx("예제2")}>
          예제2 : {isOpen2 ? "접기" : "보기"}</button>
     </div>
      {/* isOpen1이 true면 Ex1이 보임 : false면 null값 */}
      { isOpen1 ? <Ex1 /> : null }
      { isOpen2 ? <Ex2 /> : null }
    </div>
  );
}

function Ex1(){
  let [num, setNum] = useState(0);

  return (
    <div>
      <h1>예제1</h1>
        <button onClick={()=> { setNum( num - 1) }}>-</button>
        <input type="text" readOnly = {true} value = {num}/>
        <button onClick={()=> { setNum( num + 1) }}>+</button>
    </div>
  );

}

function Ex2(){
  let [text, setText] = useState("");

  return(
    <div>
      <h1>예제2</h1>
      <input type = "text" onChange={(e)=> {setText(e.target.value)}} value={text}/>
      <h2>{text}</h2>
    </div>  
  )
}


export default App;
