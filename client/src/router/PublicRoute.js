import React from "react";
import { animateScroll } from "react-scroll";

export const handleScrollToTop = () => {
    const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
    if (scrollTop > 0) {
        animateScroll.scrollToTop({
            duration: 300
        });
    }
}

const PublicRoute = (props) => {
    return (
        <>
            {props.children}
        </>
    )
}

export default PublicRoute;