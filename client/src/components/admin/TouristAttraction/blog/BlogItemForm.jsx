import { Box, Button, FormControl, TextField } from "@mui/material";
import React, { useEffect, useState } from "react";
import ValidateUtils from "../../../../utils/validate";
import FieldMessage from "../../../global/Form/FieldMessage";

const BlogItemForm = ({ blogItem, onSubmitUpdate, onRemoveItem }) => {
  const [form, setForm] = useState({
    subTitle: "",
    imageUrl: "",
    content: "",
    isValid: false,
  });
  const [errors, setErrors] = useState(new Map());
  const [isUpdating, setIsUpdating] = useState(false);

  const handleChangeForm = ({ target: { name, value } }) => {
    if (!isUpdating) {
      setIsUpdating(true);
    }
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleValidateForm = () => {
    return ValidateUtils({
      formData: form,
      rules: {
        subTitle: {
          required: true,
        },
        content: {
          required: true,
        },
      },
      messages: {
        subTitle_required: "Tiêu đề mục không được để trống!",
        content_required: "Nội dung mục không được để trống",
      },
    });
  };

  const handleSubmitUpdate = () => {
    const { isValid, errors } = handleValidateForm();
    setErrors(errors);
    setIsUpdating(false);
    onSubmitUpdate({ ...form, isValid: isValid });
  };

  useEffect(() => {
    if (blogItem) {
      setForm((prev) => ({ ...prev, ...blogItem }));
    }
  }, [blogItem]);

  return (
    <FormControl fullWidth>
      <TextField
        type="text"
        name="subTitle"
        label="Tiêu đề"
        helperText="(Tiêu đề không được để trống)"
        variant="standard"
        fullWidth
        // required
        margin="normal"
        value={form.subTitle ?? ""}
        onChange={handleChangeForm}
        error={errors.has("subTitle")}
      />
      {/* <div className="error-message">{errors.get("subTitle")?.message}</div> */}
      <TextField
        type="text"
        name="imageUrl"
        label="Ảnh mục"
        helperText="(Ảnh mục có thể trống)"
        variant="standard"
        fullWidth
        multiline
        margin="normal"
        value={form.imageUrl ?? ""}
        onChange={handleChangeForm}
      />
      <TextField
        type="text"
        name="content"
        label="Nội dung"
        helperText="(Nội dung không được để trống)"
        variant="standard"
        fullWidth
        // required
        multiline
        margin="normal"
        value={form.content ?? ""}
        onChange={handleChangeForm}
        error={errors.has("content")}
      />
      {/* <div>{errors.get("content")?.message}</div> */}
      <FieldMessage
        isValid={blogItem.isValid}
        validMessage={"Thông tin mục hợp lệ!"}
        invalidMessage={"Thông tin mục không hợp hợp lệ hoặc chưa xác thực!"}
      />
      <Box>
        {(isUpdating || !blogItem.isValid) && (
          <Button
            type="button"
            className="admin-btn admin-btn--submit"
            onClick={handleSubmitUpdate}
          >
            Xác thực
          </Button>
        )}
        {/* {isUpdating && (
          <Button
            type="button"
            className="admin-btn admin-btn--submit"
            onClick={handleSubmitUpdate}
          >
            Xác thực
          </Button>
        )} */}
        <Button
          type="button"
          className="admin-btn admin-btn--delete"
          onClick={() => onRemoveItem(blogItem)}
        >
          Xoá bỏ mục này
        </Button>
      </Box>
    </FormControl>
  );
};

export default BlogItemForm;
