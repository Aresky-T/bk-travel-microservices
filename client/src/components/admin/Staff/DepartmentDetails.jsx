const DepartmentDetails = ({ department }) => {
  return (
    <div className="department-details">
      <div className="department-details_item __name">
        <h4>{department.name || "None"}</h4>
      </div>
      <div className="department-details_item __description">
        {department.description || "None"}
      </div>
      <div className="department-details_item title">
        Trưởng phòng:{" "}
        <strong>{department.manager?.name || "Chưa xác định"}</strong>
      </div>
    </div>
  );
};

export default DepartmentDetails;
