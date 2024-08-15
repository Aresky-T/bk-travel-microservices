import React, { useEffect, useState } from "react";
import TourGuideManager from "../../../components/admin/TourGuide/TourGuideManager";
import { getAllTourGuidesApi } from "../../../api/admin/tour_guide.api";
import { useAuth } from "../../../redux/selector";

const TourGuideManagerContainer = () => {
  const [data, setData] = useState([]);
  const account = useAuth();
  const [paginate, setPaginate] = useState({
    size: 10,
    pageNumber: 1,
  });

  const handleChangeCurrentPage = (page) => {
    setPaginate(page);
  };

  useEffect(() => {
    account.accessToken &&
      getAllTourGuidesApi(account.accessToken, paginate)
        .then((res) => {
          setData(res.data);
        })
        .catch((err) => {});
  }, [account, paginate]);

  return (
    <TourGuideManager
      data={data}
      handleChangeCurrentPage={handleChangeCurrentPage}
      paginate={paginate}
    />
  );
};

export default TourGuideManagerContainer;
