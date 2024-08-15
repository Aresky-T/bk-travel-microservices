import {
  FormControl,
  InputLabel,
  MenuItem,
  TextField,
  Box,
  Button,
} from "@mui/material";
import React, { useRef } from "react";
import ReactQuill from "react-quill";
import "react-quill/dist/quill.snow.css";
import { AiFillDelete } from "react-icons/ai";
import AdminModalWrapper from "../Modal";

const TourCreate = ({
  formik,
  selectedFiles,
  isShowCreateTour,
  setEmptySelectedFiles,
  handleChangeFiles,
  handleResetForm,
  handleHiddenForm,
}) => {
  const inputFileRef = useRef();

  const handleFocusInputFile = () => {
    if (inputFileRef.current) {
      inputFileRef.current.click();
    }
  };

  return (
    <AdminModalWrapper
      title={"Tạo mới Tour"}
      isOpen={isShowCreateTour}
      handleClose={handleHiddenForm}
    >
      <form className="tour-create-form" onSubmit={formik.handleSubmit}>
        <TextField
          name="title"
          label="Tiêu đề"
          // required
          fullWidth
          multiline
          maxRows={3}
          margin="normal"
          value={formik.values.title}
          onChange={formik.handleChange}
        />
        <TextField
          name="destinations"
          label="Danh sách điểm đến"
          type="text"
          // required
          fullWidth
          multiline
          maxRows={5}
          margin="normal"
          value={formik.values.destinations}
          onChange={formik.handleChange}
        />
        <Box display={"flex"} gap={"20px"}>
          <TextField
            name="duration"
            label="Tổng thời gian"
            type="text"
            // required
            fullWidth
            margin="normal"
            placeholder="Ví dụ: 2 ngày 1 đêm"
            value={formik.values.duration}
            onChange={formik.handleChange}
          />
          <TextField
            name="departureLocation"
            label="Địa điểm khởi hành"
            type="text"
            // required
            fullWidth
            margin="normal"
            value={formik.values.departureLocation}
            onChange={formik.handleChange}
          />
        </Box>
        <Box display={"flex"} gap={"20px"}>
          <TextField
            name="totalSeats"
            label="Tổng số chỗ"
            type="number"
            // required
            fullWidth
            margin="normal"
            value={formik.values.totalSeats}
            onChange={formik.handleChange}
          />
          <TextField
            name="vehicle"
            type="text"
            label="Phương tiện di chuyển"
            // required
            fullWidth
            margin="normal"
            select
            value={formik.values.vehicle}
            onChange={formik.handleChange}
          >
            <MenuItem value="">
              <em>None</em>
            </MenuItem>
            <MenuItem value="Máy bay">Máy bay</MenuItem>
            <MenuItem value="Xe du lịch">Xe du lịch</MenuItem>
            <MenuItem value="Máy bay, Xe du lịch">Cả hai</MenuItem>
          </TextField>
        </Box>
        <Box display={"flex"} gap={"20px"}>
          <TextField
            name="adultPrice"
            label="Chi phí cho 1 người lớn"
            type="number"
            // required
            fullWidth
            margin="normal"
            className="prices-tour-form-item"
            value={formik.values.adultPrice}
            onChange={formik.handleChange}
          />

          <TextField
            name="childrenPrice"
            label="Chi phí cho 1 trẻ em"
            type="number"
            // required
            fullWidth
            margin="normal"
            className="prices-tour-form-item"
            value={formik.values.childrenPrice}
            onChange={formik.handleChange}
          />

          <TextField
            name="babyPrice"
            label="Chi phí cho 1 em bé"
            type="number"
            // required
            fullWidth
            margin="normal"
            className="prices-tour-form-item"
            value={formik.values.babyPrice}
            onChange={formik.handleChange}
          />
        </Box>

        {selectedFiles < 1 ? (
          <div className="upload-image-tour-form">
            <div className="upload-image-tour" onClick={handleFocusInputFile}>
              <div>
                <img
                  src="https://cdn.pixabay.com/photo/2017/02/07/02/16/cloud-2044823_960_720.png"
                  alt=""
                />
              </div>
              <span>Tải 4 tệp ảnh tại đây</span>
            </div>
            <input
              type="file"
              name=""
              id=""
              multiple
              onChange={handleChangeFiles}
              ref={inputFileRef}
            />
          </div>
        ) : (
          <div className="selected-images">
            <InputLabel>Các tệp đã chọn</InputLabel>
            {selectedFiles?.map((file, id) => {
              file.url = URL.createObjectURL(file);
              return (
                <div className="selected-images-item" key={id}>
                  <img src={file.url} alt="" />
                </div>
              );
            })}
            <span
              className="delete-selected-files"
              onClick={setEmptySelectedFiles}
            >
              <AiFillDelete size={30} />
            </span>
          </div>
        )}
        <FormControl
          className="scheduleDes-tour-form"
          fullWidth
          margin="normal"
        >
          <ReactQuill
            placeholder="Mô tả lịch trình (bắt buộc)"
            name="scheduleDescription"
            theme="snow"
            value={formik.values.schedules}
            onChange={(e) => {
              formik.setFieldValue("schedules", e);
            }}
          />
        </FormControl>
        <Box display={"flex"} justifyContent={"flex-end"}>
          <Button
            type="reset"
            className="admin-btn admin-btn--delete"
            onClick={handleResetForm}
          >
            Đặt lại
          </Button>
          <Button type="submit" className="admin-btn admin-btn--submit">
            Xác nhận
          </Button>
        </Box>
      </form>
    </AdminModalWrapper>
  );
};

export default TourCreate;
