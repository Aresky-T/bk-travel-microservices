import React, { useEffect, useState } from "react";

const CustomPaginate = ({
  currentPage,
  totalPages,
  setCurrentPage,
  firstLabel,
  lastLabel,
}) => {
  const [pages, setPages] = useState([1]);

  const handleChangeCurrentPage = (page) => {
    if (page === firstLabel) {
      setCurrentPage(1);
    }
    if (page === lastLabel) {
      setCurrentPage(totalPages);
    }
    typeof page === "number" && setCurrentPage(page);
  };

  const loadPages = (currentPage, totalPages, firstLabel, lastLabel) => {
    const loadPages = [];
    const startIndex = Math.max(currentPage - 5, 1);
    const endIndex = Math.min(startIndex + 4, totalPages);

    if (startIndex > 1) {
      loadPages.unshift(firstLabel);
    }

    for (let i = startIndex; i <= totalPages; i++) {
      loadPages.push(i);
    }

    if (endIndex < totalPages) {
      loadPages.push(lastLabel);
    }

    return loadPages;
  };

  useEffect(() => {
    const pages = loadPages(currentPage, totalPages, firstLabel, lastLabel);
    setPages(pages);
  }, [currentPage, firstLabel, lastLabel, totalPages]);

  return (
    <div className="paginate">
      {pages?.map((page) => (
        <button
          key={page}
          onClick={() => handleChangeCurrentPage(page - 1)}
          className={
            currentPage === page - 1 ? "paginate-item active" : "paginate-item"
          }
        >
          {page}
        </button>
      ))}
    </div>
  );
};

export default CustomPaginate;
