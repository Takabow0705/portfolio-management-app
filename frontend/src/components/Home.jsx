import React, {useEffect, useState} from 'react';
import {checkSession} from "../utils/checkSession";
import {Redirect} from "react-router";

export const Home = () => {
    let authorized = false;

    useEffect(() => {
        (
            async () => {
                try {
                    const result = await checkSession();
                    authorized = result;
                    console.log("Home component authorized check => " + result)
                } catch (e) {
                    authorized = false;
                } finally {
                    if (!authorized) {
                        return <Redirect from="/home" to="/login"/>
                    }
                }
            }
        )()
    }, [])

    return (
        <h1>Application Home!</h1>
    )
}