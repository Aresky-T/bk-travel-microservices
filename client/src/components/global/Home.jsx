import React, { useEffect, useRef } from "react";
import vnImage from "../../assets/image/Flag-map_of_Vietnam.png";
import video1 from "../../assets/video/video1.mp4";
import video2 from "../../assets/video/video2.mp4";
import video3 from "../../assets/video/video3.mp4";
// import TouristAttractionCard from "./TouristAttraction/TouristAttractionCard";
// import LoadingIndicator from "./Loading/LoadingIndicator";
// import TourCardContainer from "../../containers/global/Tour/card";

const destinations = [
  {
    id: 1,
    name: "Hà Nội",
    image:
      "https://static.vinwonders.com/production/quang-truong-ba-dinh-so-6.jpg",
  },
  {
    id: 2,
    name: "Đà Nẵng",
    image:
      "https://baovephapluat.vn/data/images/0/2019/05/29/hienbt/cau-vang-sun-world-ba-na-hills-6.jpg",
  },
  {
    id: 3,
    name: "Đà Lạt",
    image:
      "https://static-images.vnncdn.net/files/publish/2022/8/6/cao-dang-su-pham4-552.jpg",
  },
  {
    id: 4,
    name: "Phú Quốc",
    image:
      "https://dulich3mien.vn/wp-content/uploads/2022/09/Bai-Truong-Phu-Quoc-1-1200x959.jpg",
  },
];

const Home = ({ featuredTours, touristAttractions }) => {
  const videosRef = useRef();

  useEffect(() => {
    const videos = document.querySelectorAll(".hero_image-box video");

    const timer = setTimeout(() => {
      for (const video of videos) {
        if (video.paused) {
          video.muted = true;
          video.play();
        }
      }
    }, 2000);

    return () => clearTimeout(timer);
  }, []);

  return (
    <div className="main-session home-container">
      <section className="home_header">
        <div className="home_header_left">
          <div className="subtitle">
            <span className="title">Đưa bạn đến mọi miền đất nước</span>
            <img src={vnImage} alt="vn" />
          </div>
          <h1>
            Du lịch mở ra cánh cửa để đem lại những{" "}
            <span className="highlight">kỷ niệm</span>
          </h1>
          <p>
            Việt Nam - đất nước thiên nhiên tuyệt đẹp, con người thân thiện.
            <br />
            Hãy đi và khám phá vẻ đẹp của núi rừng, biển cả và cảnh quan từ phố
            thị tới đồng quê. Trải nghiệm văn hóa và sự ấm áp từ người dân mọi
            miền.
          </p>
        </div>
        <div className="home_header_right" ref={videosRef}>
          <div className="hero_image-box">
            <video className="video_1" loop>
              <source src={video1} type="video/mp4" />
            </video>
          </div>
          <div className="hero_image-box">
            <video className="video_2" loop>
              <source src={video2} type="video/mp4" />
            </video>
          </div>
          <div className="hero_image-box">
            <video className="video_3" loop>
              <source src={video3} type="video/mp4" />
            </video>
          </div>
        </div>
      </section>
      <section className="home_main">
        <div className="featured destination">
          <div className="subtitle">
            <span className="title">Điểm đến nổi bật</span>
            <h1>Những điểm đến hấp dẫn ở khắp mọi miền</h1>
          </div>
          <div className="featured-destinations">
            {destinations.map((des) => (
              <div className="featured-destination" key={des.id}>
                <div className="featured-destination-img">
                  <img src={des.image} alt="destination-img" loading="lazy" />
                </div>
                <div className="featured-destination-name">
                  <b>{des.name}</b>
                </div>
                <div className="featured-destination-discovery">
                  <button>Khám phá</button>
                </div>
              </div>
            ))}
          </div>
          {/* {touristAttractions.length > 0 ? (
            <div className="featured-list featured__list-2">
              {[...touristAttractions].map((item) => (
                <TouristAttractionCard data={item} key={item.id} />
              ))}
            </div>
          ) : (
            <LoadingIndicator />
          )} */}
        </div>
        {/* <div className="featured tour">
          <div className="subtitle">
            <span className="title">Khám phá</span>
            <h1>Tour nổi bật</h1>
          </div>
          {featuredTours.length > 0 ? (
            <div className="featured-list featured__list-1">
              {[...featuredTours].map((tour) => (
                <TourCardContainer tour={tour} key={tour.id} />
              ))}
            </div>
          ) : (
            <LoadingIndicator />
          )}
        </div> */}
      </section>
    </div>
  );
};

export default Home;
