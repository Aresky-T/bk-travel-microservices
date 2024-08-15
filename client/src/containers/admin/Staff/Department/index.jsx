import React, { useEffect, useState } from "react";
import { Box } from "@mui/material";
import AdminModalWrapper from "../../../../components/admin/Modal";
import DepartmentCreateForm from "../../../../components/admin/Staff/DepartmentCreateForm";
import DepartmentList from "../../../../components/admin/Staff/DepartmentList";
import DepartmentDetailsContainer from "./DepartmentDetailsContainer";
import { getAllDepartmentsApi } from "../../../../api/admin/staff.api";

const initFormData = {
  name: {
    type: "string",
    value: "",
  },
  description: {
    type: "string",
    value: "",
  },
};

const DepartmentManagerContainer = () => {
  const [departments, setDepartments] = useState([]);
  const [selectedDept, setSelectedDept] = useState(null);
  const [isShowModal, setIsShowModal] = useState(false);
  const [departmentForm, setDepartmentForm] = useState(initFormData);

  const handleShowModal = () => setIsShowModal(true);
  const handleCloseModal = () => setIsShowModal(false);

  const handleChangeForm = (event) => {
    if (event && event.target) {
      const { name, value } = event.target;
      setDepartmentForm((prevForm) => ({
        ...prevForm,
        [name]: {
          ...prevForm[name],
          value: value,
        },
      }));
    }
  };

  const handleSubmitCreateDepartment = (event) => {
    event.preventDefault();
    const data = {
      name: departmentForm.name.value,
      description: departmentForm.description.value,
    };
    departments.push(data);
    setDepartments([...departments]);
    handleCloseModal();
  };

  const handleResetForm = () => {
    setDepartmentForm(initFormData);
  };

  const handleSelectDepartment = (dept) => {
    setSelectedDept(dept);
  };

  const fetchAllDepartments = async () => {
    try {
      const response = await getAllDepartmentsApi();
      console.log(response.data);
      setDepartments(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    fetchAllDepartments();
  }, []);

  return (
    <Box
      className="administrator-manager_main"
      sx={{
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        gap: "20px",
      }}
    >
      {selectedDept ? (
        <DepartmentDetailsContainer
          department={selectedDept}
          handleReturn={() => {
            setSelectedDept(null);
          }}
        />
      ) : (
        <DepartmentList
          departments={departments}
          handleSelect={handleSelectDepartment}
          handleShowModal={handleShowModal}
        />
      )}

      <AdminModalWrapper
        title="Tạo phòng ban"
        isOpen={isShowModal}
        handleClose={handleCloseModal}
        handleSubmit={handleSubmitCreateDepartment}
      >
        <DepartmentCreateForm
          formData={departmentForm}
          handleChange={handleChangeForm}
          handleSubmit={handleSubmitCreateDepartment}
          handleReset={handleResetForm}
        />
      </AdminModalWrapper>
    </Box>
  );
};

export default DepartmentManagerContainer;
