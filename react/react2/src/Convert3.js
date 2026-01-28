
//분을 입력받으면 초로 변환하는 컴포넌트

import { useState } from "react";

//숫자를 입력하면 입력한 숫자에 해당하는 초가 바로 출력되도록 구현 
function Convert3(){

  let [amount, setAmount] = useState(0);
  let [flag, setFlag] = useState(true);

  const changeAmount = e => setAmount(e.target.value);
  const btnClick = () => {
    setAmount(flag ? parseInt(amount / 60) : amount * 60);
    setFlag(!flag);
  }


  return(
    <div>
      <div>
        <input 
            type="number"
            onChange={changeAmount}
            disabled={flag}
            // flag일 때 parseInt(amount/60), !flag일 때 amount
            value={flag ? parseInt(amount/60) : parseInt(amount)}/>
        <button onClick={(btnClick)}>변환</button>
      </div>   
        <input 
            type="number" 
            onChange={changeAmount}
            disabled={!flag}
            // !flag 일 때 amount. flag일 때 amount*60
            value={flag ? parseInt(amount) : parseInt(amount * 60)}/>
    </div>
  )

}

export default Convert3;