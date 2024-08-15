import { Button } from "@mui/material";
import React from "react";

const PositionList = ({ positions }) => {
  return (
    <div className="department-details__position-list">
      <table className="admin-table">
        <thead>
          <tr>
            <th>STT</th>
            <th>Name</th>
            <th>Số vị trí</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {positions?.map((position, index) => (
            <tr key={index}>
              <td>{index + 1}</td>
              <td>{position.name || "Không xác định"}</td>
              <td>{position.headcount}</td>
              <td>
                <Button>Xem</Button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default PositionList;
