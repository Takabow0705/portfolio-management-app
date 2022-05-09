import React, {useEffect, useState} from 'react';
import {LoginClient} from '../../utils/loginClient'
import {Navigate} from "react-router";


export const LoginPage = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [redirect, setRedirect] = useState(false);

    useEffect(() => {
        let result = false;
        (
            async () => {
                try {
                    result = await checkSession();
                    console.log("Home component authorized check => " + result)
                    setRedirect(result)
                } catch (e) {
                    setRedirect(false)
                }
            }
        )()
    }, [])

    const submit = async (event) => {
        // formのデフォルト挙動をキャンセル
        event.preventDefault()
        if (username === null || password === null || username === "" || password === ""){
            return;
        }
        const client = new LoginClient()
        const response = await client.sendLoginRequest(username, password)
            .then(s => setRedirect(true))
            .catch(console.error)
            .catch(s => setRedirect(false))
    }

    if (redirect){
        console.log("login component flg = "+redirect)
        return <Navigate to="/app/home" />
    }

    return (
        <div>
            <h3 className="text-center text-white pt-5">Login Form</h3>
            <div className="container">
                <div id="login-row" className="row justify-content-center align-items-center">
                    <div id="login-column" className="col-md-6">
                        <div id="login-box" className="col-md-12">
                            <form id="login-form" className="form" onSubmit={submit}>
                                <h3 className="text-center text-info">Login</h3>
                                <div className="form-group">
                                    <label htmlFor="username" className="text-info">Username:</label><br/>
                                    <input type="text" className="form-control"
                                           onChange={e => setUsername(e.target.value)}/>
                                </div>
                                <div className="form-group">
                                    <label htmlFor="password" className="text-info">Password:</label><br/>
                                    <input type="password" className="form-control"
                                           onChange={e => setPassword(e.target.value)}/>
                                </div>
                                <div className="form-group">
                                    <input type="submit" name="submit" className="btn btn-info btn-md" value="submit"/>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
};