import { Box, Button, TextField } from "@mui/material";
import AdminModalWrapper from "../Modal";

const TouristAttractionCreate = ({
  isOpen,
  formData,
  handleChange,
  handleSubmit,
  handleHidden,
  handleCancel,
}) => {
  return (
    <AdminModalWrapper
      title={"Tạo mới Địa điểm du lịch"}
      isOpen={isOpen}
      handleClose={handleHidden}
    >
      <form className="tourist-attraction-create-form" onSubmit={handleSubmit}>
        <TextField
          label="Tên"
          name="name"
          required
          fullWidth
          margin="normal"
          value={formData.name}
          onChange={handleChange}
        />
        <TextField
          label="Link ảnh"
          name="imageUrl"
          required
          fullWidth
          margin="normal"
          value={formData.imageUrl}
          onChange={handleChange}
        />
        <TextField
          label="Giới thiệu chung"
          name="introduction"
          required
          fullWidth
          margin="normal"
          multiline
          minRows={5}
          maxRows={10}
          value={formData.introduction}
          onChange={handleChange}
        />
        <Box display={"flex"} justifyContent={"flex-end"} gap={"15px"}>
          <Button
            type="reset"
            className="admin-btn--delete"
            onClick={handleCancel}
          >
            Huỷ bỏ
          </Button>
          <Button type="submit" className="admin-btn--submit">
            Xác nhận
          </Button>
        </Box>
      </form>
    </AdminModalWrapper>
  );
};

export default TouristAttractionCreate;
