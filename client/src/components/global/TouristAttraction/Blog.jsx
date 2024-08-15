import React, { useEffect, useState } from "react";
import { GiPencil } from "react-icons/gi";

const Blog = ({ blog }) => {
  const [contents, setContents] = useState([]);

  useEffect(() => {
    setContents(blog.items);
  }, [blog]);

  useEffect(() => {
    if (blog.title) {
      document.title = `BK Travel | ${blog.title}`;
    }
  }, [blog]);

  return (
    <div className="main-session blog-container">
      <section className="blog-header">
        <div className="blog-header__image">
          <img src={blog.imageUrl} alt="" loading="lazy" />
        </div>
        <div className="blog-header__title">
          <h1 className="title">{blog.title}</h1>
          <div className="created_date">
            <span>
              {new Date(blog.createdTime).toLocaleDateString("vi-VN", {
                dateStyle: "full",
              })}
            </span>
            <span> - </span>
            <span>{blog.author}</span>
            <GiPencil />
          </div>
        </div>
      </section>
      <section className="blog-content">
        <div className="blog-content__intro">{blog.intro}</div>
        <div className="blog-content__list-item">
          {contents?.map((item, id) => (
            <div className="blog-content-item" key={item.id}>
              <h3 className="title">
                {id + 1}. {item.subTitle}
              </h3>
              <div className="blog-content__image">
                <img src={item.imageUrl} alt="" loading="lazy" />
              </div>
              <p className="text">{item.content}</p>
            </div>
          ))}
        </div>
        <div className="dividing-line">
          -------------- Kết thúc --------------
        </div>
      </section>
    </div>
  );
};

export default Blog;
