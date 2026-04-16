import { Link } from "react-router-dom";
import { useState } from "react";
import { sendData } from "./Ai";

function Summarize(){

  const[result, setResult] = useState('')
  const [form, setForm] = useState({
      target_len : "",  max_sentence : 10, text : "",
    })

  const inputChange = (e) =>{
    const {name, value} = e.target
    setForm({...form, [name] : value})
  }

  const formSubmit = async (e) =>{
    e.preventDefault()

    if(form.max_sentence === ''){
      alert("줄 수를 입력하세요")
      return
    }

    if(form.text.trim() === ''){
      alert("내용을 입력하세요.")
      return
    }

    // setIsLoading(true)
    sendData('/api/v1/ai/summarize', form, 'json', (res) => {
      setResult(res.message)
    //   setIsLoading(false)
    }) 
  }

  return(

    <div>
      <Link to={"/list"}>뒤로가기</Link>
      <h1>요약</h1>
      <form onSubmit={formSubmit}>
      <div style={{display : 'flex', margin: '10px 0'}}>
        <label style={{width:'100px'}}>언어</label>
          <select name="target_len" style={{width:'100%'}} onChange={inputChange}>
            <option>한국어</option>
            <option>영어</option>
            <option>일본어</option>
            <option>중국어</option>
            <option>스페인어</option>
            <option>독일어</option>
          </select>
        </div>
        <div style={{display : 'flex', margin: '10px 0'}}>
        <label style={{width:'100px'}}>최대 요약 줄</label>
          <input type="number" style={{width:'100%'}} name="max_sentence" placeholder="원하는 줄 수를 숫자로입력하세요" onChange={inputChange}/>
      </div>
        <div>
          <p>내용</p>
          <textarea name="text" rows={10} cols={100} onChange={inputChange}></textarea>
        </div>
          <button>전송</button>
        </form>
        <h1>결과</h1>
        <div style={{border: "1px solid black", minHeight : "200px"}}>{result}</div>
    </div>
  );
}


export default Summarize;