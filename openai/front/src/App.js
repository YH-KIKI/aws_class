import Main from "./Main";
import { BrowserRouter, Routes, Route} from "react-router-dom";
import {useEffect, useState} from "react";
import List from "./List";
import Ask from "./Ask.jsx";


function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" exact element={<Main />} />
        <Route path="/list" exact element={<List />} />
        <Route path="/ask" exact element={<Ask />} />
      </Routes>
		</BrowserRouter>
  );
}

export default App;
