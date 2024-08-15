import React, { useState } from "react";
import { useAdmin } from "../../../../redux/selector";
import { Button } from "@mui/material";
import BlogCard from "../../../../components/admin/TouristAttraction/blog/BlogCard";
import BlogFormContainer from "./BlogFormContainer";
import { useDispatch } from "react-redux";
import { onUpdateAdminCurrentBlog } from "../../../../redux/slices/admin.slice";

const BlogManagerContainer = ({ blogList }) => {
  const [isShowForm, setIsShowForm] = useState(false);

  const { touristAttractionManager } = useAdmin();
  const { currentBlog } = touristAttractionManager;

  const isEmptyBlogList = blogList && blogList.length === 0;

  const dispatch = useDispatch();

  const handleShowForm = () => setIsShowForm(true);

  const handleHiddenForm = () => {
    if (currentBlog) {
      dispatch(onUpdateAdminCurrentBlog(null));
    }
    setIsShowForm(false);
  };

  return (
    <div className="blog-manager-container">
      <h2 className="blog-manager-container-title">Danh sách bài viết</h2>
      <Button
        type="button"
        className="admin-btn--create"
        onClick={handleShowForm}
      >
        + Thêm bài viết mới
      </Button>
      <div className="blog-list--admin">
        {isEmptyBlogList ? (
          <div className="blog-list--admin--empty">Danh sách trống!</div>
        ) : (
          <>
            {[...blogList].map((item, index) => (
              <BlogCard
                blog={item}
                key={index}
                onViewBlogDetails={() => {
                  dispatch(onUpdateAdminCurrentBlog(item));
                  handleShowForm();
                }}
              />
            ))}
          </>
        )}
      </div>
      <BlogFormContainer
        isShowForm={isShowForm}
        onHiddenForm={handleHiddenForm}
      />
    </div>
  );
};

export default BlogManagerContainer;
