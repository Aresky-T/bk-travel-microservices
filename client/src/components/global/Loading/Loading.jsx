import React from "react";
import { useLoading } from "../../../redux/selector";

const Loading = () => {
  const isLoading = useLoading();

  return (
    <>
      {isLoading && (
        <div className="loading-container">
          <div className="lds-dual-ring"></div>
        </div>
      )}
    </>
  );
};

export default Loading;
