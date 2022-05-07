import React, {useEffect, useState} from 'react';
import {Link} from "react-router-dom";
export const Header = () => {
    return (
        <nav className={"sb-topnav navbar navbar-expand navbar-dark bg-dark"}>
            <Link to={"/app/home"} className={"navbar-brand ps-3"}>Application Home</Link>
        </nav>
    )
}