
function Btn1(){
    return(
        <button>일반버튼1</button>

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