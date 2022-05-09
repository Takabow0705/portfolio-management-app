import React, {useEffect, useState} from 'react';
import {Link} from "react-router-dom";

export const SideBarItem = ({id, menu, subMenus}) => {
    const [expanded, setExpanded] = useState(false);

    return (
        <>
            <a className={expanded ? "nav-link" : "nav-link collapsed"}
               href={"/#/app"}
               data-toggle={"collapse"}
               data-target={`#collapseLayouts_${id}`}
               aria-expanded={expanded}
               onClick={() => setExpanded(!expanded)}>
                {menu}
                <div className="sb-sidenav-collapse-arrow"><i className="fas fa-angle-down"></i></div>
            </a>
            <div className={expanded ? "collapse show" : "collapse"}
                 id={`collapseLayouts_${id}`}
                 aria-labelledby={"headingOne"}
            >
                <nav className={"sb-sidenav-menu-nested nav"}>
                    {subMenus.map(s => <Link className={"nav-link"} key={s.id} to={s.href}>{s.name}</Link>)}
                </nav>
            </div>
        </>
    )
}
