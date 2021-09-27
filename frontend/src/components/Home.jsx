import React, {createContext, useEffect, useState} from 'react';
import {checkSession} from "../utils/checkSession";
import {Redirect} from "react-router";
import {Header} from "./Header";
import {PageBody} from "./PageBody";
import sideBar from "../config/sidebar-config.json";

export const SideBarContext = createContext();
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
        <SideBarContext.Provider value={{sideBar}}>
        <div className={"sb-nav-fixed"}>
            <Header>Header</Header>
            <PageBody></PageBody>
        </div>
        </SideBarContext.Provider>
    )
}