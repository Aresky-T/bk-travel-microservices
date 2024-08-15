import React from "react";
import { GrStatusGoodSmall } from "react-icons/gr";
import CustomPaginate from "../../pagination/CustomPaginate";
import { Box, TextField, InputAdornment, Button } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";
import { ROLE } from "../../../constant/role";
import { useDispatch } from "react-redux";
import { onUpdateAdminCurrentAccount } from "../../../redux/slices/admin.slice";

const AccountManager = ({
  accounts,
  pageNumber,
  totalElements,
  totalPages,
  handleChangeCurrentPage,
  handleDeleteAccount,
}) => {
  const dispatch = useDispatch();

  const renderCreatedTime = (createTime) => {
    return new Date(createTime).toLocaleString("vi-VN", {
      dateStyle: "short",
      timeStyle: "short",
    });
  };

  const renderRole = (account) => {
    const role = account?.role;

    if (!role) return "";

    switch (role) {
      case "ADMIN":
        return "Quản trị viên";
      case ROLE.USER:
        return "Người dùng";
      case ROLE.STAFF:
        return "Nhân viên";
      default:
    }
  };

  const renderActivationStatus = (account) => {
    const activationStatus = account?.status;

    if (!activationStatus) return "Không xác định";

    return activationStatus === "ACTIVE" ? "Kích hoạt" : "Bị khoá";
  };

  const renderActionButton = (account) => {
    const activationStatus = account?.status;
    const role = account?.role;

    if (role === ROLE.ADMIN || !activationStatus) return "";

    return (
      <>
        <Button
          className="admin-btn--submit"
          type="button"
          onClick={() => {
            dispatch(onUpdateAdminCurrentAccount(account));
          }}
        >
          Cập nhật
        </Button>
        <Button
          className="admin-btn--delete"
          onClick={() => handleDeleteAccount(account)}
        >
          Xoá
        </Button>
      </>
    );
  };

  return (
    <Box className="administrator-manager">
      <section className="administrator-manager_header ">
        <div className="administrator-manager__title">Danh sách tài khoản</div>
        <div className="administrator-manager__menu">
          <div className="administrator-manager__menu-item tours active">
            Account
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
              label="Tìm kiếm tài khoản"
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
          {accounts.length ? (
            <Box width={"100%"}>
              <table className="admin-table account-manager__table">
                <thead>
                  <tr>
                    <th className="account-manager__table-th __id">STT</th>
                    <th className="account-manager__table-th __name">
                      Username
                    </th>
                    <th className="account-manager__table-th __email">Email</th>
                    <th className="account-manager__table-th __role">Role</th>
                    <th className="account-manager__table-th __status">
                      Status
                    </th>
                    <th className="account-manager__table-th __created-time">
                      Thời gian tạo
                    </th>
                    <th className="account-manager__table-th __actions">
                      Hành động
                    </th>
                  </tr>
                </thead>
                <tbody>
                  {accounts?.map((account, index) => (
                    <tr className="admin-table-row" key={index}>
                      <td className="account-manager__table-td __id">
                        {index + 1}
                      </td>
                      <td className="account-manager__table-td __name">
                        {account.username || ""}
                      </td>
                      <td className="account-manager__table-td __email">
                        {account.email || ""}
                      </td>
                      <td className="account-manager__table-td __role">
                        {renderRole(account)}
                      </td>
                      <td className="account-manager__table-td __status">
                        <div>
                          <span>
                            <GrStatusGoodSmall
                              size={15}
                              color={
                                account.status === "ACTIVE"
                                  ? "var(--fourth-color)"
                                  : "red"
                              }
                            />
                          </span>
                          <span>{renderActivationStatus(account)}</span>
                        </div>
                      </td>
                      <td className="account-manager__table-td __created-time">
                        {account.createdTime &&
                          renderCreatedTime(account.createdTime)}
                      </td>
                      <td>
                        <Box
                          sx={{
                            width: "fit-content",
                            display: "flex",
                            alignItems: "center",
                            justifyContent: "center",
                            gap: "15px",
                          }}
                        >
                          {renderActionButton(account)}
                        </Box>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
              <Box
                sx={{
                  padding: "5px 0",
                  marginTop: "15px",
                  fontSize: ".9rem",
                  fontWeight: 550,
                }}
              >
                Tổng số tài khoản: {totalElements}
              </Box>
              <CustomPaginate
                currentPage={pageNumber}
                totalPages={totalPages}
                setCurrentPage={handleChangeCurrentPage}
              />
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
      </Box>
    </Box>
  );
};

export default AccountManager;
