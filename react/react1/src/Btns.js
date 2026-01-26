
/*
이벤트 등록 시 주의사항
- on이벤트명에서 이벤트명은 대문자로 시작(리액트)
- on이벤트명에서 이벤트명은 소문자로 시작(js)
*/
function Btn1(){
    /*function btnClick(){
      alert("클릭");
    }*/
    // 버튼 클릭할 때 호출될 함수
    const btnClick = () => { alert ("클릭"); }

    return(
        <button onClick={btnClick}>일반버튼1</button>
    );
}

const Btn2 = () => {
    return(
        <button>일반버튼2</button>
    );
}
//부모가 보낸 정보를 props 객체로 받음
function Btn3(props){
    return(
        <button style ={{color : props.color}}>{props.text}</button>
    );
}
//부모가 보낸 정보를 객체로 받음
function Btn4({text = "버튼", color = "green"}){
    return(
        <button style ={{color : color}}>{text}</button>
    );
}
// 예전 리액트는 반영 됐으나 18버전 이후에는 지양하기 때문에 적용 안됨
Btn3.defaultProps = {
    text : "버튼",
    color : "green"
};

export {Btn1, Btn2, Btn3, Btn4};