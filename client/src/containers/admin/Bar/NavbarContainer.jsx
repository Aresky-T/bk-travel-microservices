import Navbar from "../../../components/admin/Bar/Navbar";
import { useLocation } from "react-router-dom";
import { useEffect, useState } from "react";
import { ROUTE } from "../../../constant/route";

const NavbarContainer = () => {
  const location = useLocation();
  const [title, setTitle] = useState("");

  useEffect(() => {
    switch (location.pathname) {
      case ROUTE.ADMIN_ACCOUNT_MANAGER:
        setTitle("Quản lý Tài khoản");
        break;
      case ROUTE.ADMIN_BOOKING_MANAGER:
        setTitle("Quản lý Booking");
        break;
      case ROUTE.ADMIN_STAFF_MANAGER:
        setTitle("Quản lý nhân sự");
        break;
      case ROUTE.ADMIN_TOUR_MANAGER:
        setTitle("Quản lý Tour");
        break;
      case ROUTE.ADMIN_TOURIST_ATTRACTION_MANAGER:
        setTitle("Quản lý địa điểm du lịch");
        break;
      default:
        setTitle("");
        break;
    }
  }, [location.pathname]);

  return (
    <>
      <Navbar title={title} />
    </>
  );
};

export default NavbarContainer;
