import React from "react";
import SearchIcon from "@mui/icons-material/Search";
import { Box, Button, InputAdornment, TextField } from "@mui/material";

const TourListFilter = ({ handleShowCreateTour }) => {
  return (
    <Box
      className="administrator-manager__actions"
      sx={{
        height: "80px",
        display: "flex",
        alignItems: "center",
        justifyContent: "space-between",
        marginTop: "15px",
      }}
    >
      <Box
        className="administrator-manager__actions--left"
        display={"flex"}
        gap={"15px"}
        width={"40%"}
      >
        <TextField
          type="text"
          name="name"
          label="Tìm kiếm Tour"
          placeholder="nhập vào đây..."
          margin="normal"
          sx={{ minWidth: 100 }}
          variant="standard"
          InputProps={{
            startAdornment: (
              <InputAdornment position="start">
                <SearchIcon />
              </InputAdornment>
            ),
          }}
        />
      </Box>
      <Box className="administrator-manager__actions--right">
        <Button
          className="admin-btn admin-btn--create"
          onClick={() => {
            handleShowCreateTour();
          }}
        >
          + Thêm mới Tour
        </Button>
      </Box>
    </Box>
  );
};

export default TourListFilter;
