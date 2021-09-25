import ReactDOM from "react-dom";
import "./scss/index.scss";
import React from "react";
import {BrowserRouter, Route, Switch} from "react-router-dom";
import {LoginPage} from "./components/Login";
import {Home} from "./components/Home";
import axios from "axios";

axios.defaults.withCredentials = true;
// routing設定
const Main = () => {
    return (
        <BrowserRouter>
            <Route exact path="/" exact component={LoginPage} />
            <Route path="/login" ><LoginPage/></Route>
            <Route path="/home"><Home/></Route>
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