import React, { useCallback, useEffect, useState } from "react";
import Blog from "../../../components/global/TouristAttraction/Blog";
import { useLocation } from "react-router-dom";
import { getBlogDetailsApi } from "../../../api/global/tourist_attraction.api";

const BlogContainer = () => {
  const location = useLocation();

  const [data, setData] = useState({});

  const fetchDetailsBlog = useCallback(() => {
    if (location.state && location.state.id) {
      const blogId = location.state.id;
      getBlogDetailsApi(blogId)
        .then((res) => {
          setData(res.data);
        })
        .catch((err) => {});
    }
  }, [location]);

  useEffect(() => {
    fetchDetailsBlog();
  }, [fetchDetailsBlog]);

  return <Blog blog={data} />;
};

export default BlogContainer;
