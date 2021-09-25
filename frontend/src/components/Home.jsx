import React, {useEffect, useState} from 'react';
import {checkSession} from "../utils/checkSession";
import {Redirect} from "react-router";
import {Header} from "./Header";
import {PageBody} from "./PageBody";

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
        <div className={"container-scroller"}>
            <Header>Header</Header>
            <PageBody></PageBody>
        </div>
    )
}