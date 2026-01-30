import { useState } from "react";

const EX_ITEMS = [
  {label : "예제1", isOpen : false, el : <Ex1 />},
  {label : "예제2", isOpen : false, el : <Ex2 />},
  {label : "예제3", isOpen : false, el : <Ex3 />},
]


function App2() {
  return (
    <div className="App">
      <Main />
    </div>
  );
}

function Main() {
  let [items, setItems] = useState(EX_ITEMS);

  const changeEx = (label) => {
    const updateEx = items.map(item=>{
      if(item.label === label){
        return {...item, isOpen : !item.isOpen};
      }
      return {...item, isOpen : false}
    });
    setItems(updateEx);

  }

  return (
    <div>
     <h1>메인</h1>
     <div>
      {items.map(item=>{
        return <button onClick={()=>changeEx(item.label)}>{item.label} : {item.isOpen ? "접기" : "보기" }</button>
      })}
     </div>
      {items.map(item=> item.isOpen ? item.el : null)}
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

function Ex3(){
  return <div><h1>예제3</h1></div>
}


export default App2;
