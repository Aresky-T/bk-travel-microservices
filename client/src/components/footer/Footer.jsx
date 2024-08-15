import { Link } from "react-router-dom";
import { links } from "../../constant/links";
import { FiMapPin } from "react-icons/fi";
import { TbMailOpened } from "react-icons/tb";
import { FiFacebook } from "react-icons/fi";
import logo from "../../assets/logo/png/logo-no-background.png";

const LayoutFooter = () => {
  const mainLinks = links.filter((link) => link.align === "center");
  return (
    <div className="main footer">
      <div className="footer-item footer-logo">
        <img src={logo} alt="logo" />
        <span className="text-2">
          Luôn đồng hành cùng bạn trên những chuyến đi, ngắm nhìn vẻ đẹp bất tận
          của dải đất hình chữ S
        </span>
        <span className="text-3">© 2023 | All Rights Reserved</span>
      </div>
      <div className="footer-item discover">
        <h3>Khám phá</h3>
        {[...mainLinks].map((link) => {
          return (
            <Link to={link.path} key={link.name} className="text-2">
              {link.name}
            </Link>
          );
        })}
      </div>
      <div className="footer-item contact">
        <h3>Liên hệ</h3>
        <div className="contact-item address">
          <span className="contact-icon">
            <FiMapPin />
          </span>
          <span className="text-1">Địa chỉ: </span>
          <span className="text-2">Số 1 - Đại Cồ Việt, Hà Nội, Việt Nam</span>
        </div>
        <div className="contact-item email">
          <span className="contact-icon">
            <TbMailOpened />
          </span>
          <span className="text-1">Email: </span>
          <span className="text-2">gr1projectmailhust@gmail.com</span>
        </div>
        <div className="contact-item facebook">
          <span className="contact-icon">
            <FiFacebook />
          </span>
          <span className="text-1">Facebook: </span>
          <span className="text-2">
            <a href="https://www.facebook.com/profile.php?id=100078811796824">
              Anh Tuan
            </a>
          </span>
        </div>
      </div>
    </div>
  );
};

export default LayoutFooter;
