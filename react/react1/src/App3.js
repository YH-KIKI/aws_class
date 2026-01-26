import { useState } from "react";

function App3(){

  //state를 이용하여 변수 num과 setNum 함수를 가져옴
  //setNum은 num의 값을 변경한 후 렌더링을 함.
  let [num, setNum] = useState(1);

  const plusNum = () => { setNum(num + 1); }
  const minusNum = () => { setNum(num - 1); }


  let arr = [1, 2, 3]; //num1에 1을, num2에 2를 num3에 3을 저장
  /*let num1 = arr[0];
  let num2 = arr[1];
  let num3 = arr[2]; */
  let [num1, num2, num3] = arr;
  console.log(num1, num2, num3);

  let obj = {
    name : '홍길동',
    age : 21
  };
  // 객체에 있는 값을 변수에 저장 
  let {name, age} = obj;
  //let name =obj.name;
  console.log(name, age);
  
  // = 을 통해 arr의 값을 arr2에 가져옴(공유)
  // arr2에 4를 뒤에 추가
  // arr확인. 4가 뒤에 추가 됨.
  let arr2 = arr;
  arr2.push(4);
  console.log(arr);

  arr = [1,2,3];
  //[...배열]로 arr3에 넣어줌. 공유가 아니라 복사본을 넘김
  //arr3에 4를 추가
  //arr확인. 4가 추가가 안됨

  let arr3 = [...arr];
  arr3.push(4);
  console.log(arr);


  return (
    <div>
      <h1>값 : {num}</h1>
      <button onClick={minusNum}>+</button>
      <button onClick={plusNum}>+</button>
    </div>
  );
}

export default App3;