import { useNavigate } from "react-router-dom";

const TouristAttractionCard = ({ data }) => {
  const navigate = useNavigate();

  return (
    <div className="tourist-attraction-card">
      <div className="tourist-attraction-card_item image">
        <img
          src={data?.imageUrl}
          alt="tourist-attraction-card-img"
          loading="lazy"
        />
      </div>
      <div className="tourist-attraction-card_item info">
        <div className="tourist-attraction-card-item_info name">
          {data?.name}
        </div>
        <div className="tourist-attraction-card-item_info intro">
          {data?.introduction || ""}
        </div>
        <div className="tourist-attraction-card-item_info action">
          <button
            onClick={() => {
              navigate(`/tourist-attraction/details?id=${data.id}`, {
                state: {
                  id: data.id,
                },
              });
            }}
          >
            Khám phá
          </button>
        </div>
      </div>
    </div>
  );
};

export default TouristAttractionCard;
