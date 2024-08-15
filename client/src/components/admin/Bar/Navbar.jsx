import React from "react";
// import NotificationsNoneIcon from "@mui/icons-material/NotificationsNone";
// import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import { RiNotification2Fill, RiAdminFill } from "react-icons/ri";
import { Link } from "react-router-dom";
import { IoIosArrowForward } from "react-icons/io";
import { ROUTE } from "../../../constant/route";

const Navbar = ({ title }) => {
  return (
    <div className="navbar-admin">
      <div className="title">
        <span>
          <Link to={ROUTE.ADMIN_HOME}>Home</Link>
        </span>
        <span>
          <IoIosArrowForward />
        </span>
        <span>{title}</span>
      </div>
      <div className="notification">
        <span className="notification-icon">
          <RiNotification2Fill />
        </span>
        <span className="notification-number">4</span>
      </div>
      <div className="admin-wrapper">
        <div className="admin">
          {/* <img
            src="https://www.lansweeper.com/wp-content/uploads/2018/05/ASSET-USER-ADMIN.png"
            alt=""
          /> */}
          <RiAdminFill />
          <span>
            <b>Quản trị viên</b>
          </span>
          {/* <span>
            <KeyboardArrowDownIcon />
          </span> */}
        </div>
      </div>
    </div>
  );
};

export default Navbar;
