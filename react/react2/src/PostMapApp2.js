import { useState } from "react";


/*
등록 버튼을 클릭하면 입력한 제목, 작성자, 내용이 콘솔에 출력되도록 작성
*/
function PostMapApp2(){
  //게시글 등록할 때 게시글 번호 
  let [num, setNum] = useState(2);
  let [posts, setPosts] = useState ([
    {num : 1, title : '제목입니다', content : '내용입니다.', writer : '홍길동'},
  ]);

  const [title, setTitle] = useState("");
  const [writer, setWriter] = useState("");
  const [content, setContent] = useState("");


  const titleChange = (e)=> setTitle(e.target.value);  //함수가 한줄일땐 {} 생략 가능
  const writerChange = (e)=>{ setWriter(e.target.value); }
  const contentChange = (e)=>{ setContent(e.target.value); }


  const postInsert = (e) =>{ e.preventDefault();
    const obj ={
      num,
      title,
      writer,
      content
    }
    setNum(num + 1);
    setPosts([obj, ... posts]);
  }

  return(
    <div>
      <form onSubmit={postInsert}>
        <input type ="text" placeholder="제목을 입력하세요." onChange={titleChange}/>
        <br />
        <input type ="text" placeholder="작성자를 입력하세요." onChange={writerChange} />
        <br />
        <textarea placeholder="내용을 입력하세요." onChange={contentChange}></textarea>
        <br />
        <button>게시글 등록</button>
      </form>
      <h1>게시글 목록</h1>
      <table>
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
          </tr>
        </thead>
        <tbody>
          {
            posts.map((item) => {
              return(
                <tr key = {"post" + item.num}>
                  <td>{item.num}</td>
                  <td>{item.title}</td>
                  <td>{item.writer}</td>
                </tr> 
              );
            })
          }
        </tbody>
      </table>
    </div>
  );
}

export default PostMapApp2;