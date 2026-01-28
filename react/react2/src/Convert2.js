
//분을 입력받으면 초로 변환하는 컴포넌트

import { useState } from "react";

//숫자를 입력하면 입력한 숫자에 해당하는 초가 바로 출력되도록 구현 
function Convert2(){

  let [amount, setAmount] = useState(0);


  return(
    <div>
      <div>
        <input 
            type="number" 
            disabled={true} 
            value={parseInt(amount/60)}/>
      </div>   
        <input 
            type="number" 
            onChange={e=>setAmount(e.target.value)}
            disabled={false}
            value={amount}/>
    </div>
  )

}

export default Convert2;