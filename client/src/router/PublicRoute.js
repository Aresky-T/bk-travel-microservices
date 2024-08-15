import { useEffect } from "react";
import { animateScroll } from "react-scroll";
import { useLocation } from "react-router-dom";

export const handleScrollToTop = () => {
    const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
    if (scrollTop > 0) {
        animateScroll.scrollToTop({
            duration: 300
        });
    }
}

const PublicRoute = (props) => {
    const location = useLocation();

    useEffect(() => {
        handleScrollToTop()
    }, [location.pathname])

    return (
        <>
            {props.children}
        </>
    )
}

export default PublicRoute;