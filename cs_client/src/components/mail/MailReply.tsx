import React from "react";
import { IMailReply } from "../../types/mail";
import Box from "../styled/box";
import { useAppContext } from "../../store/context";
import Avatar from "../customer/Avatar";
import Text from "../styled/text";
import { BiSolidDownArrow, BiSolidRightArrow } from "react-icons/bi";

interface PropsType {
  mailReply: IMailReply;
}

const defaultAvatar =
  "https://static.vecteezy.com/system/resources/previews/008/442/086/non_2x/illustration-of-human-icon-user-symbol-icon-modern-design-on-blank-background-free-vector.jpg";

const MailReply: React.FC<PropsType> = ({ mailReply }) => {
  const staff = useAppContext().state.mail.staff;

  return (
    <Box
      style={{
        borderTop: "1px solid #ccc",
        width: "100%",
        padding: "10px 0",
      }}
    >
      <Box $display="flex" $gap={15} $alignItems="flex-start">
        <Box $width={40} $minWidth={40} $height={40} $minHeight={40}>
          <Avatar type="url" url={staff?.avatarUrl || defaultAvatar} />
        </Box>
        <Box $color="#5E5E5E" $lineHeight={1.5}>
          <Text>
            <b>{staff?.fullName}</b>{" "}
            <Text
              $display="inline"
              $fontSize={".7rem"}
            >{`<${staff?.email}>`}</Text>
          </Text>
          <Text $fontSize={"0.7rem"}>
            Đã trả lời lúc{" "}
            {new Date(mailReply.repliedAt).toLocaleString("vi-VN", {
              timeStyle: "short",
              dateStyle: "medium",
            })}
          </Text>
          <Box
            $color="#000"
            $marginTop={20}
            $borderLeft={"1px solid #ccc"}
            $paddingLeft={10}
          >
            <Box $display="flex" $alignItems="center" $gap={10}>
              <Box $minWidth={60}>Tiêu đề</Box>
              <BiSolidRightArrow color="var(--gray-color)" />
              <b>{mailReply.subject}</b>
            </Box>
            <Box
              $display="flex"
              $alignItems="center"
              $gap={10}
              $margin={"10px 0"}
            >
              <Box $minWidth={60}>Nội dung</Box>
              <BiSolidDownArrow color="var(--gray-color)" />
            </Box>
            {React.createElement("div", {
              dangerouslySetInnerHTML: { __html: mailReply.body },
            })}
          </Box>
        </Box>
      </Box>
    </Box>
  );
};

export default MailReply;
