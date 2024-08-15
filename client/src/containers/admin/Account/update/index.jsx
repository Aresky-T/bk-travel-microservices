import React, { useCallback, useEffect, useState } from "react";
import AdminFormWrapper from "../../../../components/admin/Form";
import { Box, Button, MenuItem, TextField } from "@mui/material";
import { useAdmin } from "../../../../redux/selector";
import { ROLE } from "../../../../constant/role";
import { useFormik } from "formik";
import { useDispatch } from "react-redux";
import {
  onRefetchAdminAccountList,
  onUpdateAdminCurrentAccount,
} from "../../../../redux/slices/admin.slice";
import { updateAccountApi } from "../../../../api/admin/account.api";
import { toast } from "react-toastify";
import { questionAlert } from "../../../../config/sweetAlertConfig";

const AccountUpdateContainer = () => {
  const { accountManager } = useAdmin();
  const { currentAccount } = accountManager;
  const [isActive, setIsActive] = useState(false);
  const dispatch = useDispatch();

  const formik = useFormik({
    initialValues: {
      role: "",
      status: "",
    },
    onSubmit: (values) => {
      const formData = {};

      for (const key in values) {
        if (
          Object.hasOwnProperty.call(currentAccount, key) &&
          currentAccount[key] !== values[key]
        ) {
          formData[key] = values[key];
        }
      }

      if (JSON.stringify(formData) === "{}") {
        toast.warning("Không có giá trị nào bị thay đổi", {
          position: "top-right",
        });
        return;
      }

      questionAlert(
        "Cảnh báo",
        "Bạn có chắc chắn muốn cập nhật tài khoản này không?",
        "Cập nhật",
        "Huỷ bỏ"
      ).then((selectedBtn) => {
        if (selectedBtn.isConfirmed) {
          handleSubmitUpdate(currentAccount.id, values);
        }
      });
    },
    onReset: (values) => {
      values.role = currentAccount?.role ?? "";
      values.status = currentAccount?.status ?? "";
    },
  });

  const handleSubmitUpdate = (accountId, formData) => {
    updateAccountApi(accountId, formData)
      .then((res) => {
        toast.success("Cập nhật tài khoản thành công", {
          position: "top-right",
        });
        dispatch(onUpdateAdminCurrentAccount(res.data));
        dispatch(onRefetchAdminAccountList());
      })
      .catch((err) => {
        toast.error("Cập nhật tài khoản thất bại", {
          position: "top-right",
        });
      });
  };

  const handleCancelUpdate = () => {
    formik.handleReset();
    dispatch(onUpdateAdminCurrentAccount(null));
  };

  const renderAccountRoleOptions = useCallback(() => {
    if (!currentAccount) return;

    return (
      <TextField
        name="role"
        label="Cấp độ tài khoản"
        helperText="(Có thể cập nhật cấp độ tài khoản)"
        // variant="standard"
        fullWidth
        margin="normal"
        required
        select
        value={formik.values.role}
        onChange={formik.handleChange}
      >
        <MenuItem value={ROLE.USER}>Người dùng</MenuItem>
        <MenuItem value={ROLE.STAFF}>Nhân viên</MenuItem>
      </TextField>
    );
  }, [currentAccount, formik]);

  const renderAccountStatusOptions = useCallback(() => {
    if (!currentAccount) return;

    return (
      <TextField
        name="status"
        label="Trạng thái kích hoạt"
        helperText="(Có thể cập nhật trạng thái kích hoạt)"
        // variant="standard"
        fullWidth
        margin="normal"
        select
        required
        value={formik.values.status}
        onChange={formik.handleChange}
      >
        <MenuItem value={"ACTIVE"}>Kích hoạt</MenuItem>
        <MenuItem value={"BLOCKED"}>Khoá</MenuItem>
      </TextField>
    );
  }, [currentAccount, formik]);

  useEffect(() => {
    setIsActive(currentAccount ? true : false);
    currentAccount &&
      formik.setValues({
        role: currentAccount.role,
        status: currentAccount.status,
      });

    //eslint-disable-next-line
  }, [currentAccount]);

  return (
    <AdminFormWrapper active={isActive} position={"right"} title={"Tài khoản"}>
      <form onSubmit={formik.handleSubmit} onReset={formik.handleReset}>
        {currentAccount && (
          <>
            <TextField
              type="text"
              name="username"
              label="Tên tài khoản"
              required
              fullWidth
              margin="normal"
              helperText="(Không thể cập nhật tên tài khoản)"
              value={currentAccount.username}
              InputProps={{
                readOnly: true,
              }}
            />
            <TextField
              type="text"
              name="email"
              label="Email"
              required
              fullWidth
              margin="normal"
              helperText="(Không thể cập nhật địa chỉ email)"
              value={currentAccount.email}
              InputProps={{
                readOnly: true,
              }}
            />
          </>
        )}
        {renderAccountStatusOptions()}
        {renderAccountRoleOptions()}
        <Box>
          <Button type="submit" className="admin-btn admin-btn--submit">
            Cập nhật
          </Button>
          <Button type="reset" className="admin-btn">
            Đặt lại
          </Button>
          <Button
            type="button"
            className="admin-btn admin-btn--red"
            onClick={handleCancelUpdate}
          >
            Thoát
          </Button>
        </Box>
      </form>
    </AdminFormWrapper>
  );
};

export default AccountUpdateContainer;
