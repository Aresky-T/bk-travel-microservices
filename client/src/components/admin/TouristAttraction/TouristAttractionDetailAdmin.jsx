import {
  Box,
  Button,
  ImageList,
  ImageListItem,
  TextField,
} from "@mui/material";
import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { onUpdateAdminTouristAttraction } from "../../../redux/slices/admin.slice";
import BlogManagerContainer from "../../../containers/admin/TouristAttraction/blog/BlogManagerContainer";

const TouristAttractionDetailAdmin = ({
  formik,
  handleDeleteTouristAttraction,
  handleRestoreOriginal,
}) => {
  const dispatch = useDispatch();
  const [isShowImage, setIsShowImage] = useState(false);

  return (
    <div className="tourist-attraction-details-admin scroll-container">
      <div className="tourist-attraction-details-admin_main">
        <h1>Chi tiết Điểm đến</h1>
        <Button
          className="admin-btn--back"
          onClick={() => {
            dispatch(onUpdateAdminTouristAttraction(null));
          }}
        >
          Quay lại
        </Button>
        <form className="admin-main-form tourist-attraction-details-admin-form">
          <TextField
            name="name"
            className="tad-admin-item"
            label="Tên"
            required
            margin="normal"
            fullWidth
            variant="standard"
            helperText="(Tên không được để trống)"
            value={formik.values.name}
            onChange={formik.handleChange}
          />
          <Box>
            <TextField
              name="imageUrl"
              className="tad-admin-item"
              label="Link ảnh"
              required
              margin="normal"
              fullWidth
              variant="standard"
              helperText="(Link ảnh không được để trống)"
              value={formik.values.imageUrl}
              onChange={formik.handleChange}
            />
            {isShowImage && (
              <Box
                width={500}
                height={"fit-content"}
                minHeight={"200px"}
                sx={{
                  boxShadow: "0 0 10px 0 rgba(0, 0, 0, 0.3)",
                  borderRadius: "15px",
                  padding: "10px",
                  margin: "15px 0",
                }}
              >
                <ImageList
                  sx={{ width: "100%", height: "fit-content" }}
                  cols={1}
                >
                  <ImageListItem
                    cols={1}
                    rows={1}
                    sx={{ borderRadius: "5px", overflow: "hidden" }}
                  >
                    <img
                      src={formik.values.imageUrl}
                      alt="preview"
                      loading="lazy"
                    />
                  </ImageListItem>
                </ImageList>
              </Box>
            )}
            <Button
              className="admin-btn admin-btn--standard"
              onClick={() => {
                setIsShowImage(!isShowImage);
              }}
            >
              {isShowImage ? "Ẩn đi" : "Xem trước ảnh"}
            </Button>
          </Box>
          <TextField
            name="introduction"
            className="tad-admin-item--multiline"
            label="Giới thiệu điểm đến"
            required
            margin="normal"
            fullWidth
            multiline
            variant="standard"
            maxRows={10}
            spellCheck={false}
            helperText="(Phần giới thiệu không được để trống)"
            value={formik.values.introduction}
            onChange={formik.handleChange}
          />
          <Button
            type="button"
            className="admin-btn admin-btn--submit"
            onClick={formik.handleSubmit}
          >
            Xác nhận chỉnh sửa
          </Button>
          <Button
            type="button"
            className="admin-btn admin-btn--back"
            onClick={handleRestoreOriginal}
          >
            Phục hồi lại
          </Button>
          <Button
            type="button"
            className="admin-btn admin-btn--delete"
            onClick={handleDeleteTouristAttraction}
          >
            Xóa địa điểm này
          </Button>
        </form>
        <BlogManagerContainer blogList={formik.values.blogs} />
      </div>
    </div>
  );
};

export default TouristAttractionDetailAdmin;
