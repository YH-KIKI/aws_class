import { useState } from "react";
import { Link } from "react-router-dom";
import { fetchPost, sendData } from "./Ai";
import { hasFormSubmit } from "@testing-library/user-event/dist/utils";


function RagChatbot(){


  return(
    <div>
      <Link to={"/list"}>뒤로가기</Link>
      <h1>Rag 챗봇</h1>
      <IngestPdf/>
      <hr />
      <RagAsk/>
    </div>
  )
}
// 규정집 등록(pdf만)
function IngestPdf(){
  
  const [file, setFile] = useState(null);
  
  const submitHandler = async (e) => {
    e.preventDefault()
    const formData = new FormData();
    // 부트에서 받을 이름 file
    formData.append("pdfFile", file)

    //전송
    await fetchPost("/api/v1/ai/ingest-pdf", {
      method : 'POST',
      body : formData
    }, "json", (res)=>{
      alert(res.message)
    })
  }
  const fileChange = (e) =>{
    setFile(e.target.files[0]);
  }
  return (
    <form onSubmit={submitHandler}>
      <div>
        {/* 첨부파일 등록 후 규정집 등록 버튼을 클릭하면 규정집이 등록되도록 작업 
        결과를 alert()으로 띄움*/}
        <div>
          <input type="file" name="file" accept=".pdf" onChange={fileChange} />
        <div>
        </div>
          <button>규정집등록</button>
        </div>
      </div>
    </form>
  ) 
}

// 규정집에 있는 내용 질문
function RagAsk(){
  const [form, setForm] = useState({prompt : ''});
  const [result, setResult] = useState("");
  
  const submitHandler = async (e) =>{
    e.preventDefault()

    if(form.prompt.trim() === ''){
      alert("내용을 입력하세요.")
      return
    }

    await sendData('/api/v1/ai/rag-ask', form, 'json', (res)=>{
    setResult(res.message)
    })
  }
  const inputChange = (e) =>{
    const {name, value} = e.target
    setForm({...form, [name] : value})
  }


  return(
    <div>
      <form onSubmit={submitHandler}>
        <input type="text" name="prompt" placeholder="질문을 입력하세요" onChange={inputChange}/>
        <button>질문하기</button>
      </form>
      <pre style={{width:'100%', minHeight:'200px', border:'1px solid black'}}>{result}</pre>
    </div>
  )
  }

export default RagChatbot;