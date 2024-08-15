import React from "react";
import {
  Box,
  Button,
  FormControl,
  ImageList,
  ImageListItem,
  InputLabel,
  MenuItem,
  Select,
  TextField,
} from "@mui/material";
import ReactQuill from "react-quill";
import SubTourManagerContainer from "../../../containers/admin/Tour/subtour/SubTourManagerContainer";
import { useDispatch } from "react-redux";
import { onUpdateAdminSelectedTourId } from "../../../redux/slices/admin.slice";

const TourDetailsAdmin = ({ tourFormik, handleDeleteTour }) => {
  const dispatch = useDispatch();

  return (
    <div className="tour-detail-admin scroll-container">
      <div className="tour-detail-admin_main">
        <h1>Chi tiết Tour</h1>
        <Button
          className="admin-btn--back"
          onClick={() => {
            dispatch(onUpdateAdminSelectedTourId(null));
          }}
        >
          Quay lại
        </Button>
        <form
          className="admin-main-form tour-details-admin-form"
          onSubmit={tourFormik.handleSubmit}
        >
          <TextField
            type="text"
            name="title"
            label="Tiêu đề"
            required
            multiline
            maxRows={5}
            fullWidth
            margin="normal"
            value={tourFormik.values.title}
            onChange={tourFormik.handleChange}
          />
          <Box
            display={"flex"}
            gap={"30px"}
            alignItems={"center"}
            justifyContent={"space-between"}
            width={"100%"}
          >
            <Box>
              <ImageList
                sx={{ width: 350, borderRadius: "10px" }}
                variant="quilted"
                cols={2}
                rowHeight={100}
              >
                <ImageListItem cols={1} rows={2}>
                  <img
                    src={tourFormik.values.image1}
                    alt="tour-details-img"
                    loading="lazy"
                  />
                </ImageListItem>
                <ImageListItem cols={1} rows={1}>
                  <img
                    src={tourFormik.values.image2}
                    alt="tour-details-img"
                    loading="lazy"
                  />
                </ImageListItem>
                <ImageListItem cols={1} rows={1}>
                  <img
                    src={tourFormik.values.image3}
                    alt="tour-details-img"
                    loading="lazy"
                  />
                </ImageListItem>
                <ImageListItem cols={2} rows={1}>
                  <img
                    src={tourFormik.values.image4}
                    alt="tour-details-img"
                    loading="lazy"
                  />
                </ImageListItem>
              </ImageList>
            </Box>
            <Box>
              <TextField
                type="text"
                name="image1"
                label="Link ảnh 1"
                required
                fullWidth
                margin="normal"
                value={tourFormik.values.image1}
                onChange={tourFormik.handleChange}
              />
              <TextField
                type="text"
                name="image2"
                label="Link ảnh 2"
                required
                fullWidth
                margin="normal"
                value={tourFormik.values.image2}
                onChange={tourFormik.handleChange}
              />
              <TextField
                type="text"
                name="image3"
                label="Link ảnh 3"
                required
                fullWidth
                margin="normal"
                value={tourFormik.values.image3}
                onChange={tourFormik.handleChange}
              />
              <TextField
                type="text"
                name="image4"
                label="Link ảnh 4"
                required
                fullWidth
                margin="normal"
                value={tourFormik.values.image4}
                onChange={tourFormik.handleChange}
              />
            </Box>
          </Box>
          <TextField
            type="text"
            name="destinations"
            label="Danh sách điểm đến"
            required
            fullWidth
            margin="normal"
            multiline
            maxRows={5}
            value={tourFormik.values.destinations}
            onChange={tourFormik.handleChange}
          />
          <div className="scheduleDes-tour-form">
            <InputLabel className="input-label" required>
              Mô tả lịch trình (bắt buộc)
            </InputLabel>
            <ReactQuill
              name="schedules"
              theme="snow"
              value={tourFormik.values.schedules}
              onChange={(e) => {
                tourFormik.setFieldValue("schedules", e);
              }}
            />
          </div>
          <Box
            className="td-admin-form__time-address"
            sx={{
              display: "grid",
              gridTemplateColumns: "repeat(2, 1fr)",
              gridTemplateRows: "auto",
              columnGap: "20px",
            }}
          >
            <TextField
              type="text"
              name="departureLocation"
              label="Địa điểm khởi hành"
              required
              fullWidth
              margin="normal"
              value={tourFormik.values.departureLocation}
              onChange={tourFormik.handleChange}
            />
            <FormControl fullWidth margin="normal">
              <InputLabel id="td-admin-form__vehicle-id" required>
                Phương tiện
              </InputLabel>
              <Select
                labelId="td-admin-form__vehicle-id"
                label="Phương tiện"
                required
                name="vehicle"
                value={tourFormik.values.vehicle}
                onChange={tourFormik.handleChange}
              >
                <MenuItem value="">
                  <em>None</em>
                </MenuItem>
                <MenuItem
                  value="Máy bay"
                  defaultChecked={tourFormik.values.vehicle === "Máy bay"}
                >
                  Máy bay
                </MenuItem>
                <MenuItem
                  value="Xe du lịch"
                  defaultChecked={tourFormik.values.vehicle === "Xe du lịch"}
                >
                  Xe du lịch
                </MenuItem>
                <MenuItem
                  value="Máy bay, Xe du lịch"
                  defaultChecked={
                    tourFormik.values.vehicle === "Máy bay, Xe du lịch"
                  }
                >
                  Cả hai
                </MenuItem>
              </Select>
            </FormControl>
          </Box>
          <Box
            className="td-admin-form__seats"
            sx={{
              display: "grid",
              gridTemplateColumns: "repeat(2, 1fr)",
              columnGap: "20px",
            }}
          >
            <TextField
              type="number"
              name="totalSeats"
              label="Tổng số chỗ"
              required
              fullWidth
              margin="normal"
              value={tourFormik.values.totalSeats}
              onChange={tourFormik.handleChange}
            />
            <TextField
              type="text"
              name="duration"
              label="Tổng thời gian"
              required
              fullWidth
              margin="normal"
              value={tourFormik.values.duration}
              onChange={tourFormik.handleChange}
            />
          </Box>
          <Box
            sx={{
              display: "grid",
              gridTemplateColumns: "repeat(3, 1fr)",
              columnGap: "20px",
            }}
            className="td-admin-form__prices"
          >
            <TextField
              type="number"
              name="adultPrice"
              label="Giá cho người lớn (VNĐ)"
              required
              fullWidth
              margin="normal"
              value={tourFormik.values.adultPrice}
              onChange={tourFormik.handleChange}
            />

            <TextField
              type="number"
              name="childrenPrice"
              label="Giá cho trẻ em (VNĐ)"
              required
              fullWidth
              margin="normal"
              value={tourFormik.values.childrenPrice}
              onChange={tourFormik.handleChange}
            />

            <TextField
              type="number"
              name="babyPrice"
              label="Giá cho em bé  (VNĐ)"
              required
              fullWidth
              margin="normal"
              value={tourFormik.values.babyPrice}
              onChange={tourFormik.handleChange}
            />
          </Box>
          <Box sx={{ marginTop: "30px" }}>
            <Button type="submit" className="admin-btn admin-btn--submit">
              Xác nhận chỉnh sửa
            </Button>
            <Button
              type="button"
              className="admin-btn admin-btn--delete"
              onClick={handleDeleteTour}
            >
              Xóa Tour này
            </Button>
          </Box>
        </form>
        <SubTourManagerContainer />
      </div>
    </div>
  );
};

export default TourDetailsAdmin;
