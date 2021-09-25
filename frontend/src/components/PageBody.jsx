import {SideBar} from "./SideBar";
import {MainPanel} from "./MainPanel";
import React, {useEffect, useState} from 'react';

export const PageBody = () => {
    return(
    <div id={"layoutSidenav"}>
        <SideBar></SideBar>
        <MainPanel></MainPanel>
    </div>
    )
}