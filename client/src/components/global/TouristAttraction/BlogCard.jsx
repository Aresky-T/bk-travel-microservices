import React from "react";
import { useNavigate } from "react-router-dom";
import { ROUTE } from "../../../constant/route";

const BlogCard = ({ blog }) => {
  const navigate = useNavigate();

  return (
    <div className="blog-card">
      <div className="blog-card-item blog-card-image">
        <img src={blog.imageUrl} alt="blog-img" />
      </div>
      <div className="blog-card-info">
        <div className="blog-card-info_item title">{blog.title || ""}</div>
        <div className="blog-card-info_item intro">{blog.intro || ""}</div>
        <div className="blog-card-info_item action">
          <button
            onClick={() => {
              navigate(`${ROUTE.TOURIST_ATTRACTION_BLOG}?id=${blog.id}`, {
                state: {
                  id: blog.id,
                },
              });
            }}
          >
            Đọc bài viết
          </button>
        </div>
      </div>
    </div>
  );
};

export default BlogCard;
