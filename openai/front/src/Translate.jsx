import { Link } from "react-router-dom";
import { useState } from "react";
import { sendData } from "./Ai";

function Translate(){

  const [form, setForm] = useState({style : 'formal', text : ''})
  const [result, setResult] = useState('')
  const [isLoading, setIsLoading] = useState(false) //작업중이 아니니 false / true로하면 문구가 바로뜸

  //이벤트들
  //태그들의 입력값들이 변하면 변한 값을 form(state 변수)에 넣어주는 함수
  const inputChange = (e) =>{
    const {name, value} = e.target
    setForm({...form, [name] : value})
  }

  const formSubmit = async (e) =>{
    e.preventDefault()
    console.log("전송됨", form)

    if(form.text.trim() === ''){
      alert("내용을 입력하세요.")
      return
    }

    sendData('/api/v1/ai/translate', form, 'json', (res) => {
      setResult(res.message)
      setIsLoading(false)
    }) 
  }

  return(
    <div>
      <h1>번역</h1>
      <Link to={"/list"}>뒤로가기</Link>
      <h2>텍스트를 입력하세요</h2>
      <form onSubmit={formSubmit}>
        <select name="style" onChange={inputChange}>
          <option value="formal">격식</option>
          <option value="casual">반말</option>
          <option value="besiness">비즈니스</option>
        </select>
        <div style={{display:'flex'}}>
          <textarea name="text" rows={5} cols={30} 
            style={{width:'100%'}}
            onChange={inputChange} 
            //value={form.prompt}
          ></textarea>
          <div style={{width:'100%', border:'1px solid black'}}>
            {
              isLoading ? "[[번역중입니다. 잠시만 기다려주세요]]" : result
            }
          </div>
        </div>
        <button disabled={isLoading}>번역</button>
      </form>
    </div>
  );


  
}

export default Translate;