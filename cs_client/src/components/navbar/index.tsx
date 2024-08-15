import { useState } from "react";
import UserImg from "../../imgs/other/avatar.jpg";
import { useAppContext } from "../../store/context";
import Box from "../styled/box";
import Avatar from "../customer/Avatar";
import DropdownAvatar from "./DropdownAvatar";

interface PropsType {
  renderLinks: () => JSX.Element[];
}

const Navbar: React.FC<PropsType> = ({ renderLinks }) => {
  const { state } = useAppContext();
  const { profile, chat, auth } = state;
  const staff = chat.staff;
  const isConnected: boolean = staff != null && staff.status === "ONLINE";
  const [isShowDropdown, setIsShowDropdown] = useState(false);

  return (
    <div className="navbar-container">
      <div className="navbar_item left">
        <div className="logo flex-center">
          <div style={{ fontWeight: 600, fontSize: "1.2rem" }}>BK</div>
        </div>
      </div>
      <div className="navbar_item main">
        <ul className="menu">{renderLinks()}</ul>
      </div>
      <div className="navbar_item right">
        <div className="name_n_email">
          <div className="name">
            <strong>{profile.data?.fullName || "Not Found Name"}</strong>
          </div>
          <div className="email">{auth.data.email}</div>
        </div>
        <Box $position="relative">
          <Box
            $width={40}
            $height={40}
            $position={"relative"}
            $cursor="pointer"
            onClick={() => {
              setIsShowDropdown(!isShowDropdown);
            }}
          >
            <Avatar type="url" url={profile.data?.avatarUrl || UserImg} />
            <div className={`status${isConnected ? " onl" : ""}`}>
              {/* {isConnected ? (
              <span className="onl">Trực tuyến</span>
            ) : (
              <span>Không có kết nối</span>
            )} */}
            </div>
          </Box>
          <DropdownAvatar isShow={isShowDropdown} />
        </Box>
      </div>
    </div>
  );
};

export default Navbar;
