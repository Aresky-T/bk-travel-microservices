import React from "react";
import CustomPaginate from "../../pagination/CustomPaginate";
import { useNavigate } from "react-router-dom";
import { ROUTE } from "../../../constant/route";

const TourGuideManager = ({ data, handleChangeCurrentPage, paginate }) => {
  const navigate = useNavigate();

  return (
    <div className="admin-main tour-guide-manage-container">
      <section className="admin-main__header">
        <button
          className="admin-main__header__btn"
          onClick={() => {
            navigate(ROUTE.TOUR_GUIDE_CREATE);
          }}
        >
          + Thêm nhân viên
        </button>
      </section>
      <section className="admin-main__body">
        <table className="admin-main__body__table tour-guide-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Họ Tên</th>
              <th>Ngày sinh</th>
              <th>Số điện thoại</th>
              <th>Địa chỉ</th>
              <th>Trạng thái</th>
              <th>Hành động</th>
            </tr>
          </thead>
          <tbody>
            {data?.content?.map((tourGuide) => (
              <tr className="admin-table-row" key={tourGuide.id}>
                <td>{tourGuide.id}</td>
                <td>{tourGuide.fullName}</td>
                <td>
                  {new Date(tourGuide.birthDate).toLocaleDateString("vi-VN", {
                    dateStyle: "short",
                  })}
                </td>
                <td>{tourGuide.phone}</td>
                <td>{tourGuide.address}</td>
                <td>{tourGuide.status}</td>
                <td className="admin-table-data__action">
                  <button
                    onClick={() => {
                      navigate(`/admin/tour-guide/detail/${tourGuide.id}`);
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
          currentPage={paginate.pageNumber}
          totalPages={data.totalPages}
          setCurrentPage={handleChangeCurrentPage}
        />
      </section>
    </div>
  );
};

export default TourGuideManager;
