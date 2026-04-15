
import { useState } from "react";
import { sendData } from "./Ask"


function Ask(){

  const [form, setForm] = useState({prompt : ''})
  const [result, setResult] = useState('')

  const formSubmit = (e) =>{
    e.preventDefault()

    if(form.prompt.trim() === ''){
      alert("내용을 입력하세요.")
      return
    }

    sendData(form, (datas)=>{
      setResult(datas.message)
      setForm({...form, prompt : ''})
    })

  }

  const inputChange = (e) =>{
    const {name, value} = e.target
    setForm({...form, [name] : value})
  }

  //일반 함수들
  const sendData = async (params, callbackFunc) =>{
    try{
      // URLSearchParams는 객체 안에 있는 변수들을 다음과 같이 만들어줌
      // 변수=값 & 변수=값 & 변수=값 ...
      // 객체 = {변수 : 값, 변수 : 값}
      const queryString = new URLSearchParams(params).toString()
      const response = await fetch("/api/v1/ai/ask?" + queryString)

      if(!response.ok){
        return

      }
    const result = await response.json()
    // 콜백함수가 있으면 마지막에 콜백 함수를 실행
    if(callbackFunc)
      callbackFunc(result)
    }catch(e){
      console.error(e)

    }
  }

  

  return(

    <div>
      <h1>기본 ai 테스트 페이지</h1>
      <form style={{display:'flex'}} onSubmit={formSubmit}>
        <textarea name="prompt" rows={5} cols={30} onChange={inputChange} value={form.prompt}></textarea>
        <button>전송</button>
      </form>
      <h1>결과</h1>
      <div style={{border: "1px solid black", minHeight : "200px"}}>{result}</div>
    </div>
  );
}


export default Ask;