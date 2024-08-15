import React from "react";
import BlogContainer from "../../containers/global/TouristAttraction/BlogContainer";
import HelmetTitle from "../../components/helmet/HelmetTitle";

const BlogPage = () => {
  return (
    <>
      <HelmetTitle
        title={"BK Travel - Blog"}
        metaName={"meta-tourist-attraction"}
      />
      <BlogContainer />
    </>
  );
};

export default BlogPage;
