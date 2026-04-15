const sendData = async (params, callbackFunc) =>{
  try{
    // URLSearchParams는 객체 안에 있는 변수들을 다음과 같이 만들어줌
    // 변수=값 & 변수=값 & 변수=값 ...
    // 객체 = {변수 : 값, 변수 : 값}
    const queryString = new URLSearchParams(params).toString()
    const response = await fetch("/api/v1/ai/ask?" + queryString)

    if(!response.ok){
      return

    }
  const result = await response.json()
  // 콜백함수가 있으면 마지막에 콜백 함수를 실행
  if(callbackFunc)
    callbackFunc(result)
  }catch(e){
    console.error(e)

  }
  
}

export {sendData}

  