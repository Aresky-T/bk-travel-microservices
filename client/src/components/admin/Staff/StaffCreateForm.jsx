import React from "react";
import { TextField, MenuItem, Box, Button } from "@mui/material";

const StaffCreateForm = ({
  formData,
  formErrors,
  handleChange,
  handleSubmit,
  handleReset,
}) => {
  return (
    <form margin="normal" onSubmit={handleSubmit}>
      <Box display="flex" gap={"45px"}>
        <TextField
          type="text"
          name="firstName"
          fullWidth
          required
          margin="normal"
          label="First name (Tên nhân viên)"
          variant="standard"
          helperText="Hãy nhập tên của nhân viên (bắt buộc)"
          error={formErrors.has("firstName")}
          value={formData.firstName}
          onChange={handleChange}
        />
        <TextField
          type="text"
          name="lastName"
          fullWidth
          required
          margin="normal"
          variant="standard"
          helperText="Hãy nhập họ và tên đệm của nhân viên (bắt buộc)"
          label="Last name (Họ + tên đệm)"
          error={formErrors.has("lastName")}
          value={formData.lastName}
          onChange={handleChange}
        />
      </Box>
      <Box display="flex" gap={"45px"}>
        <TextField
          type="email"
          name="email"
          fullWidth
          required
          margin="normal"
          variant="standard"
          helperText="Hãy nhập địa chỉ email của nhân viên (bắt buộc)"
          label="Email (địa chỉ email)"
          error={formErrors.has("email")}
          value={formData.email}
          onChange={handleChange}
        />
        <TextField
          type="text"
          name="phone"
          fullWidth
          required
          margin="normal"
          variant="standard"
          helperText="Hãy nhập số điện thoại của nhân viên (bắt buộc)"
          label="Phone (số điện thoại)"
          error={formErrors.has("phone")}
          value={formData.phone}
          onChange={handleChange}
        />
      </Box>
      <TextField
        type="text"
        name="address"
        fullWidth
        required
        margin="normal"
        variant="standard"
        helperText="Hãy nhập địa chỉ thường trú của nhân viên (bắt buộc)"
        label="Address (địa chỉ thường trú)"
        error={formErrors.has("address")}
        value={formData.address}
        onChange={handleChange}
      />
      <TextField
        type="text"
        name="description"
        fullWidth
        required
        margin="normal"
        variant="standard"
        helperText="Hãy nhập mô tả khái quát về nhân viên (không bắt buộc)"
        label="Description (Mô tả khái quát)"
        error={formErrors.has("description")}
        value={formData.description}
        onChange={handleChange}
      />
      <TextField
        type="text"
        name="avatarUrl"
        fullWidth
        required
        margin="normal"
        variant="standard"
        helperText="Hãy nhập link ảnh đại diện (bắt buộc)"
        label="Avatar url (Link ảnh đại diện)"
        error={formErrors.has("avatarUrl")}
        value={formData.avatarUrl}
        onChange={handleChange}
      />
      <Box display="flex" gap={"45px"}>
        <TextField
          name="gender"
          fullWidth
          required
          select
          margin="normal"
          variant="standard"
          helperText="Hãy chọn giới tính của nhân viên (bắt buộc)"
          label="Gender (giới tính)"
          error={formErrors.has("gender")}
          value={formData.gender}
          onChange={handleChange}
        >
          <MenuItem value="">None</MenuItem>
          <MenuItem value={"MALE"}>Nam</MenuItem>
          <MenuItem value={"FEMALE"}>Nữ</MenuItem>
          <MenuItem value={"Other"}>Khác</MenuItem>
        </TextField>
        <TextField
          type="date"
          name="dateOfBirth"
          fullWidth
          required
          margin="normal"
          variant="standard"
          helperText="Hãy nhập ngày sinh của nhân viên (bắt buộc)"
          label="Date of birth (Ngày sinh)"
          error={formErrors.has("dateOfBirth")}
          value={formData.dateOfBirth || "1990-01-01"}
          onChange={handleChange}
        />
      </Box>
      <Box display="flex" gap={"45px"}>
        {/* <TextField
          type="number"
          name="basicSalary"
          fullWidth
          required
          margin="normal"
          variant="standard"
          helperText="Hãy nhập mức lương cơ bản theo hợp đồng (bắt buộc)"
          label="Basic salary (Mức lương cơ bản)"
          value={formData.basicSalary}
          onChange={handleChange}
        /> */}
        <TextField
          type="date"
          name="hireDate"
          fullWidth
          required
          margin="normal"
          variant="standard"
          helperText="Nhập ngày bắt đầu làm việc của nhân viên (bắt buộc)"
          label="Ngày bắt đầu làm việc"
          error={formErrors.has("hireDate")}
          value={formData.hireDate || "1990-01-01"}
          onChange={handleChange}
        />
      </Box>
      <TextField
        type="text"
        name="contractUrl"
        fullWidth
        required
        margin="normal"
        variant="standard"
        helperText="Nhập link hợp đồng (bắt buộc)"
        label="Contract url (Link hợp đồng)"
        error={formErrors.has("contractUrl")}
        value={formData.contractUrl}
        onChange={handleChange}
      />
      <Box display={"flex"} justifyContent={"flex-end"}>
        <Button className="admin-btn" onClick={handleReset} type="reset">
          Đặt lại
        </Button>
        <Button className="admin-btn admin-btn--submit" type="submit">
          Xác nhận
        </Button>
      </Box>
    </form>
  );
};

export default StaffCreateForm;
