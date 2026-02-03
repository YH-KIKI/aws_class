import { useState } from "react";
import { authFetch } from "../../api/authFetch";


function Main() {

  //로그인 했으면 '로그인했습니다'로 뜨고, 아니면 '안했습니다'로 뜨도록 작업
  const fetchHandler = async () => {
      
      try{
          const response = await authFetch("/api/v1/auth/test")

          if(!response.ok){
            alert("로그인 인증 실패!");
            return;
          }      
          const res = await response.json();
          if(res){
            alert("로그인 인증 확인!");
          }
      }catch(e){
        console.error(e);
      }
  }

  const fetchHandler2 = async () => {
     
      try{
          const response = await authFetch("/api/v1/auth/me")

          if(!response.ok){
            alert("로그인 인증 실패!");
            return;
          }
          const res = await response.json();
          console.log(res);
      }catch(e){
        console.error(e);
      }
  }
  const LIST = [
    {age : 20, name : '홍길동'},
    {age : 30, name : '고길동'},
    {age : 40, name : '홍둘리'},

  ];
  const [list, setList] = useState(LIST);

  /*
  filter
  - 배열에서 조건에 맞는 항목들만 거름
  - 배열 항목 개수는 변경
  - 배열 항목 구조는 안 바뀜
  - true/fasle를 반환하는 함수가 필요
    -> false가 되면 거름
  map 
  - 배열에서 항목들을 가져와 변경하여 배열을 만듬
  - 배열 항목 개수 변경 없음
  - 배열 항목 구조 바뀜
  - 어떤 값(객체, 숫자, 문자열)을 반환하는 함수가 필요
    -> 어떤 값들을 이용해서 배열을 만듬
  */

  const filter1 = () => {
    const filterList = LIST.filter(item=>{
      return item.age >= 30;
    })
    setList(filterList);
  };

  const filter2 = () => {
    const filterList = LIST.filter(item=>{
      return item.name.startsWith("홍");
    })
    setList(filterList);
  };

  const filter3 = () => {
    const filterList = LIST.filter(item=>{
      return item.name.endsWith("길동");
    })
    setList(filterList);
  };

  const filter4 = (checkCallback) => {
    const filterList = LIST.filter(item=>{
      return checkCallback(item);
    });
    setList(filterList);
  }

  return (
    <div>
      <h1>메인</h1>
      <button onClick={fetchHandler}>인증 테스트</button>
      <button onClick={fetchHandler2}>회원 정보 테스트</button>
      <div>
        <button onClick={filter1}>30살 이상만</button>
        <button onClick={filter2}>성이 "홍"인 사람들만</button>
        <button onClick={filter3}>이름이 "길동"인 사람들만</button>
      </div>
      <div>
        <button onClick={()=>filter4((item)=>item.age >= 30)}>30살 이상만</button>
        <button onClick={()=>filter4((item)=>item.name.startsWith("홍"))}>성이 "홍"인 사람들만</button>
        <button onClick={()=>filter4((item)=>item.name.endsWith("길동"))}>이름이 "길동"인 사람들만</button>
      </div>
      <ul>
        {!list ? null : list.map(item=>{
          return <li>{item.age}, {item.name}</li>
        })}
      </ul>
    </div>
  );
}


export default Main;