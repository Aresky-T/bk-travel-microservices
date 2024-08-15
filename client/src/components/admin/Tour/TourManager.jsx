import React from "react";
import { Box } from "@mui/material";
import { useAdmin } from "../../../redux/selector";
import TourList from "./TourList";
import TourListFilter from "./TourListFilter";

const TourManager = ({ handleShowCreateTour, handleChangeCurrentPage }) => {
  const { tourManager } = useAdmin();
  const { tourList, pageNumber, totalPages, totalElements } = tourManager;

  const isNotEmptyTourList = totalElements > 0;

  return (
    <Box className="administrator-manager">
      <section className="administrator-manager_header">
        <div className="administrator-manager__title">Danh sách Tour</div>
        <div className="administrator-manager__menu">
          <div className="administrator-manager__menu-item tours active">
            Tour
          </div>
        </div>
      </section>
      <Box
        className="administrator-manager_main"
        sx={{
          display: "flex",
          flexDirection: "column",
          justifyContent: "center",
          gap: "20px",
        }}
      >
        <TourListFilter handleShowCreateTour={handleShowCreateTour} />
        <Box
          sx={{
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
            width: "100%",
            height: "100%",
          }}
        >
          {isNotEmptyTourList ? (
            <TourList
              tours={tourList}
              currentPage={pageNumber}
              totalPages={totalPages}
              handleChangeCurrentPage={handleChangeCurrentPage}
            />
          ) : (
            <Box
              sx={{
                fontSize: "1.4rem",
                color: "var(--gray-color)",
              }}
            >
              Danh sách trống!
            </Box>
          )}
        </Box>
      </Box>
    </Box>
  );
};

export default TourManager;
