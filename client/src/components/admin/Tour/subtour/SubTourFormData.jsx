import React from "react";
import { TextField, FormControl, Box, Button, MenuItem } from "@mui/material";
import CustomDateTimePicker from "../../../mui_component/datetimepicker";

const SubTourFormData = ({
  selectedSubTour,
  formData,
  errorsMap,
  isShowForm,
  handleCancel,
  handleChange,
  handleSubmitCreate,
  handleSubmitUpdate,
}) => {
  const isExistSelectedSubTour = selectedSubTour != null;

  const handleSubmit = (event) => {
    event.preventDefault();
    if (isExistSelectedSubTour) {
      handleSubmitUpdate();
    } else {
      handleSubmitCreate();
    }
  };

  return (
    <div className={`sub-tour-form ${isShowForm ? "active" : ""}`}>
      <h2 className="sub-tour-form__title">
        Tour phụ{" "}
        {formData.id ? <span>(cập nhật)</span> : <span>(tạo mới)</span>}
      </h2>
      <form onSubmit={handleSubmit}>
        {isExistSelectedSubTour && (
          <TextField
            type="text"
            name="tourCode"
            label="Mã tour"
            helperText="(Không thể cập nhật mã tour)"
            variant="standard"
            fullWidth
            margin="normal"
            value={formData.tourCode ?? ""}
            InputProps={{
              readOnly: true,
            }}
            focused={false}
          />
        )}
        <TextField
          type="text"
          name="title"
          label="Tiêu đề"
          helperText={
            errorsMap?.get("title")?.message || "(Có thể cập nhật tiêu đề)"
          }
          variant="standard"
          error={errorsMap?.has("title")}
          multiline
          maxRows={5}
          required
          fullWidth
          margin="normal"
          value={formData.title ?? ""}
          onChange={handleChange}
        />
        {isExistSelectedSubTour && (
          <TextField
            name="status"
            type="text"
            labelId="sub-tour-form_item--status-id"
            label="Trạng thái"
            helperText="(Có thể cập nhật trạng thái)"
            variant="standard"
            required
            select
            fullWidth
            margin="normal"
            value={formData.status ?? ""}
            defaultValue={""}
            onChange={handleChange}
          >
            <MenuItem value="HIDDEN">Ẩn</MenuItem>
            <MenuItem value="NOT_STARTED">Chưa bắt đầu</MenuItem>
            <MenuItem value="ON_GOING">Đã khởi hành</MenuItem>
            <MenuItem value="FINISHED">Đã hoàn thành</MenuItem>
            <MenuItem value="BE_DELAYED">Bị trì hoãn</MenuItem>
            <MenuItem value="CANCELED">Đã bị hủy</MenuItem>
          </TextField>
        )}
        {isExistSelectedSubTour && (
          <TextField
            type="number"
            name="availableSeats"
            label={`Số chỗ còn trống`}
            helperText="(Có thể cập nhật số chỗ)"
            variant="standard"
            required
            fullWidth
            margin="normal"
            value={formData.availableSeats}
            onChange={(event) => {
              const value = Number(event.target.value);
              if (value >= 0) {
                handleChange({
                  target: {
                    name: "availableSeats",
                    value: value,
                  },
                });
              }
            }}
          />
        )}
        {isExistSelectedSubTour && (
          <TextField
            name="tourGuideId"
            label="Người dẫn đoàn"
            helperText="(Có thể cập nhật người dẫn đoàn)"
            variant="standard"
            fullWidth
            margin="normal"
            select
            value={formData.tourGuideId ?? 0}
            onChange={(event) => {
              let value = Number(event.target.value);
              if (value === 0) {
                handleChange({ target: { name: "tourGuideId", value: null } });
              } else {
                handleChange({ target: { name: "tourGuideId", value: value } });
              }
            }}
          >
            <MenuItem value={0}>Chưa xác định</MenuItem>
            <MenuItem value={1}>Option 1</MenuItem>
            <MenuItem value={2}>Option 2</MenuItem>
          </TextField>
        )}
        <FormControl fullWidth margin="normal">
          <CustomDateTimePicker
            label={"Thời gian khởi hành (Giờ Việt Nam)"}
            value={formData.departureTime}
            setValue={(value) => {
              handleChange({
                target: {
                  name: "departureTime",
                  value: value,
                },
              });
            }}
          />
        </FormControl>
        <Box>
          {isExistSelectedSubTour ? (
            <>
              <Button type="submit" className="admin-btn admin-btn--submit">
                Cập nhật
              </Button>
              <Button
                type="button"
                className="admin-btn admin-btn--delete"
                onClick={handleCancel}
              >
                Thoát
              </Button>
            </>
          ) : (
            <>
              <Button type="submit" className="admin-btn admin-btn--submit">
                Xác nhận
              </Button>
              <Button
                type="button"
                className="admin-btn admin-btn--delete"
                onClick={handleCancel}
              >
                Huỷ tạo
              </Button>
            </>
          )}
        </Box>
      </form>
    </div>
  );
};

export default SubTourFormData;
