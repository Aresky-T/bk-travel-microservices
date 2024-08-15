import { Link } from "react-router-dom";
// import LogoutIcon from "@mui/icons-material/Logout";
import { IoMdLogOut } from "react-icons/io";
import React from "react";

const Sidebar = ({ adminLinks, location, handleLogout }) => {
  return (
    <>
      <div className="logo">
        <Link to={"/admin"}>BK Travel Admin</Link>
      </div>
      <div className="sidebar-links">
        {adminLinks.map((link) => {
          return (
            <Link
              to={link.path}
              key={link.name}
              className={
                location.pathname === link.path
                  ? "sidebar-links-item active"
                  : "sidebar-links-item"
              }
            >
              <span className="link-content">
                <span className="link-icon">{link.icon}</span>
                <span className="link-name">{link.name}</span>
              </span>
            </Link>
          );
        })}
      </div>
      <div className="dividing-line"></div>
      <div className="logout" onClick={handleLogout}>
        <span>Đăng xuất</span>
        <IoMdLogOut />
      </div>
    </>
  );
};

export default Sidebar;
