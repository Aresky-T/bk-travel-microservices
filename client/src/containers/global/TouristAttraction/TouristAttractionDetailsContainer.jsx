import React, { useCallback, useEffect, useState } from "react";
import { GiPencil } from "react-icons/gi";
import { useLocation, useNavigate } from "react-router-dom";
import { getTouristAttractionDetailsApi } from "../../../api/global/tourist_attraction.api";
import BlogCard from "../../../components/global/TouristAttraction/BlogCard";

const TouristAttractionDetailsContainer = () => {
  const [touristAttraction, setTouristAttraction] = useState({});
  const [touristAttractionId, setTouristAttractionId] = useState(null);
  const [blogs, setBlogs] = useState([]);
  const location = useLocation();
  const navigate = useNavigate();

  const fetchTouristAttraction = useCallback(() => {
    if (touristAttractionId) {
      getTouristAttractionDetailsApi(touristAttractionId)
        .then((res) => {
          setTouristAttraction(res.data);
          [...res.data.blogs].length > 0 && setBlogs(res.data.blogs);
        })
        .catch((err) => {});
    }
  }, [touristAttractionId]);

  useEffect(() => {
    fetchTouristAttraction();
  }, [fetchTouristAttraction]);

  useEffect(() => {
    if (location.state && location.state.id) {
      const id = location.state.id;
      setTouristAttractionId(id);
    } else {
      navigate("/not-found-page");
    }
    //eslint-disable-next-line
  }, [location]);

  return (
    <div className="main-session tourist-attraction-details-container">
      <section className="main-session-header tourist-attraction-details-header">
        <div className="header-item tourist-attraction-details-header__image">
          <img src={touristAttraction.imageUrl} alt="" />
        </div>
        <div className="tourist-attraction-details-header__title">
          <div className="created_date">
            <span>
              {new Date().toLocaleDateString("vi-VN", { dateStyle: "full" })}
            </span>
            <GiPencil />
          </div>
          <h1 className="title">{touristAttraction.name}</h1>
        </div>
      </section>
      <section className="tourist-attraction-details-content">
        <div className="tourist-attraction-details-content_item intro">
          {touristAttraction.introduction ||
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi at massa facilisis, dignissim sem vel, fringilla massa. Proin eu aliquet ligula. Vestibulum ac mattis orci, nec lacinia velit. Duis pretium gravida nunc, at lacinia est. Cras ut arcu in magna varius dictum ut ut felis. Vestibulum a condimentum felis. Suspendisse posuere facilisis dictum. Praesent elementum massa eu nisi lacinia euismod."}
        </div>
        <div className="tourist-attraction-details-content_item blogs">
          {blogs?.map((item, id) => (
            <BlogCard blog={item} key={id} />
          ))}
        </div>
        <div className="dividing-line">
          -------------- Kết thúc --------------
        </div>
      </section>
    </div>
  );
};

export default TouristAttractionDetailsContainer;
