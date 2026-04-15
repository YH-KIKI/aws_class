import { Link } from "react-router-dom";
import { sendData } from "./Ai";
import { useState } from "react";


function Ask(){

  const [form, setForm] = useState({prompt : ''})
  const [result, setResult] = useState('')

  const formSubmit = (e) =>{
    e.preventDefault()

    if(form.prompt.trim() === ''){
      alert("내용을 입력하세요.")
      return
    }

    sendData('/api/v1/ai/ask', form, 'json', (datas)=>{
      setResult(datas.message)
      setForm({...form, prompt : ''})
    })

  }

  const inputChange = (e) =>{
    const {name, value} = e.target
    setForm({...form, [name] : value})
  }

  

  return(

    <div>
      <Link to={"/list"}>뒤로가기</Link>
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