import Main from "./Main";
import { BrowserRouter, Routes, Route} from "react-router-dom";
import {useEffect, useState} from "react";
import List from "./List";
import Ask from "./Ask.jsx";
import Translate from "./Translate.jsx";
import AdCopy from "./AdCopy.jsx";
import Summarize from "./Summarize.jsx";


function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" exact element={<Main />} />
        <Route path="/list" exact element={<List />} />
        <Route path="/ask" exact element={<Ask />} />
        <Route path="/translate" exact element={<Translate />} />
        <Route path="/ad-copy" exact element={<AdCopy />} />
        <Route path="/summarize" exact element={<Summarize />} />
      </Routes>
		</BrowserRouter>
  );
}

export default App;
