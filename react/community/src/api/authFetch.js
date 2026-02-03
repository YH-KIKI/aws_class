
//인증 정보를 header에 자동으로 추가하는 함수
export async function authFetch(url, options = {}) {
	
	const token = localStorage.getItem("accessToken");

	const headers = {
		//쿠키를 보냄. 리액트 => 부트로. 리프레쉬 토큰 때문에
		credentials : "include",
		"Content-type" : "application/json",
		...(options.headers || {})
	};
	//token이 있으면 
	if(token){
		headers.Authorization = `Bearer ${token}`
	}

	const response = await fetch(url, {
		...options, 
		headers
	});

	if(response.ok){
		return response;
	}


//리프레쉬 토큰으로 새 토큰을 가져오는 작업
	const refresh = await fetch("/api/v1/auth/refresh", {
		method : "POST",
		credentials : "include",
	});

 if (!refresh.ok) throw new Error("로그인 만료");
		//새 토큰을 가져옴 
    const data = await refresh.json();
		//새 토큰을 로컬 스토리지에 저장
    localStorage.setItem("accessToken", data.accessToken);

    return authFetch(url, options);

}



//토큰을 이용하여 회원 정보 가져오기
export async function getMe() {

	const res = await authFetch("/api/v1/auth/me");

	if(!res.ok){
		throw new Error("인증 실패")
	}
	return res.json();
}