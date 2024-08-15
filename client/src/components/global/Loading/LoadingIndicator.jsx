import React, { useEffect, useState } from "react";

const LoadingIndicator = ({ text }) => {
  const [dots, setDots] = useState(".");

  useEffect(() => {
    const interval = setInterval(() => {
      setDots((dots) => {
        if (dots === "...") {
          return "";
        } else {
          return dots + ".";
        }
      });
    }, 500);
    return () => clearInterval(interval);
  }, []);

  return (
    <div>
      {text || "Đang tải dữ liệu"}
      {dots}
    </div>
  );
};

export default LoadingIndicator;
