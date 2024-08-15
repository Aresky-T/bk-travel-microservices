import { useMemo } from "react";
import Box from "../../../components/styled/box";
import { useAppContext } from "../../../store/context";
import { IMail } from "../../../types/mail";
import { EMailStatus } from "../../../types/enum";

interface PropsType {
  mail: IMail;
}

const MailTagContainer: React.FC<PropsType> = ({ mail }) => {
  const dispatch = useAppContext().dispatch;

  const mailContent = useMemo(() => {
    return `${mail.body}`;
  }, [mail]);

  const mailStatus = useMemo(() => {
    switch (mail.status) {
      case EMailStatus.NEW:
        return "Mới";
      case EMailStatus.READ:
        return "Chưa trả lời";
      case EMailStatus.REPLIED:
        return "Đã trả lời";
    }
  }, [mail]);

  return (
    <Box className={`mail-tag ${mail.status}`}>
      <Box
        key={mail.id}
        $transition="all 50ms linear"
        className="mail-tag read"
        onClick={() => {
          dispatch &&
            dispatch({ type: "MAIL|SET|CURRENT_MAIL", payload: mail });
        }}
      >
        <Box
          className={`mail-tag__header`}
          $paddingInline={15}
          $height={50}
          $backgroundColor="#fff"
          $boxShadow="0 0 1px 0 rgba(0, 0, 0, .5)"
          $fontSize={".8rem"}
          $display="flex"
          $alignItems="center"
          $justifyContent="space-between"
          $gap={50}
          $cursor="pointer"
          $transition="all 100ms linear"
        >
          <Box
            $display="flex"
            $alignItems="center"
            $gap={5}
            className="mail-tag__header--title-n-content"
          >
            <span className="title">{mail.subject}</span>
            <span className="content">{mailContent}</span>
          </Box>
          <Box
            $display="flex"
            $alignItems="center"
            $justifyContent="space-between"
            $gap={20}
            className="mail-tag__header--status-n-sent-at"
          >
            <span className="mail-tag__header--status">
              <b>{mailStatus}</b>
            </span>
            <span className="mail-tag__header--sent-at">
              {new Date(mail.sentAt).toLocaleString("vi-VN", {
                dateStyle: "medium",
                timeStyle: "short",
              })}
            </span>
          </Box>
        </Box>
      </Box>
    </Box>
  );
};

export default MailTagContainer;
