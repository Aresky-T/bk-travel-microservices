import React from "react";
import Home from "../../../components/global/Home";

const HomeContainer = () => {
  // const [featuredTours, setFeaturedTours] = useState([]);
  // const [featuredTouristAtt, setFeaturedTouristAtt] = useState([]);

  // useEffect(() => {
  //   getLatestToursApi(4)
  //     .then((res) => {
  //       setFeaturedTours(res.data);
  //     })
  //     .catch((err) => {
  //       console.log(err);
  //     });
  // }, []);

  // useEffect(() => {
  //   getLatestTouristAttractionsApi(4)
  //     .then((res) => {
  //       setFeaturedTouristAtt(res.data);
  //     })
  //     .catch((err) => {
  //       console.log(err);
  //     });
  // }, []);

  return <Home />;
};

export default HomeContainer;
