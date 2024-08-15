import React from "react";
import styled from "styled-components";
import { IoClose } from "react-icons/io5";

const AdminModalHeaderStyled = styled.div({
  width: "100%",
  height: "fit-content",
  display: "flex",
  justifyContent: "space-between",
  alignItems: "center",
  padding: "15px",
  borderBottom: "1px solid #ccc",
  fontFamily:
    "system-ui, -apple-system, BlinkMacSystemFont, Roboto, sans-serif",

  ".admin-modal_header-title": {
    fontSize: "1.3rem",
    fontWeight: 600,
    userSelect: "none",
    color: "var(--third-color)",
    textTransform: "uppercase",
  },

  ".admin-modal_close-btn": {
    width: "40px",
    height: "40px",
    borderRadius: "50%",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#e6e6e6",
    cursor: "pointer",

    "&:hover": {
      backgroundColor: "var(--gray-color)",
    },

    svg: {
      fontSize: "25px",
    },
  },
});

export default function AdminModalHeader({ title, handleClose }) {
  return (
    <AdminModalHeaderStyled className="admin-modal_header">
      <h1 className="admin-modal_header-title">{title || "Modal"}</h1>
      <div
        className="admin-modal_close-btn"
        onClick={() => {
          handleClose();
        }}
      >
        <IoClose />
      </div>
    </AdminModalHeaderStyled>
  );
}
