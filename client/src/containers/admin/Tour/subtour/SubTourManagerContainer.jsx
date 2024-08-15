import React, { useCallback, useEffect, useState } from "react";
import Button from "@mui/material/Button";
import SubTourFormContainer from "./SubTourFormContainer";
import {
  deleteSubTourByIdApi,
  getAllSubToursForAdminApi,
} from "../../../../api/admin/tour.api";
import SubTourDetailsCard from "../../../../components/admin/Tour/subtour/SubTourDetailsCard";
import { useAdmin } from "../../../../redux/selector";
import {
  errorAlert,
  successAlert,
  warningAlert,
} from "../../../../config/sweetAlertConfig";

const SubTourManagerContainer = () => {
  const [subTours, setSubTours] = useState([]);
  const [selectedSubTour, setSelectedSubTour] = useState(null);
  const [isShowForm, setIsShowForm] = useState(false);
  const [isRefetch, setIsRefetch] = useState(false);

  const { tourManager } = useAdmin();
  const tourId = tourManager.selectedTourId;

  const handleShowForm = () => {
    !isShowForm && setIsShowForm(true);
  };

  const handleHiddenForm = () => {
    setSelectedSubTour(null);
    isShowForm && setIsShowForm(false);
  };

  const handleRefetchSubTours = () => setIsRefetch(true);

  const handleSelectSubTour = (subTour) => {
    if (subTour) {
      setIsShowForm(true);
      setSelectedSubTour(subTour);
    }
  };

  const handleDeleteSubTour = async (subTour) => {
    if (subTour) {
      try {
        const selectedBtn = await warningAlert(
          "Cảnh báo",
          "Bạn có chắc chắn muốn xoá Tour phụ này?",
          {
            confirmButtonText: "Xác nhận xoá",
            cancelButtonText: "Huỷ bỏ",
          }
        );

        if (!selectedBtn.isConfirmed) {
          return;
        }

        const res = await deleteSubTourByIdApi(subTour.id);

        if (res) {
          handleRefetchSubTours();
          successAlert("Xoá thành công!", null, "OK");
        }
      } catch (error) {
        errorAlert("Xoá thất bại!", null, "Quay lại");
      }
    }
  };

  const renderSubTours = useCallback(() => {
    if (subTours.length === 0) {
      return (
        <div className="sub-tour-list--admin--empty">Danh sách trống!</div>
      );
    }

    return subTours.map((item, index) => (
      <SubTourDetailsCard
        subTour={item}
        key={index}
        onSelectSubTour={handleSelectSubTour}
        onDeleteSubTour={handleDeleteSubTour}
      />
    ));
    //eslint-disable-next-line
  }, [subTours]);

  const fetchSubTours = useCallback(() => {
    if (tourId) {
      getAllSubToursForAdminApi(tourId)
        .then((res) => {
          setSubTours(res.data);
          if (isRefetch) {
            setIsRefetch(false);
          }
        })
        .catch((err) => {});
    }
  }, [tourId, isRefetch]);

  useEffect(() => {
    fetchSubTours();
  }, [fetchSubTours]);

  return (
    <div className="sub-tour-manager-container">
      <h2 className="create-sub-tour-title">Danh sách Tour phụ</h2>
      <Button
        type="button"
        className="admin-btn admin-btn--submit"
        onClick={handleShowForm}
      >
        + Tạo mới tour phụ
      </Button>
      <div className="sub-tour-list--admin">{renderSubTours()}</div>
      <SubTourFormContainer
        subTour={selectedSubTour}
        isShowForm={isShowForm}
        handleHiddenForm={handleHiddenForm}
        handleRefetchSubTours={handleRefetchSubTours}
      />
    </div>
  );
};

export default SubTourManagerContainer;
