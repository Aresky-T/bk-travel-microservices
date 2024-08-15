import React, { useCallback, useEffect, useState } from "react";
import {
  Button,
  TextField,
  Box,
  Select,
  InputLabel,
  MenuItem,
  FormControl,
  InputAdornment,
} from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";
import StaffCreateContainer from "./StaffCreateContainer";
import StaffList from "../../../components/admin/Staff/StaffList";
import { useDispatch } from "react-redux";
import { fetchAllStaffsThunk } from "../../../redux/slices/admin.slice";
import { useAdmin } from "../../../redux/selector";
import StaffDetailsContainer from "./StaffDetailsContainer";

const StaffsListContainer = () => {
  const [isShowModal, setIsShowModal] = useState(false);
  const [pagination, setPagination] = useState({
    size: 5,
    page: 0,
    sort: "firstName,ASC",
  });
  const [isRefetch, setIsRefetch] = useState(false);

  const { staffManager } = useAdmin();
  const { staffList, totalPages } = staffManager;
  const dispatch = useDispatch();

  const handleChangeCurrentPage = (page) => {
    setPagination({
      ...pagination,
      page: page,
    });
  };

  const handleShowModal = () => {
    setIsShowModal(true);
  };

  const handleCloseModal = () => {
    setIsShowModal(false);
  };

  const fetchAllStaffs = useCallback(() => {
    dispatch(fetchAllStaffsThunk(pagination));
  }, [dispatch, pagination]);

  const renderStaffList = useCallback(() => {
    return staffList.length > 0 ? (
      <StaffList
        data={staffList}
        page={pagination.page}
        totalPages={totalPages}
        dispatch={dispatch}
        onChangePage={handleChangeCurrentPage}
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
    );

    // eslint-disable-next-line
  }, [staffList, pagination, totalPages]);

  const handleRefetchStaffs = () => setIsRefetch(true);

  useEffect(() => {
    if (isRefetch) fetchAllStaffs();

    // eslint-disable-next-line
  }, [isRefetch]);

  useEffect(() => {
    fetchAllStaffs();
  }, [fetchAllStaffs]);

  return (
    <Box
      className="administrator-manager_main staffs-list-container"
      sx={{
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        gap: "20px",
      }}
    >
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
            label="Tìm kiếm nhân viên"
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
            sx={{ minWidth: 120 }}
          >
            <InputLabel
              id="administrator-manager__actions-item--filter-id"
              sx={{ background: "#fff" }}
            >
              Tất cả
            </InputLabel>
            <Select
              labelId="administrator-manager__actions-item--filter-id"
              value={""}
            >
              <MenuItem value="">None</MenuItem>
              <MenuItem value="ACTIVE">đang làm việc</MenuItem>
              <MenuItem value="ON_LEAVE">nghỉ phép</MenuItem>
              <MenuItem value="EXPIRED_CONTRACT">hết thời hạn</MenuItem>
              <MenuItem value="LAYOFFED">bị sa thải</MenuItem>
            </Select>
          </FormControl>
        </Box>
        <Box className="administrator-manager__actions--right">
          <Button
            className="admin-btn admin-btn--create"
            type="button"
            onClick={handleShowModal}
          >
            + Thêm mới nhân viên
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
        {renderStaffList()}
      </Box>
      <StaffCreateContainer
        isShowModal={isShowModal}
        handleRefetchStaffs={handleRefetchStaffs}
        handleShowModal={handleShowModal}
        handleCloseModal={handleCloseModal}
      />
      <StaffDetailsContainer />
    </Box>
  );
};

export default StaffsListContainer;
