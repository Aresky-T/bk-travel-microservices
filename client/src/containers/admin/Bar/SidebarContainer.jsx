import React from "react";
import { useLocation } from "react-router-dom";
// import ManageAccountsIcon from "@mui/icons-material/ManageAccounts";
import { ROUTE } from "../../../constant/route";
import { useDispatch } from "react-redux";
import { logout } from "../../../redux/slices/auth.slice";
import Sidebar from "../../../components/admin/Bar/Sidebar";
import { removeProfile } from "../../../redux/slices/profile.slice";
import { FaStaffSnake, FaCartFlatbedSuitcase } from "react-icons/fa6";
import { MdTour } from "react-icons/md";
import { GiDetour } from "react-icons/gi";
import { MdSupervisorAccount } from "react-icons/md";

const SidebarContainer = () => {
  const location = useLocation();
  const dispatch = useDispatch();

  const adminLinks = [
    {
      name: "Quản lý Tour",
      path: ROUTE.ADMIN_TOUR_MANAGER,
      icon: <GiDetour />,
    },
    {
      name: "Địa điểm du lịch",
      path: ROUTE.ADMIN_TOURIST_ATTRACTION_MANAGER,
      icon: <MdTour />,
    },
    {
      name: "Quản lý nhân sự",
      path: ROUTE.ADMIN_STAFF_MANAGER,
      icon: <FaStaffSnake />,
    },
    {
      name: "Quản lý Booking",
      path: ROUTE.ADMIN_BOOKING_MANAGER,
      icon: <FaCartFlatbedSuitcase />,
    },
    {
      name: "Quản lý Tài khoản",
      path: ROUTE.ADMIN_ACCOUNT_MANAGER,
      icon: <MdSupervisorAccount />,
    },
  ];

  function handleLogout() {
    dispatch(removeProfile());
    dispatch(logout());
  }

  return (
    <div className="sidebar-container">
      <Sidebar
        adminLinks={adminLinks}
        handleLogout={handleLogout}
        location={location}
      />
    </div>
  );
};

export default SidebarContainer;
