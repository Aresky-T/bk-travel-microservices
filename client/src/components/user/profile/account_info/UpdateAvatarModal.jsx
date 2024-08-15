import React, { useRef, useState } from "react";
import { styled } from "@mui/material";
import { useDispatch } from "react-redux";
import toast from "react-hot-toast";
import { useAuth } from "../../../../redux/selector";
import { offLoading, onLoading } from "../../../../redux/slices/loading.slice";
import { uploadSingleFileApi } from "../../../../api/global/file.api";
import { updateAvatarApi } from "../../../../api/global/profile.api";
import { getProfileThunk } from "../../../../redux/slices/profile.slice";

const CustomModal = styled("div")({
  width: "100vw",
  height: "100vh",
  backgroundColor: "#cccccc59",

  position: "fixed",
  top: 0,
  left: 0,
  zIndex: 1000,

  display: "flex",
  justifyContent: "center",

  ".update-avatar-modal": {
    width: "500px",
    height: "fit-content",
    borderRadius: "15px",
    backgroundColor: "#fff",
    marginTop: "100px",
    padding: "15px",
    boxShadow: "0 0 5px rgba(0,0,0, .5)",
    animation: "0.5s dropdownModal forwards",
  },

  ".update-avatar__choose-file": {
    width: "100%",
    height: "100px",
    borderRadius: "10px",
    border: "2px dashed #ccc",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    cursor: "pointer",
    fontWeight: 600,
    transition: "all 0.2s linear",

    "&:hover": {
      borderStyle: "solid",
      borderColor: "var(--primary-color)",
    },
  },

  ".update-avatar__file-selected": {
    width: "100%",
    height: "300px",
    overFlow: "hidden",
    padding: "15px",
    backgroundColor: "var(--font-color2)",
    borderRadius: "12px",

    "#update-avatar__img": {
      objectFit: "cover",
      width: "100%",
      height: "100%",
      borderRadius: "9px",
      border: "none",
    },
  },

  ".update-avatar-modal__btn-area": {
    width: "100%",
    marginTop: "25px",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    gap: "15px",

    ".update-avatar-modal__btn--submit": {
      backgroundColor: "var(--font-color2)",
      color: "#fff",

      "&:hover": {
        backgroundColor: "var(--primary-color)",
      },
    },

    ".update-avatar-modal__btn--cancel": {
      border: "1px solid #ccc",
      fontWeight: 600,

      "&:hover": {
        backgroundColor: "var(--button-color)",
        color: "#fff",
      },
    },
  },
});

// const ModalButton = styled("button")({
//   padding: "7px 15px",
//   border: "none",
//   outline: "none",
//   borderRadius: "5px",
//   margin: "0 10px",
//   cursor: "pointer",
//   transition: "all 50ms linear",
//   letterSpacing: "1px",
// });

const UpdateAvatarModal = ({ handleCloseModal }) => {
  const [selectedFile, setSelectedFile] = useState();
  const auth = useAuth();
  const accountId = auth.id;
  const fileRef = useRef();
  const dispatch = useDispatch();

  const handleClickChooseFile = () => {
    if (fileRef.current) {
      fileRef.current.click();
    }
  };

  const handleChangeFile = (e) => {
    const file = e.target.files[0];
    if (file) {
      const fileType = file.name.split(".").pop().toLowerCase();
      if (
        fileType !== "png" &&
        fileType !== "jpg" &&
        fileType !== "jpeg" &&
        fileType !== "avif"
      ) {
        toast.error("Chỉ cho phép tệp tin định dạng PNG, JPG, JPEG hoặc AVIF.");
        e.target.value = null;
        return;
      }

      file.url = URL.createObjectURL(file);
      setSelectedFile(file);
    }
  };

  const handleSubmit = () => {
    if (!selectedFile) {
      toast("Vui lòng chọn ảnh trước khi cập nhật!", { icon: "⚠️" });
      return;
    }

    dispatch(onLoading());
    uploadSingleFileApi(selectedFile)
      .then((res) => {
        return res.data.secure_url;
      })
      .then((url) => {
        updateAvatarApi(accountId, url)
          .then((res) => {
            dispatch(offLoading());
            handleCloseModal();
            toast.success("Cập nhật ảnh đại diện thành công thành công!");
            dispatch(getProfileThunk(accountId));
          })
          .catch((err) => {
            dispatch(offLoading());
            handleCloseModal();
            toast.error("Cập nhật ảnh đại diện thất bại!");
          });
      })
      .catch((err) => {
        handleCloseModal();
        toast.error("Cập nhật ảnh đại diện thất bại!");
        dispatch(offLoading());
      });
  };

  return (
    <CustomModal>
      <div className="update-avatar-modal">
        {selectedFile ? (
          <div className="update-avatar__file-selected">
            <img src={selectedFile.url} alt="" id="update-avatar__img" />
          </div>
        ) : (
          <div
            className="update-avatar__choose-file"
            onClick={handleClickChooseFile}
          >
            Click để chọn file
          </div>
        )}
        <input
          type="file"
          name=""
          id=""
          style={{
            display: "none",
          }}
          accept=".png, .jpg, .jpeg, .avif"
          ref={fileRef}
          onChange={handleChangeFile}
        />
        <div className="update-avatar-modal__btn-area">
          <button className="profile-btn submit" onClick={handleSubmit}>
            Cập nhật
          </button>
          <button className="profile-btn cancel" onClick={handleCloseModal}>
            Hủy bỏ
          </button>
        </div>
      </div>
    </CustomModal>
  );
};

export default UpdateAvatarModal;
