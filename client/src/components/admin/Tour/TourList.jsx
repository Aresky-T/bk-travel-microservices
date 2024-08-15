import React from "react";
import CustomPaginate from "../../pagination/CustomPaginate";
import { Box } from "@mui/material";
import { useDispatch } from "react-redux";
import { onUpdateAdminSelectedTourId } from "../../../redux/slices/admin.slice";

const TourList = ({
  tours,
  currentPage,
  totalPages,
  handleChangeCurrentPage,
}) => {
  const dispatch = useDispatch();

  return (
    <Box className="admin-tours-list" width={"100%"}>
      <table className="admin-table tour-table">
        <thead>
          <tr>
            <th>STT</th>
            <th>Tiêu đề</th>
            <th>Tổng thời gian</th>
            <th>Địa điểm khởi hành</th>
            <th>Giá người lớn (VND)</th>
            <th>Hành động</th>
          </tr>
        </thead>
        <tbody>
          {[...tours].map((item, index) => (
            <tr key={index} className="admin-table-row">
              <td>{item.id}</td>
              <td>{item.title}</td>
              <td>{item.duration}</td>
              <td>{item.departureLocation}</td>
              <td>{Number(item.adultPrice).toLocaleString("vi-VN")}</td>
              <td className="admin-table-data__action">
                <button
                  onClick={() => {
                    dispatch(onUpdateAdminSelectedTourId(item.id));
                  }}
                >
                  Xem
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <CustomPaginate
        currentPage={currentPage}
        firstLabel="<"
        lastLabel=">"
        setCurrentPage={handleChangeCurrentPage}
        totalPages={totalPages}
      />
    </Box>
  );
};

export default TourList;
