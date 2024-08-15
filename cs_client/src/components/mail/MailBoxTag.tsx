import React from "react";
import Box from "../styled/box";
import { IMailBox } from "../../types/mail";
import Avatar from "../customer/Avatar";
import { useAppContext } from "../../store/context";

interface PropsType {
  mailBox: IMailBox;
}

const MailBoxTag: React.FC<PropsType> = ({ mailBox }) => {
  const { customer } = mailBox;
  const { dispatch, state } = useAppContext();
  const { currentMailBox } = state.mail;
  const isCurrent = currentMailBox?.id === mailBox.id;

  return (
    <Box
      className={isCurrent ? "tag current" : "tag"}
      onClick={() => {
        if (dispatch) {
          dispatch({ type: "MAIL|REMOVE|CURRENT_MAIL" });
          dispatch({ type: "MAIL|RESET|MAIL_FORM" });
          dispatch({ type: "MAIL|SET|CURRENT_MAIL_BOX", payload: mailBox });
        }
      }}
    >
      <Box className="tag-avatar">
        <Avatar
          type="url"
          url="https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png"
        />
        {mailBox.unrepliedMailCount > 0 && (
          <Box
            className="flex-center"
            $position="absolute"
            $bottom={0}
            $right={"-2px"}
            $fontSize={".6rem"}
            $backgroundColor="var(--red-color)"
            $border="3px solid #fff"
            $color="#fff"
            $fontWeight={600}
            $borderRadius={"50%"}
            $width={20}
            $height={20}
          >
            {mailBox.unrepliedMailCount}
          </Box>
        )}
      </Box>
      <Box className="mail-box-tag_info">
        <div>{customer.fullName}</div>
        <div>{customer.email}</div>
      </Box>
    </Box>
  );
};

export default MailBoxTag;
