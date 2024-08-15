import React from "react";
import { FiSearch } from "react-icons/fi";

const initResult = [
  {
    id: 1,
    name: "Thủ đô Hà Nội",
    imageUrl:
      "https://ticotravel.com.vn/wp-content/uploads/2022/06/pho-ta-hien-5.jpg",
  },
  {
    id: 2,
    name: "Thành phố Đà Lạt",
    imageUrl: "https://gonatour.vn/vnt_upload/news/08_2021/du_lich_da_lat.jpg",
  },
  {
    id: 3,
    name: "Đà Nẵng",
    imageUrl:
      "https://tiki.vn/blog/wp-content/uploads/2023/03/WSZwZ6dJ4Z3mmNH73Ctti4EVpYizqFn1zyUL8qgcW_44I0QJYkF6sTtaBIT2ALPEErT8lXh-VJhfQFSillCAEW-yaPiRq14CJGaQ66b7yF4jQ-u2fcsYG6aaAMj7sQoAx4QJiSzaLSC2.png",
  },
  {
    id: 4,
    name: "Phú Quốc",
    imageUrl:
      "https://nld.mediacdn.vn/291774122806476800/2021/9/7/3-chot-3-1630978256337659996993.jpg",
  },
  {
    id: 5,
    name: "Thanh Hóa",
    imageUrl: "https://smjahome.vn/wp-content/uploads/2021/07/cau-ham-rong.jpg",
  },
  {
    id: 6,
    name: "Cần Thơ",
    imageUrl:
      "https://tourismcantho.vn/files/images/diem-den/diem-du-lich/aa.jpg",
  },
  {
    id: 7,
    name: "Cao bằng",
    imageUrl: "https://hnm.1cdn.vn/2023/10/03/1(2).jpg",
  },
  {
    id: 9,
    name: "test",
    imageUrl: "test-url",
  },
  {
    id: 10,
    name: "test 2",
    imageUrl: "test-url-2",
  },
  {
    id: 11,
    name: "test 3",
    imageUrl: "test-url-3",
  },
];

const SearchContainer = ({
  search,
  handleChange,
  handleClose,
  handleSubmit,
  result,
}) => {
  return (
    <div className={`search-container`}>
      <div className="search">
        <div className="search-bar">
          <input
            type="text"
            name="search"
            value={search}
            onChange={handleChange}
            placeholder="Tìm kiếm tên địa danh..."
          />
          <span className="search-icon" onClick={handleSubmit}>
            <FiSearch />
          </span>
        </div>
        <div className="search-result scroll-container">
          {initResult.map((item) => (
            <div className="search-result-item" key={item.name}>
              <div className="search-result-item_image">
                <img src={item.imageUrl} alt="img" />
              </div>
              <div className="search-result-item_info">
                <div className="name">{item.name}</div>
                <div className="blogs">10 bài viết</div>
              </div>
            </div>
          ))}
        </div>
        <div className="search-close">
          <button onClick={handleClose}>Thoát</button>
        </div>
      </div>
    </div>
  );
};

export default SearchContainer;
