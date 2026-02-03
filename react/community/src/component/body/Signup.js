import { useState } from "react";


function Signup(){

  let [info, setInfo] = useState({id : "", pw : "", pw2 : "", email : ""})

  const changeHandler = e => {
    //이벤트가 발생한 요소에 표준 속성인 name과 value를 가져옴
    //input, select, textarea가 아니면 name은 표준이 아님
    const {name, value} = e.target;
    //[name] : 속성명에 변수값을 쓰기 위해 [변수명]을 활용
    setInfo({...info, [name] : value});  //[]을 안쓰면 name이라는 변수가 아닌 속성을 불러오기 때문에 사용
  }

  const submitHandler = e =>{
    e.preventDefault();
    //아이디 유효성 검사(생략)

    //비번 유효성 검사(생략)

    //비번 확인 검사
    if(info.pw !== info.pw2){
      alert("비밀번호가 일치하지 않습니다.");
      return;
    }

    //비동기 통신으로 회원가입 진행
    signup(info);

  }

  const signup = async (info) =>{
    try{
      const response = await fetch("/api/v1/auth/signup", {
        method : "POST",
        headers : {
          "content-Type" : "application/json"
        },
        body : JSON.stringify(info)
      });

      if(!response.ok){
        alert("회원가입에 실패하였습니다.");
        return;
      }
      const res = await response.json();
      if(res){
        alert("회원가입이 완료되었습니다.");
      }else{
        alert("아이디/이메일이 사용중입니다.");
      }
    }catch(e){
      console.error(e);
    }
  }

  return(
    <div>
      <h1>회원가입</h1>
      <form onSubmit={submitHandler}>
        <div>
          <label htmlFor="id">아이디 : </label>
          <input type="text" name="id" id="id" onChange={changeHandler}/>
        </div>
        <div>
          <label htmlFor="pw">비밀번호 : </label>
          <input type="text" name="pw" id="pw" onChange={changeHandler}/>
        </div>
        <div>
          <label htmlFor="pw2">비밀번호 확인 : </label>
          <input type="text" name="pw2" id="pw2" onChange={changeHandler}/>
        </div>
        <div>
          <label htmlFor="email">이메일 : </label>
          <input type="email" name="email" id="email" onChange={changeHandler} />
        </div>
        <button>회원가입</button>
      </form>
    </div>
  )
}

export default Signup;