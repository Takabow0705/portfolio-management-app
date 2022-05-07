import React, {useContext, useEffect, useState} from 'react';
import {SideBarItem} from "./SideBarItem";
import {SideBarContext} from "./Layout";

export const SideBar = () => {
    const {sideBar} = useContext(SideBarContext);

    return (
        <div id={"layoutSidenav_nav"}>
            <nav className={"sb-sidenav accordion sb-sidenav-dark"} id={"sidenavAccordion"}>
                <div className={"sb-sidenav-menu"}>
                    <div className={"nav"}>
                        {sideBar.map(menu => <SideBarItem key={menu.id} {...menu}/>)}
                    </div>
                </div>
                <div className={"sb-sidenav-footer"}>
                    <div className={"small"}>Logged in as:</div>
                    Start Bootstrap
                </div>
            </nav>
        </div>
    )
}