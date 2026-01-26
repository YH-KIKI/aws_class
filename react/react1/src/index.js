import ReactDOM from 'react-dom/client';
import App from './App';

// id가 root인 요소를 찾아 리액트 DOM의 루트로 만듬
const root = ReactDOM.createRoot(document.getElementById('root'));
// 최상위 요소 안에 App 컴포넌트를 배치해서 렌더링해라
root.render(
    <App />
);


