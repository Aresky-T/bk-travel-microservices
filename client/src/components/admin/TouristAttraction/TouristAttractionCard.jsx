import { Button } from "@mui/material";
import React from "react";
import { useDispatch } from "react-redux";
import { onUpdateAdminTouristAttraction } from "../../../redux/slices/admin.slice";

const TouristAttractionCard = ({ touristAttraction }) => {
  const dispatch = useDispatch();

  return (
    <div className="admin-tourist-attraction-card">
      <div className="admin-tourist-attraction-card_image">
        <img
          src={touristAttraction.imageUrl}
          alt="admin-tourist-attraction-card-img"
          loading="lazy"
        />
      </div>
      <div className="admin-tourist-attraction-card_info">
        <div className="tourist-attraction-name">{touristAttraction.name}</div>
        <div className="admin-tourist-attraction-card_action">
          <Button
            className="admin-btn--standard"
            onClick={() => {
              dispatch(onUpdateAdminTouristAttraction(touristAttraction));
            }}
          >
            Xem
          </Button>
        </div>
      </div>
    </div>
  );
};

export default TouristAttractionCard;
