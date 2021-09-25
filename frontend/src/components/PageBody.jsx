import {SideBar} from "./SideBar";
import {MainPanel} from "./MainPanel";
import React, {useEffect, useState} from 'react';

export const PageBody = () => {
    return (
        <div className={"container-fluid page-body-wrapper"}>
            <SideBar></SideBar>
            <MainPanel></MainPanel>
        </div>
    )
}