
function MapApp() {
   const list =[
    { name : "홍길동",  age : 21 },
    { name : "둘리",   age : 31 },
    { name : "고길동",  age : 41 },
];

/*
배열.map()
- 배열을 다른 형태의 배열로 변환하는 함수
- 매개변수로 함수가 필요. func : 함수
  - 보통 화살표 함수로 바로 구현
*/
  console.log(list);
  //item : 배열에서 꺼낸 값
  //index : 배열에서 꺼냈을 때의 위치. 0부터
  //arr : 배열 전체
  let ages = list.map((item, index, arr)=>{
    return item.age;
  });
  console.log(ages);
  return (
    <div>
      <ul>
      {
        list.map(item => {
          return(
            <li>
              <span>{item.name} : </span>
              <span>{item.age}</span>
            </li>
          );
        })
      }
    </ul>
    </div>
  )
}

export default MapApp;
