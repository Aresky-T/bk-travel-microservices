import React from "react";
import Box from "../styled/box";
import Button from "../styled/button";
import ReactQuill from "react-quill";
import styled from "styled-components";
import { BsSend } from "react-icons/bs";
import { AiOutlineDelete } from "react-icons/ai";
import { IMailForm } from "../../types/mail";
import Loading from "../loading/DualsRing";

const QillStyled = styled(ReactQuill)`
  height: 200px;
  display: flex;
  flex-direction: column;
  border-radius: 10px;
  box-shadow: 0 0 3px 0 rgba(0, 0, 0, 0.5);
  background-color: #fff;

  .ql-container {
    overflow: hidden;
    .ql-editor {
      overflow-y: scroll;

      &::-webkit-scrollbar {
        width: 3px;
      }

      &::-webkit-scrollbar-thumb {
        background-color: #4a4a4a;
        border-radius: 5px;
      }

      &::-webkit-scrollbar-track {
        background-color: #ececec;
      }
    }
  }
  div.ql-snow {
    border: none;
  }
`;

interface PropsType {
  formData: IMailForm;
  formErrors: Map<string, { message: string }>;
  isLoading: boolean;
  handleChangeForm: (name: keyof IMailForm, value: any) => any;
  handleCloseForm: () => any;
  handleSubmitForm: () => any;
}

const MailReplyForm = ({
  formData,
  formErrors,
  isLoading,
  handleChangeForm,
  handleSubmitForm,
  handleCloseForm,
}: PropsType) => {
  return (
    <Box className="reply-form-container" $width={"100%"}>
      <Box $display="flex" $flexDirection="column" $gap={8}>
        <textarea
          name="subject"
          cols={30}
          rows={1}
          placeholder="Nhập tiêu đề"
          className="cs-scroll"
          value={formData.subject || ""}
          onChange={(event) => handleChangeForm("subject", event.target.value)}
        />
        {formErrors.has("subject") && (
          <div className="reply-form--error-msg">
            {formErrors.get("subject")?.message}
          </div>
        )}
        <Box></Box>
        <QillStyled
          theme="snow"
          placeholder="Nhập nội dung"
          defaultValue=""
          value={formData.body || ""}
          onChange={(value) => handleChangeForm("body", value)}
        />
        {formErrors.has("body") && (
          <div className="reply-form--error-msg">
            {formErrors.get("body")?.message}
          </div>
        )}
      </Box>
      <Box $display="flex" $gap={20}>
        <Button
          text="Gửi"
          icon={BsSend}
          revert={true}
          className="submit"
          onClick={handleSubmitForm}
        />
        <Button
          text="Hủy"
          icon={AiOutlineDelete}
          className="cancel"
          onClick={handleCloseForm}
        />
      </Box>
      <Loading isLoading={isLoading} />
    </Box>
  );
};

export default MailReplyForm;
