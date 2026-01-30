import { useState } from "react";
import { useNavigate } from "react-router-dom";

function PostInsert(){

  //게시글 등록 성공 후 게시글 목록으로 이동하기 위해서
  const navigator = useNavigate();
	
	let [title, setTitle] = useState("");
	let [writer, setWriter] = useState("");
	let [content, setContent] = useState("");

	const titleChange = (e) =>{ setTitle(e.target.value); };
	const writerChange = (e) =>{ setWriter(e.target.value); };
	const contentChange = (e) =>{ setContent(e.target.value); };
	const addPost = (e) =>{
		e.preventDefault();

		if(!title.trim()){
			alert("제목을 입력하세요.");
			return;
		}
		if(!writer.trim()){
			alert("작성자을 입력하세요.");
			return;
		}
		if(!content.trim()){
			alert("내용을 입력하세요.");
			return;
		}

		let post = {
			title,
			writer,
			content
		}
		
		sendPost(post);
	}
	//비동기 통신으로 게시글을 등록
	const sendPost = async (post) =>{
		try{
			const response = await fetch("/api/v1/posts", {
				method : "POST",
				headers : {
					"Content-Type" : "application/json"
				},
				body : JSON.stringify(post)
			});
			console.log(JSON.stringify(post))
			if(response.ok){
				const result = await response.json();
				if(result){
					alert("게시글을 등록했습니다.");
          //게시글 등록 후 /posts로 이동
          navigator("/posts");
				}else{
					alert("게시글을 등록하지 못했습니다.");
				}
			}
		}catch(e){
			console.error(e);
		}
	}

	//방법2 : 객체를 이용해서 입력을 받음
	let [post, setPost] = useState({
		title : "",
		writer : "", 
		content : ""
	});

	const inputChange = (e) =>{
		setPost({
			...post,
			[e.target.name] : e.target.value
		});
	}

	const addPost2 = (e) =>{
		e.preventDefault();

		if(!post.title.trim()){
			alert("제목을 입력하세요.");
			return;
		}
		if(!post.writer.trim()){
			alert("작성자를 입력하세요.");
			return;
		}
		if(!post.content.trim()){
			alert("내용을 입력하세요.");
			return;
		}

		sendPost(post);
	}

	return (
		<div>
			<h1>게시글 등록</h1>
			<form onSubmit={addPost}>
				<input type="text" name="title" placeholder="제목 입력" onChange={titleChange} />
				<br/>
				<input type="text" name="writer" placeholder="작성자 입력" onChange={writerChange} />
				<br/>
				<textarea name="content" placeholder="내용 입력" onChange={contentChange}></textarea>
				<br/>
				<button>등록</button>
			</form>

			<h1>게시글 등록 방법2</h1>
			<form onSubmit={addPost2}>
				<input type="text" name="title" placeholder="제목 입력" onChange={inputChange} />
				<br/>
				<input type="text" name="writer" placeholder="작성자 입력" onChange={inputChange}/>
				<br/>
				<textarea name="content" placeholder="내용 입력" onChange={inputChange}></textarea>
				<br/>
				<button>등록</button>
			</form>
		</div>
	)
}

export default PostInsert;