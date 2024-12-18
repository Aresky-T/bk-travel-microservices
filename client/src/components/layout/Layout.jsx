import LayoutFooter from "../footer/Footer";
import { Outlet, useLocation } from "react-router-dom";
import MailContainer from "../../containers/mail/MailContainer";
import ChatContainer from "../../containers/chat/ChatContainer";
import LayoutHeader from "../../containers/layout/header";
import { useEffect, useRef, useState } from "react";

const Layout = () => {
  const [isScrolled, setIsScrolled] = useState(false);
  const layoutRef = useRef();
  const location = useLocation();

  useEffect(() => {
    const scrollTop = layoutRef.current ? layoutRef.current.scrollTop : 0;
    if (scrollTop > 0) {
      layoutRef.current.scrollTo(0, 0);
    }
  }, [location.pathname]);

  useEffect(() => {
    const layoutElement = layoutRef.current;
    const handleScroll = () => {
      const scrollTop = layoutElement.scrollTop;
      setIsScrolled(scrollTop > 0);
    };

    layoutElement.addEventListener("scroll", handleScroll);

    return () => {
      layoutElement.removeEventListener("scroll", handleScroll);
    };
  }, []);

  return (
    <div className={`layout${isScrolled ? " scrolled" : ""}`} ref={layoutRef}>
      <LayoutHeader />
      <Outlet />
      <LayoutFooter />
      <MailContainer />
      <ChatContainer />
    </div>
  );
};

export default Layout;
