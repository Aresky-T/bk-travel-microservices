import { Link, useLocation } from "react-router-dom";
import { useDispatch } from "react-redux";
import { useCallback, useEffect, useState } from "react";
import { ROLE } from "../../../constant/role";
import { LINK_POSITIONS, links } from "../../../constant/links";
import Header from "../../../components/header/Header";
import { useAuth } from "../../../redux/selector";

const LayoutHeader = () => {
  const auth = useAuth();
  const { accessToken, role } = auth;
  const location = useLocation();
  const dispatch = useDispatch();
  const [isShowSidebar, setIsShowSidebar] = useState(false);
  const [isScrolled, setIsScrolled] = useState(false);
  const [publicLinks, setPublicLinks] = useState([]);

  function handleChangeShowSidebar() {
    setIsShowSidebar(!isShowSidebar);
  }

  const onFilterLinks = useCallback(
    (links) => {
      const isLoggedIn = accessToken && role === ROLE.USER;
      const publicLinks = links
        .map((link) => {
          if (link.requireLogin) {
            link.isPublic = isLoggedIn;
            return link;
          }

          if (link.title === "login" || link.title === "register") {
            link.isPublic = !isLoggedIn;
            return link;
          }

          return link;
        })
        .filter((link) => link.isPublic);

      setPublicLinks(publicLinks);
    },
    [accessToken, role]
  );

  const renderLinks = (links) => {
    return links.map((link) => {
      const current = location.pathname === link.path ? "active" : "";
      if (link.path) {
        return (
          <Link
            key={link.name}
            to={link.path}
            className={`link-item ${link.align} ${current} ${link.class}`}
          >
            {link.name}
          </Link>
        );
      }

      if (link.showIcon) {
        return (
          <div
            key={link.name}
            className={`link-item link-icon ${link.class}`}
            onClick={link.action}
          >
            {<link.icon />}
            {<link.dropdownComponent />}
          </div>
        );
      }

      return (
        <button
          key={link.name}
          className={`link-item link-btn ${link.class}`}
          onClick={() => {
            link.action(dispatch);
          }}
        >
          {link.name}
        </button>
      );
    });
  };

  const renderCenterLinks = () => {
    const centerLinks = publicLinks.filter(
      (link) => link.align === LINK_POSITIONS.CENTER
    );
    return renderLinks(centerLinks);
  };

  const renderRightLinks = () => {
    const rightLinks = publicLinks.filter(
      (link) => link.align === LINK_POSITIONS.RIGHT
    );
    return renderLinks(rightLinks);
  };

  useEffect(() => {
    onFilterLinks(links);
  }, [onFilterLinks]);

  useEffect(() => {
    const handleScroll = () => {
      const scrollTop =
        window.pageYOffset || document.documentElement.scrollTop;
      setIsScrolled(scrollTop > 0);
    };

    window.addEventListener("scroll", handleScroll);

    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  }, []);

  return (
    <Header
      isScrolled={isScrolled}
      isShowSidebar={isShowSidebar}
      publicLinks={publicLinks}
      handleChangeShowSidebar={handleChangeShowSidebar}
      renderCenterLinks={renderCenterLinks}
      renderRightLinks={renderRightLinks}
    />
  );
};

export default LayoutHeader;
