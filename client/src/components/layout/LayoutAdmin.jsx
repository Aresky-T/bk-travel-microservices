import SidebarContainer from "../../containers/admin/Bar/SidebarContainer";
import NavbarContainer from "../../containers/admin/Bar/NavbarContainer";
import { Outlet, useLocation } from "react-router-dom";
import { Helmet } from "react-helmet-async";
import { useEffect } from "react";
import { ToastContainer } from "react-toastify";

const LayoutAdmin = () => {
  const location = useLocation();

  useEffect(() => {
    location.state = "admin_location";
  }, [location]);

  return (
    <>
      <Helmet>
        <meta charSet="utf-8" />
        <title>BK Travel - Quản trị</title>
        <meta name="admin-page" content="BK travel application" />
      </Helmet>
      <div className="admin-container">
        <SidebarContainer />
        <NavbarContainer />
        <Outlet />
      </div>
      <ToastContainer
        theme="colored"
        position="top-center"
        autoClose={1500}
        draggable
        stacked
      />
    </>
  );
};

export default LayoutAdmin;
