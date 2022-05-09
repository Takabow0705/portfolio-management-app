import ReactDOM from "react-dom";
import "./scss/styles.scss";
import React from "react";
import {HashRouter, Route, Routes} from "react-router-dom";
import {LoginPage} from "./pages/login/Login";
import {Layout} from "./components/Layout";
import {NoMatch} from "./pages/other/NoMatch";
import {Home} from "./pages/home/Home";
import axios from "axios";
import { RegisterExecution } from "./pages/execution/RegisterExecution";


axios.defaults.withCredentials = true;
// routing設定
const Main = () => {
    return (
        <HashRouter>
        <Routes>
            <Route path="/" element = {<LoginPage />}></Route>
            <Route path="/app" element={<Layout />}>
                <Route path="/app/home" element={<Home />} />
                <Route path="/app/registerExecution" element={<RegisterExecution />} />
                <Route path="/app/*" element={<NoMatch />}></Route>
            </Route>
            <Route path="*" element={<NoMatch />}></Route>
        </Routes>
        </HashRouter>
    )
};

// アプリ本体
const App = () => {
    return (
        <React.StrictMode>
            <Main/>
        </React.StrictMode>
    )
};

ReactDOM.render(<App/>, document.getElementById("root"));