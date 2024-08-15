import React from "react";
import styled from "styled-components";
import { Button } from "@mui/material";

const AdminModalFooterStyled = styled.div({
  width: "100%",
  height: "70px",
  padding: "15px",
  borderTop: "1px solid #ccc",
  display: "flex",
  alignItems: "center",
  justifyContent: "flex-end",
  gap: "15px",
});

function AdminModalFooter({ handleSubmit, handleCancel }) {
  return (
    <AdminModalFooterStyled>
      <Button onClick={handleCancel}>Huỷ</Button>
      <Button className="admin-btn--submit" onClick={handleSubmit}>
        Xác nhận
      </Button>
    </AdminModalFooterStyled>
  );
}

export default AdminModalFooter;
