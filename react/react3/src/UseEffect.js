import { useEffect, useState } from "react";

//useEffect 예제
function UseEffect() {
  
  //저장할 변수
  let [num, setNum] = useState(0);
  let [num2, setNum2] = useState(0);

  //실행할 함수
  const plusNum = () => {setNum(num + 1)}
  const minusNum = () => {setNum(num - 1)}

  useEffect(()=>{
    console.log("useEffect 함수 실행. 랜더링 될때마다");
  });

  //의존성 배열 : 빈배열 => 처음 렌더링만 실행
  useEffect(()=>{
    console.log("useEffect 함수 실행. 처음만");
  }, []);

   //의존성 배열 : 변수 => 변수가 바뀔때 마다 실행
  useEffect(()=>{
    console.log("useEffect 함수 실행. num이 바뀔때마다");
    //setNum(num+1); //주석을 해제하면 무한 루프 발생
  }, [num]);

  return (
    <div>
      <button onClick={minusNum}>-</button>
      {/*<button onClick={()=>setNum(num - 1)}>-</button>
          한줄이라 위처럼 따로 함수 안만들고 이렇게 해도 OK*/}
      <span style={{padding : "0 5px"}}>{num}</span>
      <button onClick={plusNum}>+</button>
      <hr/>
      <span>{num2}</span>
      <button onClick={()=>setNum2(num2 + 1)}>+</button>
    </div>
  );
}

export default UseEffect;
