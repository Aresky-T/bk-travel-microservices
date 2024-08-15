import React, { useState } from "react";
import DepartmentManagerContainer from "./Department";
import StaffsListContainer from "./StaffsListContainer";

const menu = [
  {
    id: 1,
    name: "Nhân viên",
    title: "Nhân viên",
    element: <StaffsListContainer />,
  },
  {
    id: 2,
    name: "Phòng ban",
    title: "Quản lý phòng ban",
    element: <DepartmentManagerContainer />,
  },
];

const StaffManagerContainer = () => {
  const [selectedMenuItem, setSelectedMenuItem] = useState(menu[0]);

  const onSelectMenuItem = (item) => {
    setSelectedMenuItem(item);
  };

  const renderMenuItems = () => {
    return [...menu].map((item) => {
      const isSelected = item.id === selectedMenuItem.id;

      return (
        <div
          className={`administrator-manager__menu-item employee ${
            isSelected ? "active" : ""
          }`}
          key={item.id}
          onClick={() => onSelectMenuItem(item)}
        >
          {item.name}
        </div>
      );
    });
  };

  const renderStaffManagerMain = () => {
    return [...menu]
      .filter((item) => item.id === selectedMenuItem.id)
      .map((item) => (
        <React.Fragment key={item.id}>{item.element}</React.Fragment>
      ));
  };

  return (
    <div className="administrator-manager-container">
      <div className="administrator-manager">
        <div className="administrator-manager_header">
          <div className="administrator-manager__title">
            {selectedMenuItem.title}
          </div>
          <div className="administrator-manager__menu">{renderMenuItems()}</div>
        </div>
        {renderStaffManagerMain()}
      </div>
    </div>
  );
};

export default StaffManagerContainer;
