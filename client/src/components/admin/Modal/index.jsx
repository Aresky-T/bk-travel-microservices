import React from "react";
import { Modal, Box } from "@mui/material";
import AdminModalHeader from "./header";
import AdminModalFooter from "./footer";
import styled from "styled-components";

const AdminModalBody = styled.div({
  width: "100%",
  minHeight: "200px",
  maxHeight: "70vh",
  overflowY: "scroll",
  padding: "15px",
});

export default function AdminModalWrapper({
  isOpen,
  isShowFooter,
  handleClose,
  handleSubmit,
  title,
  children,
  style,
}) {
  return (
    <Modal
      open={isOpen ?? false}
      onClose={handleClose}
      sx={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <Box
        className="admin-modal scroll-container"
        sx={
          style ?? {
            width: "60%",
            minWidth: "600px",
            height: "fit-content",
            backgroundColor: "#fff",
            boxShadow: "0 0 25px 0px rgba(94, 94, 94, 0.2)",
            borderRadius: "7px",
            position: "relative",
          }
        }
      >
        <AdminModalHeader title={title} handleClose={handleClose} />
        <AdminModalBody className="scroll-container">{children}</AdminModalBody>
        {isShowFooter && (
          <AdminModalFooter
            handleSubmit={handleSubmit}
            handleCancel={handleClose}
          />
        )}
      </Box>
    </Modal>
  );
}
