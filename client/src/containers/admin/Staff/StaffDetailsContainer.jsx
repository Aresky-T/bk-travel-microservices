import React, { useCallback, useEffect, useState } from "react";
import AdminFormWrapper from "../../../components/admin/Form";
import { useAdmin } from "../../../redux/selector";
import {
  getDetailsStaffApi,
  updateStaffApi,
} from "../../../api/admin/staff.api";
import { useFormik } from "formik";
import {
  Box,
  Button,
  ImageList,
  ImageListItem,
  MenuItem,
  TextField,
} from "@mui/material";
import { useDispatch } from "react-redux";
import { onUpdateAdminCurrentStaff } from "../../../redux/slices/admin.slice";
import { STAFF_STATUS_TRANSLATIONS } from "../../../constant/translations";
import { toast } from "react-toastify";

const initStaff = {
  firstName: "",
  lastName: "",
  email: "",
  phone: "",
  address: "",
  gender: "",
  dateOfBirth: "",
  avatarUrl: "",
  description: "",
  status: "",
  hireDate: "",
  contractUrl: "",
  accountInfo: "",
};

const StaffDetailsContainer = () => {
  const [staffDetails, setStaffDetails] = useState();
  const [active, setActive] = useState(false);
  const { staffManager } = useAdmin();
  const { currentStaff } = staffManager;
  const dispatch = useDispatch();

  const staffForm = useFormik({
    initialValues: initStaff,
    onSubmit: (values) => {
      const formData = {};

      for (const key in values) {
        if (
          Object.prototype.hasOwnProperty.call(staffDetails, key) &&
          staffDetails[key] !== values[key]
        ) {
          formData[key] = values[key];
        }
      }

      if (JSON.stringify(formData) === "{}") {
        toast.warning("Không có gì thay đổi");
        return;
      }

      const loading = toast.loading("Đang xử lý");
      updateStaffApi(staffDetails.id, formData)
        .then((res) => {
          setTimeout(() => {
            toast.dismiss(loading);
            toast.success("Cập nhật thành công");
            fetchStaffDetails();
          }, 1500);
          const data = res.data;
          console.log(data);
        })
        .catch((err) => {
          setTimeout(() => {
            toast.dismiss(loading);
            toast.error("Cập nhật thất bại");
          }, 1500);
        });
    },
  });

  const renderStaffStatusOptions = () => {
    const keys = Object.keys(STAFF_STATUS_TRANSLATIONS);
    return keys.map((key, idx) => (
      <MenuItem value={key} key={idx}>
        {STAFF_STATUS_TRANSLATIONS[key]}
      </MenuItem>
    ));
  };

  const fetchStaffDetails = useCallback(() => {
    if (!currentStaff || !currentStaff.id) return;

    const staffId = currentStaff.id;

    getDetailsStaffApi(staffId)
      .then((res) => {
        setStaffDetails(res.data);
        setActive(true);
      })
      .catch((err) => {});
  }, [currentStaff]);

  useEffect(() => {
    if (staffDetails) {
      const obj = {};
      for (const key in staffDetails) {
        if (Object.prototype.hasOwnProperty.call(staffForm.values, key)) {
          obj[key] = staffDetails[key];
        }
      }
      staffForm.setValues(obj);
    }

    //eslint-disable-next-line
  }, [staffDetails]);

  useEffect(() => {
    fetchStaffDetails();
  }, [fetchStaffDetails]);

  return (
    <AdminFormWrapper
      active={active}
      position={"center"}
      title={"Chi tiết nhân viên"}
      showCloseButton={true}
      onClose={() => {
        dispatch(onUpdateAdminCurrentStaff(null));
        staffForm.setValues(initStaff);
        setActive(false);
      }}
    >
      <form onSubmit={staffForm.handleSubmit}>
        <Box
          display={"flex"}
          gap={"30px"}
          justifyContent={"center"}
          width={"100%"}
          paddingBlock={"25px"}
          border={"2px solid #ccc"}
          borderRadius={"10px"}
        >
          <ImageList
            sx={{ width: "300px", borderRadius: "10px" }}
            variant="quilted"
            cols={2}
            rowHeight={100}
          >
            <ImageListItem cols={2} rows={2}>
              <img
                src={staffDetails?.avatarUrl}
                alt="tour-details-img"
                loading="lazy"
              />
            </ImageListItem>
          </ImageList>
        </Box>
        <Box
          display={"flex"}
          gap={"30px"}
          alignItems={"center"}
          justifyContent={"space-between"}
          width={"100%"}
        >
          <TextField
            type="text"
            name="firstName"
            label="Tên"
            required
            variant="standard"
            helperText="(*) Có thể thay đổi"
            fullWidth
            margin="normal"
            value={staffForm.values.firstName}
            onChange={staffForm.handleChange}
          />
          <TextField
            type="text"
            name="lastName"
            label="Họ và tên đệm"
            required
            variant="standard"
            helperText="(*) Có thể thay đổi"
            fullWidth
            margin="normal"
            value={staffForm.values.lastName}
            onChange={staffForm.handleChange}
          />
        </Box>
        <Box
          display={"flex"}
          gap={"30px"}
          justifyContent={"center"}
          width={"100%"}
        >
          <TextField
            type="text"
            name="email"
            label="Địa chỉ email"
            required
            variant="standard"
            helperText="(*) Có thể thay đổi"
            fullWidth
            margin="normal"
            value={staffForm.values.email}
            onChange={staffForm.handleChange}
          />
          <TextField
            type="text"
            name="phone"
            label="Số điện thoại"
            required
            variant="standard"
            helperText="(*) Có thể thay đổi"
            fullWidth
            margin="normal"
            value={staffForm.values.phone}
            onChange={staffForm.handleChange}
          />
        </Box>
        <Box
          display={"flex"}
          gap={"30px"}
          justifyContent={"center"}
          width={"100%"}
        >
          <TextField
            type="text"
            name="gender"
            label="Giới tính"
            required
            variant="standard"
            helperText="(*) Có thể thay đổi"
            fullWidth
            select
            margin="normal"
            value={staffForm.values.gender}
            onChange={staffForm.handleChange}
          >
            <MenuItem value={""}>
              <em>None</em>
            </MenuItem>
            <MenuItem value={"MALE"}>Nam</MenuItem>
            <MenuItem value={"FEMALE"}>Nữ</MenuItem>
            <MenuItem value={"OTHER"}>Khác</MenuItem>
          </TextField>
          <TextField
            type="date"
            name="dateOfBirth"
            label="ngày sinh"
            required
            variant="standard"
            helperText="(*) Có thể thay đổi"
            fullWidth
            margin="normal"
            value={staffForm.values.dateOfBirth}
            onChange={staffForm.handleChange}
          />
        </Box>
        <TextField
          type="text"
          name="address"
          label="Địa chỉ thường trú"
          required
          variant="standard"
          helperText="(*) Có thể thay đổi"
          fullWidth
          margin="normal"
          value={staffForm.values.address}
          onChange={staffForm.handleChange}
        />
        <TextField
          type="text"
          name="avatarUrl"
          label="Ảnh đại diện"
          required
          variant="standard"
          helperText="(*) Có thể thay đổi"
          fullWidth
          margin="normal"
          value={staffForm.values.avatarUrl}
          onChange={staffForm.handleChange}
        />
        <TextField
          type="date"
          name="hireDate"
          label="Ngày bắt đầu làm việc"
          required
          variant="standard"
          helperText="(*) Có thể thay đổi"
          fullWidth
          margin="normal"
          value={staffForm.values.hireDate}
          onChange={staffForm.handleChange}
        />
        <TextField
          type="text"
          name="contractUrl"
          label="Link hợp đồng"
          required
          variant="standard"
          helperText="(*) Có thể thay đổi"
          fullWidth
          margin="normal"
          value={staffForm.values.contractUrl}
          onChange={staffForm.handleChange}
        />
        <TextField
          type="text"
          name="status"
          label="Trạng thái hoạt động"
          required
          variant="standard"
          helperText="(*) Có thể thay đổi"
          fullWidth
          select
          margin="normal"
          value={staffForm.values.status ?? ""}
          onChange={staffForm.handleChange}
        >
          <MenuItem value="">
            <em>None</em>
          </MenuItem>
          {renderStaffStatusOptions()}
        </TextField>
        <Box sx={{ marginTop: "30px" }}>
          <Button type="submit" className="admin-btn admin-btn--submit">
            Cập nhật
          </Button>
          <Button
            type="button"
            className="admin-btn admin-btn--delete"
            // onClick={handleDeleteTour}
          >
            Xóa
          </Button>
        </Box>
      </form>
    </AdminFormWrapper>
  );
};

export default StaffDetailsContainer;
