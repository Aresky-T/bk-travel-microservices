import React from "react";
import { TextField, Box, Button } from "@mui/material";

const DepartmentCreateForm = ({
  formData,
  handleChange,
  handleSubmit,
  handleReset,
}) => {
  return (
    <form className="department-create-form" onSubmit={handleSubmit}>
      <TextField
        type="text"
        name="name"
        fullWidth
        required
        label="Tên phòng ban"
        helperText="Hãy nhập trên phòng ban (bắt buộc)"
        margin="normal"
        value={formData.name.value}
        onChange={handleChange}
      />
      <TextField
        type="text"
        name="description"
        label="Mô tả"
        fullWidth
        required
        multiline
        rows={5}
        helperText="Hãy nhập mô tả phòng ban (bắt buộc)"
        margin="normal"
        value={formData.description.value}
        onChange={handleChange}
      />
      <Box display={"flex"} justifyContent={"flex-end"}>
        <Button className="admin-btn" type="reset" onClick={handleReset}>
          Đặt lại
        </Button>
        <Button className="admin-btn admin-btn--submit" type="submit">
          Xác nhận
        </Button>
      </Box>
    </form>
  );
};

export default DepartmentCreateForm;
