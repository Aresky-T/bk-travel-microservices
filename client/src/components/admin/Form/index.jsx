import React, { useMemo } from "react";
import { IoClose } from "react-icons/io5";

const AdminFormWrapper = ({
  children,
  active,
  position,
  title,
  showCloseButton,
  onClose,
}) => {
  const mainClass = useMemo(() => {
    let value = "";

    if (active) {
      value += " active";
    }

    switch (position) {
      case "left":
        value += " left";
        break;
      case "center":
        value += " center";
        break;
      case "right":
        value += " right";
        break;
      default:
    }

    return value;
  }, [active, position]);

  return (
    <div className={`admin-form-wrapper${mainClass}`}>
      <div className="admin-form scroll-container">
        {showCloseButton && (
          <div className="admin-form--close-btn">
            <button onClick={onClose}>
              <IoClose />
            </button>
          </div>
        )}
        {title && <div className="admin-form--title">{title}</div>}
        {children}
      </div>
    </div>
  );
};

export default AdminFormWrapper;
