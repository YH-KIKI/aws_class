// 다른 컴포넌트를 가져와서 사용하는 방법
// import 컴포넌트명 from '컴포넌트js파일명'
// import {컴포넌트1, 컴포넌트2} from '컴포넌트js파일명';

import { Btn1, Btn2, Btn3, Btn4 } from "./Btns";
import { Select } from "./Inputs";


/*
컴포넌트 생성방법
※컴포넌트는 반드시 대문자로 시작※
1. 함수로 생성
   - 함수로 생성하는 방법이 대세
2. 클래스로 생성

컴포넌트 생성방법(함수)
방법1. 함수 선언식 
function 컴포넌트명(){
  return (
  )
}
/
방법2. 함수 표현식
2-1
const 컴포넌트명 = function(){

}
2-2 화살표함수(람다식)
const 컴포넌트명 = ()=>{
  
}
*/
/* 주의사항
1. 컴포넌트 대문자로 시작
2. return 할 때 ()가 없으면 상황에 따라 에러 발생하니 ()를 붙이는 습관을 들이자.
3. return 안에 최상위 컴포넌트는 1개만 있어야 함.
*/
/*
Select 컴포넌트를 Input.js에 생성해서 구성하고, App 컴포넌트에서 불러와 배치
Select 컴포넌트 : 
<select>
  <option>타입1</option>
  <option>타입2</option>
</select>
*/
/*
1. class 속성 대신 classNAme으로 
2. for 속성 대신 htmlFor로
*/
const App = function() {
  const btnName1 = "일반버튼3";
  const btnName2 = "일반버튼4";
  return (
    <div className="App">
      <Btn1 />
      <br></br>
      <Btn2 />
      <br></br>
      <Select />
      <br></br>
      <Btn3 text = {btnName1} color = {"red"} />
      <Btn3 />
      <br></br>
      <Btn4 text = {btnName2} color = {"blue"} />
      <Btn4 />

      {/*<lable htmlFor ="id">아이디</lable>
      <input type = "text" id="id"/>*/}
    </div>
  );
}


//export dafault 컴포넌트명; -> 이 파일에서 하나의 컴포넌트만 내보내는 경우
//내보낸다 -> 다른 곳에서 해당 컴포넌트를 사용할 수 있다
export default App;

//여러 컴포넌트를 밖에서 사용할 수 있게 내보내는 방법
//export {컴포넌트1, 컴포넌트2, ...};
