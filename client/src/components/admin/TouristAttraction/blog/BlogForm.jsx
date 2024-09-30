import { Box, Button, TextField } from "@mui/material";
import React, { useMemo, useRef } from "react";
import { TiFlowChildren } from "react-icons/ti";
import BlogItemForm from "./BlogItemForm";
import FieldMessage from "../../../global/Form/FieldMessage";

const BlogForm = ({
  title,
  formData,
  blogItems,
  onChange,
  onClose,
  onAddNewItem,
  onUpdateItem,
  onRemoveItem,
  onSubmitCreate,
  onSubmitUpdate,
}) => {
  const blogRef = useRef();
  const blogItemEndRef = useRef();
  const isExistCurrentBlog = formData.id;

  const blogItemsRender = useMemo(() => {
    return blogItems
      .filter((item) => !item.isDelete)
      .map((item, index) => (
        <Box
          borderBottom={"2px dashed var(--primary-color)"}
          padding={"15px 0"}
          key={index}
        >
          <Box display={"flex"} alignItems={"center"} gap={"10px"}>
            <TiFlowChildren size={"1.3rem"} color="var(--primary-color)" />
            <span>
              <strong>Mục {index + 1}</strong>
            </span>
          </Box>
          <BlogItemForm
            onRemoveItem={onRemoveItem}
            blogItem={item}
            onSubmitUpdate={onUpdateItem}
          />
        </Box>
      ));

    //eslint-disable-next-line
  }, [blogItems]);

  // useEffect(() => {
  //   const handleMouseDown = (event) => {
  //     if (blogRef.current && !blogRef.current.contains(event.target)) {
  //       onClose();
  //     }
  //   };

  //   document.addEventListener("mousedown", (event) => handleMouseDown(event));

  //   return () => {
  //     document.removeEventListener("mousedown", handleMouseDown, true);
  //   };

  //   // eslint-disable-next-line
  // }, []);

  // useEffect(() => {
  //   if (blogItemEndRef.current) {
  //     blogItemEndRef.current.scrollIntoView({ behavior: "smooth" });
  //   }
  // }, [formData]);

  return (
    <div className="blog-form scroll-container" ref={blogRef}>
      <h2 className="blog-form__title">{String(title).toUpperCase()}</h2>
      <h3>I. Thông tin mở đầu</h3>
      <form
        onSubmit={(event) => {
          event.preventDefault();
          if (isExistCurrentBlog) {
            onSubmitUpdate();
          } else {
            onSubmitCreate();
          }
        }}
      >
        <TextField
          type="text"
          name="title"
          label="Tiêu đề"
          helperText="(Tiêu đề không được để trống)"
          variant="standard"
          fullWidth
          // required
          margin="normal"
          value={formData.title.value ?? ""}
          onChange={onChange}
          error={!formData.title.isValid}
        />
        <FieldMessage
          isValid={formData.title.isValid}
          validMessage={"Tiêu đề hợp lệ!"}
          invalidMessage={"Tiêu đề không hợp lệ hoặc chưa được xác thực!"}
        />
        <TextField
          type="text"
          name="imageUrl"
          label="Link ảnh bìa"
          helperText="(Link ảnh bìa không được để trống)"
          variant="standard"
          fullWidth
          // required
          margin="normal"
          value={formData.imageUrl.value ?? ""}
          onChange={onChange}
          error={!formData.imageUrl.isValid}
        />
        <FieldMessage
          isValid={formData.imageUrl.isValid}
          validMessage={"Link ảnh bìa hợp lệ!"}
          invalidMessage={"Link ảnh bìa không hợp lệ hoặc chưa được xác thực!"}
        />
        <TextField
          type="text"
          name="intro"
          label="Phần mở đầu"
          helperText="(Phần mở đầu không được để trống)"
          fullWidth
          // required
          multiline
          variant="standard"
          margin="normal"
          value={formData.intro.value ?? ""}
          onChange={onChange}
          error={!formData.intro.isValid}
        />
        <FieldMessage
          isValid={formData.intro.isValid}
          validMessage={"Phần mở đầu hợp lệ!"}
          invalidMessage={"Phần mở đầu không hợp lệ hoặc chưa được xác thực!"}
        />
        <Box>
          <h3>II. Danh sách mục</h3>
          <Box
            margin={"15px 0"}
            fontStyle={"italic"}
            fontWeight={600}
            color={"var(--font-color2)"}
          >
            (Danh sách mục không được để trống)
          </Box>
          <Box>{blogItemsRender}</Box>
        </Box>
        <div ref={blogItemEndRef}></div>
        <Button
          className="admin-btn admin-btn--create"
          onClick={() => {
            onAddNewItem();
            if (blogItemEndRef.current) {
              blogItemEndRef.current.scrollIntoView({ behavior: "smooth" });
            }
          }}
        >
          + Thêm mục
        </Button>
        <Box>
          {isExistCurrentBlog ? (
            <>
              <Button type="submit" className="admin-btn admin-btn--submit">
                Cập nhật bài viết
              </Button>
              <Button
                type="button"
                className="admin-btn admin-btn--delete"
                onClick={onClose}
              >
                Huỷ cập nhật
              </Button>
            </>
          ) : (
            <>
              <Button type="submit" className="admin-btn admin-btn--submit">
                Xác nhận tạo mới
              </Button>
              <Button
                type="button"
                className="admin-btn admin-btn--delete"
                onClick={onClose}
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

export default BlogForm;
