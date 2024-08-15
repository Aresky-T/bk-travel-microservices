import React from "react";
import { Box, Button } from "@mui/material";
import CustomPaginate from "../../pagination/CustomPaginate";
import {
  GENDER_TRANSLATIONS,
  STAFF_STATUS_TRANSLATIONS,
} from "../../../constant/translations";
import { onUpdateAdminCurrentStaff } from "../../../redux/slices/admin.slice";

const StaffList = ({ data, page, totalPages, dispatch, onChangePage }) => {
  const renderGender = (gender) => {
    return GENDER_TRANSLATIONS[gender] || "Không xác định";
  };

  const renderStatus = (status) => {
    return STAFF_STATUS_TRANSLATIONS[status] || "Không xác định";
  };

  return (
    <div className="staff-list">
      <table className="admin-table __staffs">
        <thead>
          <tr>
            <th className="admin-table-row_th __id">STT</th>
            <th className="admin-table-row_th __first-name">Tên</th>
            <th className="admin-table-row_th __last-name">Họ</th>
            <th className="admin-table-row_th __gender">Giới tính</th>
            <th className="admin-table-row_th __email">Email</th>
            <th className="admin-table-row_th __phone">Điện thoại</th>
            <th className="admin-table-row_th __status">Trạng thái</th>
            <th className="admin-table-row_th __position">Chức vụ</th>
            <th className="admin-table-row_th __action">Hành động</th>
          </tr>
        </thead>
        <tbody>
          {[...data].map((staff, index) => (
            <tr key={index} className="admin-table-row">
              <td className="admin-table-row_item __id">{index + 1}</td>
              <td className="admin-table-row_item __first-name">
                {staff.firstName}
              </td>
              <td className="admin-table-row_item __last-name">
                {staff.lastName}
              </td>
              <td className="admin-table-row_item __gender">
                {renderGender(staff.gender)}
              </td>
              <td className="admin-table-row_item __email">{staff.email}</td>
              <td className="admin-table-row_item __phone">{staff.phone}</td>
              <td className="admin-table-row_item __status">
                {renderStatus(staff.status)}
              </td>
              <td className="admin-table-row_item __position">
                {staff.position || "Trống"}
              </td>
              <td className="admin-table-row_item __action">
                <Box
                  sx={{
                    width: "fit-content",
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center",
                    gap: "15px",
                  }}
                >
                  <Button
                    className="admin-btn--submit"
                    onClick={() => dispatch(onUpdateAdminCurrentStaff(staff))}
                  >
                    Cập nhật
                  </Button>
                  <Button className="admin-btn--standard">Công việc</Button>
                </Box>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <CustomPaginate
        currentPage={page}
        totalPages={totalPages}
        setCurrentPage={onChangePage}
      />
    </div>
  );
};

export default StaffList;
