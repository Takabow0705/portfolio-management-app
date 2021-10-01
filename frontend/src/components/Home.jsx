import React, {createContext, useEffect, useState} from 'react';
import {checkSession} from "../utils/checkSession";
import {Redirect} from "react-router";
import {Header} from "./Header";
import {PageBody} from "./PageBody";
import sideBar from "../config/sidebar-config.json";

export const SideBarContext = createContext();
export const Home = () => {

    useEffect(() => {
        let authorized = false;
        (
            async () => {
                try {
                    authorized= await checkSession();
                    console.log("Home component authorized check => " + authorized)
                } catch (e) {
                    authorized = false;
                }
                if (!authorized) {
                    return <Redirect from="/home" to="/"/>
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