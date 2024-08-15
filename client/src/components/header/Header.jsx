import { useNavigate } from "react-router-dom";
import logo from "../../assets/logo/png/logo-no-background.png";
import Sidebar from "../sidebar/Sidebar";

const Header = ({
  isShowSidebar,
  isScrolled,
  publicLinks,
  handleChangeShowSidebar,
  renderCenterLinks,
  renderRightLinks,
}) => {
  const navigate = useNavigate();
  return (
    <div className={isScrolled ? "header main header-fixed" : "header main"}>
      <div
        className="header__item logo"
        onClick={() => {
          navigate("/");
        }}
      >
        <img src={logo} alt="logo" />
      </div>
      <div className="header__item center-links">{renderCenterLinks()}</div>
      <div className="header__item right-links">{renderRightLinks()}</div>
      <div
        className={isShowSidebar ? "menu-icon active" : "menu-icon"}
        onClick={handleChangeShowSidebar}
      >
        <div className="bar"></div>
        <div className="bar"></div>
        <div className="bar"></div>
      </div>
      {isShowSidebar && (
        <Sidebar
          handleChangeShowSidebar={handleChangeShowSidebar}
          publicLinks={publicLinks}
        />
      )}
    </div>
  );
};

export default Header;
