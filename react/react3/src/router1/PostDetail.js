import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

function PostDetail(){
  const navigator = useNavigate();
  //URL에 있는 게시글 번호를 가져옴 /post/detail/:num
  //num을 받아옴
  let{num} = useParams();
  let[post, setPost] = useState({});

  //num을 이용하여 비동기 통신으로 게시글 정보를 받아와서 화면을 구성
  useEffect(()=>{

    const getPost = async ()=>{
      try{
          const response = await fetch("/api/v1/posts/" + num);
          
          if(response.status == 200){
            const result = await response.json();
            setPost(result);
          }
      }catch(e){
        console.error(e);
      }
    }
    getPost();
  }, []);

  //빈 객체이면(게시글을 못가져오면)
  //객체의 키(속성)들의 갯수가 0이면 => 객체에 속성이 없으면
  if(Object.keys(post).length == 0){
    return(
      <div>
        <h1>등록되지 않거나 삭제된 게시글 입니다.</h1>
      </div>
    )
  }

  const deletePost = () =>{
    // console.log("삭제 버튼 클릭") - 확인 하는 방법
    // 삭제 확인 알림을 띄움
    const isDel = window.confirm("게시글을 삭제 하시겠습니까?")

    if(!isDel){
      //취소를 누르면 종료
      return;
    }
      //확인을 누르면 삭제
      console.log("삭제")

      const sendDelPost = async () => {

        try{
          const response = await fetch("/api/v1/posts/" + num, {
            method : "DELETE",
            headers : {
              "Content-Type" : "application/json"
            },
            body : JSON.stringify({})

          })

          if(response.status == 200){
            const result = await response.json();
            if(result){
              alert("게시글을 삭제했습니다.");
              navigator("/posts");
            }else{
              alert("게시글을 삭제하지 못했습니다.");            }
          }

        }catch(e){
          console.error(e);
        }      
      }
      sendDelPost()
  }

 return(
  <div>
    <h1>게시글 상세</h1>
        <div>
          <div>제목 : {post.title}</div>
          <div>작성자 : {post.writer}</div>
          <div>작성일 : {post.date}</div>
          <hr />
          <div>내용 : </div>
          <div>{post.content}</div>
          <hr />
          <button onClick={deletePost}>삭제</button>
        </div>
  </div>
 )  
}

export default PostDetail;
