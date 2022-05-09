import React, {createContext, useEffect, useState} from 'react';
import {checkSession} from "../utils/checkSession";
import {Navigate, Outlet} from "react-router";
import {Header} from "./Header";
import sideBar from "../config/sidebar-config.json";
import { SideBar } from './SideBar';

export const SideBarContext = createContext();
export const Layout = () => {
	const [authorized, setAuthorized] = useState(true)

    useEffect(() => {
        let result = false;
        (
            async () => {
                try {
                    result = await checkSession();
                    console.log("Home component authorized check => " + result)
                } catch (e) {
                    setAuthorized(false)
                }
            }
        )()
    }, [])

    if (!authorized) {
        console.log("redirect to login page")
        return <Navigate  to="/"/>
    }

    return (
        <SideBarContext.Provider value={{sideBar}}>
        <div className={"sb-nav-fixed"}>
            <Header>Header</Header>
            <div id={"layoutSidenav"}>
                <SideBar></SideBar>
                <div id={"layoutSidenav_content"}><Outlet /></div>
            </div>
        </div>
        </SideBarContext.Provider>
    )
}