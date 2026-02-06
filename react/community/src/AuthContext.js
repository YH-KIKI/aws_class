import { createContext, useContext, useEffect, useState } from "react";
import { getMe } from "./api/authFetch";

//컴포넌트 안에서 전역으로 사용할 수 있는 통로를 생성
const AuthContext = createContext(null);

//여기서 children은 props가 아닌 자식 컴포넌트
export function AuthProvider({children}){

	const [user, setUser] = useState(null);
	
	const getMeAndSetUser = async () =>{

		try{
			const res = await getMe();
			if(res){
				setUser(res);
			}
			console.log(res)
		}catch(e){
			console.error(e);
			setUser(null);
		}
	}
	useEffect(()=>{
		getMeAndSetUser();

	}, []);


	return (
		<AuthContext.Provider value={{user, setUser}} >
			{children}
		</AuthContext.Provider>
	)
}
//가독성, import 문제
export const useAuth = ()=>useContext(AuthContext);