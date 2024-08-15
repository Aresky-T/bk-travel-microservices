import { Button } from "@mui/material";
import React from "react";
import { IoTimerOutline } from "react-icons/io5";
import { MdOutlineMarkChatUnread } from "react-icons/md";

const BlogCard = ({ blog, onViewBlogDetails }) => {
  return (
    <div className="blog-card--admin">
      <div className="blog-card__border--admin"></div>
      <div className="blog-card__image--admin">
        <img src={blog.imageUrl} alt="" />
      </div>
      <div className="blog-card__item title">
        <span>{blog.title}</span>
      </div>
      <div className="blog-card__item">
        <span className="icon">
          <MdOutlineMarkChatUnread />
        </span>
        <span className="data">{blog.intro}</span>
      </div>
      <div className="blog-card__item">
        <span className="icon">
          <IoTimerOutline />
        </span>
        <span className="data">
          Ngày tạo:{" "}
          <strong>
            {new Date(blog.createdTime)
              .toLocaleString("vi-VN", {
                dateStyle: "short",
                timeStyle: "short",
              })
              .toUpperCase()}
          </strong>
        </span>
      </div>
      <div className="blog-card__item">
        <Button
          className="admin-btn--standard"
          onClick={() => onViewBlogDetails(blog)}
        >
          Xem chi tiết
        </Button>
      </div>
    </div>
  );
};

export default BlogCard;
