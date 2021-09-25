import React, {useEffect, useState} from 'react';

export const SideBar = () => {
    return (
        <div id={"layoutSidenav_nav"}>
            <nav className={"sb-sidenav accordion sb-sidenav-dark"} id={"sidenavAccordion"}>
                <div className={"sb-sidenav-menu"}></div>
                <div className={"nav"}></div>
                <div className={"sb-sidenav-footer"}>
                    <div className={"small"}>Logged in as:</div>
                    Start Bootstrap
                </div>
            </nav>
        </div>
    )
}