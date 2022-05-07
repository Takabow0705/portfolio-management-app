import ReactDOM from "react-dom";
import "./scss/styles.scss";
import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {LoginPage} from "./components/Login";
import {Layout} from "./components/Layout";
import {NoMatch} from "./components/NoMatch";
import {Home} from "./components/Home";
import axios from "axios";
import { RegisterExecution } from "./components/RegisterExecution";


axios.defaults.withCredentials = true;
// routing設定
const Main = () => {
    return (
        <BrowserRouter>
        <Routes>
            <Route path="/login" element = {<LoginPage />}></Route>
            <Route path="/app" element={<Layout />}>
                <Route path="/app/home" element={<Home />} />
                <Route path="/app/registerExecution" element={<RegisterExecution />} />
                <Route path="/app/*" element={<NoMatch />}></Route>
            </Route>
            <Route path="*" element={<NoMatch />}></Route>
        </Routes>
        </BrowserRouter>
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