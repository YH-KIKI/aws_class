
//state를 설명하기 위한 예제
function App2(){
  let num = 1;

  /*
  버튼을 클릭하면 num의 값이 증가하지만 화면에 있는 값은 안바뀜
  -> 변수값을 바꾸고 렌더링을 해야하는데 렌더링이 되지 않아서
  */

  const plusNum = () => {
    ++num;
    console.log(num);
  }

  return(
    <div>
      <h1>값 : {num} </h1>
      <button onClick={plusNum}>+</button>
    </div> 
  );  
}

export default App2;