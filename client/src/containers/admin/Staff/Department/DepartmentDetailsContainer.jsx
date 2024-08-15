import React, { useEffect, useState } from "react";
import DepartmentDetails from "../../../../components/admin/Staff/DepartmentDetails";
import PositionList from "../../../../components/admin/Staff/PositionList";
import { Box, Button } from "@mui/material";
import { getDetailsDepartmentApi } from "../../../../api/admin/staff.api";

const DepartmentDetailsContainer = ({ department, handleReturn }) => {
  const [positions, setPositions] = useState([]);
  const [staffs, setStaffs] = useState([]);

  // const getAllPositions = async (departmentId) => {
  //   try {
  //     const response = await getAllPositionsByDepartmentIdApi(departmentId);
  //     console.log("positions: ", response.data);
  //     setPositions(response.data);
  //   } catch (error) {
  //     console.log(error);
  //   }
  // };

  // const getAllStaffs = async (departmentId) => {
  //   try {
  //     const response = await getAllStaffsByDepartmentId(departmentId);
  //     console.log("staffs: ", response.data);
  //     setStaffs(response.data);
  //   } catch (error) {
  //     console.log(error);
  //   }
  // };

  const renderStaffGender = (gender) => {
    switch (gender) {
      case "MALE":
        return "Nam";
      case "FEMALE":
        return "Nữ";
      case "OTHER":
        return "Khác";
      default:
        return "Không xác định";
    }
  };

  useEffect(() => {
    if (department && department.id) {
      getDetailsDepartmentApi(department.id).then((res) => {
        console.log(res.data);
        setStaffs(res.data.staffs);
        setPositions(res.data.positions);
      });
    }
  }, [department]);

  return (
    <div className="department-details-container">
      <Box
        className="department-details_actions"
        display={"flex"}
        justifyContent={"space-between"}
        alignItems={"center"}
      >
        <Box>
          <Button onClick={handleReturn} className="admin-btn">
            Quay lại
          </Button>
        </Box>
        <Box>
          <Button className="admin-btn admin-btn--submit">
            + Thêm Chức Vụ
          </Button>
          <Button className="admin-btn admin-btn--delete">Xoá Phòng Ban</Button>
        </Box>
      </Box>
      <DepartmentDetails department={department} />
      <PositionList positions={positions} />
      <div className="department-details__staff-list">
        <table className="admin-table">
          <thead>
            <tr>
              <th className="department-details__staff-row __id">STT</th>
              <th className="department-details__staff-row __name">
                Nhân viên
              </th>
              <th className="department-details__staff-row __birth-date">
                Ngày sinh
              </th>
              <th className="department-details__staff-row __gender">
                Giới tính
              </th>
              <th className="department-details__staff-row __email">Email</th>
              <th className="department-details__staff-row __phone">Phone</th>
              <th className="department-details__staff-row __position">
                Position
              </th>
              <th className="department-details__staff-row __status">Status</th>
            </tr>
          </thead>
          <tbody>
            {staffs.map((staff, index) => (
              <tr key={index}>
                <td>{index + 1}</td>
                <td>{staff.lastName + " " + staff.firstName}</td>
                <td>{new Date(staff.dateOfBirth).toLocaleDateString()}</td>
                <td>{renderStaffGender(staff.gender)}</td>
                <td>{staff.email}</td>
                <td>{staff.phone}</td>
                <td>{staff.position}</td>
                <td>{staff.status}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default DepartmentDetailsContainer;
