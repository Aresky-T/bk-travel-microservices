import {
  Box,
  Button,
  FormControl,
  InputAdornment,
  InputLabel,
  MenuItem,
  Select,
  TextField,
} from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";
import React from "react";

const DepartmentList = ({ departments, handleShowModal, handleSelect }) => {
  return (
    <>
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
            label="Tìm kiếm phòng ban"
            placeholder="nhập vào đây..."
            margin="normal"
            sx={{ minWidth: 120 }}
            variant="standard"
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <SearchIcon />
                </InputAdornment>
              ),
            }}
          />
          <FormControl
            className="administrator-manager__actions-item filter"
            margin="normal"
            variant="standard"
            sx={{ minWidth: 150 }}
          >
            <InputLabel
              id="administrator-manager__actions-item--filter-id"
              sx={{ background: "#fff" }}
            >
              Sắp xếp
            </InputLabel>
            <Select
              labelId="administrator-manager__actions-item--filter-id"
              value={""}
            >
              <MenuItem value="">None</MenuItem>
              <MenuItem value="ACTIVE">Mới nhất</MenuItem>
              <MenuItem value="ON_LEAVE">Nhiều nhân viên nhất</MenuItem>
            </Select>
          </FormControl>
        </Box>
        <Box className="administrator-manager__actions--right">
          <Button
            className="admin-btn admin-btn--create"
            onClick={handleShowModal}
          >
            + Tạo phòng ban
          </Button>
        </Box>
      </Box>
      <Box
        sx={{
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          width: "100%",
          height: "100%",
        }}
      >
        {departments.length > 0 ? (
          <Box className="admin-department-list" width={"100%"} height={"80%"}>
            <table className="admin-table">
              <thead>
                <tr>
                  <th>STT</th>
                  <th>Tên phòng ban</th>
                  <th>Trưởng phòng</th>
                  <th>Số nhân viên</th>
                  <th>Hành động</th>
                </tr>
              </thead>
              <tbody>
                {[...departments].map((item, index) => (
                  <tr key={index} className="admin-table-row">
                    <td>{index + 1}</td>
                    <td>{item.name}</td>
                    <td>{item.manager?.name || "Chưa xác định"}</td>
                    <td>{item.numberOfStaffs ?? 0}</td>
                    <td>
                      <Button
                        onClick={() => handleSelect(item)}
                        sx={{
                          padding: "4px 0",
                        }}
                      >
                        Xem
                      </Button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </Box>
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
    </>
  );
};

export default DepartmentList;
