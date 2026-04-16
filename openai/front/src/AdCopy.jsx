import { Link } from "react-router-dom";
import { useState } from "react";
import { sendData } from "./Ai";


function AdCopy(){

  const[result, setResult] = useState('')
  const [isLoading, setIsLoading] = useState(false) 
  const [form, setForm] = useState({
    product : "", feature : "", target : "전연령", temp : "0.2", count: 50,
  })


  const inputChange = (e) =>{
    const {name, value} = e.target;
    setForm({...form, [name] : value});
  }

  const formSubmit = async (e) =>{
    e.preventDefault()

    if(form.product.trim() === ''){
      alert("내용을 입력하세요.")
      return
    }

    if(form.feature.trim() === ''){
      alert("내용을 입력하세요.")
      return
    }

    setIsLoading(true)
    sendData('/api/v1/ai/ad-copy', form, 'json', (res) => {
      setResult(res.message)
      setIsLoading(false)
    }) 
  }
  

  return(
    <div>
      <Link to={"/list"}>뒤로가기</Link>
      <h1>광고 문구 제작</h1>
   
    <form onSubmit={formSubmit}>
      <div style={{display : 'flex', margin: '10px 0'}}>
        <label style={{width:'100px'}}>상품명</label>
          <input type="text" style={{width:'100%'}} name="product" onChange={inputChange} />
      </div>
      <div style={{display : 'flex', margin: '10px 0'}}>
        <label style={{width:'100px'}}>상품특징</label>
          <textarea name="feature" style={{width:'100%'}} onChange={inputChange}></textarea>
      </div>
      <div style={{display : 'flex', margin: '10px 0'}}>
        <label style={{width:'100px'}}>연령층</label>
          <select name="target" style={{width:'100%'}} onChange={inputChange}>
            <option>10대</option>
            <option>20대</option>
            <option>30대</option>
            <option>중년층</option>
            <option>장년층</option>
            <option>노인</option>
          </select>
      </div>
      <div style={{display : 'flex', margin: '10px 0'}}>
        <label style={{width:'100px'}}>문구 창의성</label>
        <div style={{width:'100%'}}>
          <span>일반</span>
          <input type="range" name="temp" min = "0" max="1" step="0.1" value={form.temp}
            onChange={inputChange}/>
          <span>창의적</span>
        </div>
      </div>
      <button style={{width:'100%', margin: '10px 0'}} disabled={isLoading}> 광고 문구 생성</button>
    </form>

      <h2>생성된 광고 문구</h2>
      <pre style={{border: "1px solid black", minHeight : "200px"}}>
        {
          isLoading ? "광고 문구를 생성중입니다... \n잠시만 기다려주세요" : result
        }
      </pre>
    </div>

  )

}

export default AdCopy;