import { Link } from "react-router-dom";
import { useState } from "react";

import './ChatBot.css'

function ChatBot() {
  const [form, setForm] = useState({msg : '', name: ''})
  const [chatList, setChatList] = useState([])

  const inputChange = (e) => {
    const {name, value} = e.target;
    setForm({...form, [name] : value})

  };

  const chatSubmit = (e) => {
    e.preventDefault();
    //1. 입력 확인 : 비어있으면 종료
    // 앞에서 메세지 입력 후 콘솔창 확인할 때 누굴 통해 확인했는지 기억
    if (form.msg.trim() === ""){
      alert("채팅을 입력하세요");
      return
    }

    // 2. 서버로 비동기 통신 요청
    // method : post
    // url : /api/v1/chat
    // 보낼내용 msg

      sendDataPost('/api/v1/chat', {"Content-type" : "application/json"}, form)
      
      //3. 입력을 비움
      setForm({...form, msg : ''})
    }

    async function sendDataPost(url, headers, data){
      try{
        const response = await fetch(url, {
          method : "POST",
          headers : headers,
          body : JSON.stringify(data)
        })
        if(! response.ok){
          return
        }
        const result = await response.json()

        setChatList(list => [...list, {className : 'chat-me', text : form.msg}])
        setChatList(list => [...list, {className : 'chat-bot', text : result.answer}])

        // 입력한 내용을 chat-me에 추가

        // 서버에서 보낸 내용을 chat-bot에 추가
      }catch(e){
        console.error(e);
      }
      return null;
    };

  return (
    <div>
      <Link to="/">메인으로 이동</Link>

      <h1>채팅 내역</h1>
      <div className = "chat-container">
        <div className="chat-body">
          {
            chatList.map((chat, index)=>{
              return(
                <div className={chat.className} key={index}>
                  <div className="chat-content">{chat.text}</div>
                </div>
              )
            })
          }
        </div>
      </div>
        
      <form className="chat-footer" onSubmit={chatSubmit}>
        <input 
          type="text" 
          name="msg" 
          className="chat-input" 
          value={form.msg}
          onChange={inputChange}
        />
        <button className="btn btn-chat-submit">전송</button>

        </form>
    </div>
  );
}

export default ChatBot;