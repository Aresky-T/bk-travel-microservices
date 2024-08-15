import React, { useCallback, useEffect, useState } from "react";
import Box from "../../../components/styled/box";
import { IMail, IMailDetails, IMailForm } from "../../../types/mail";
import { BiSolidRightArrow, BiSolidDownArrow } from "react-icons/bi";
import { HiMiniArrowUturnLeft } from "react-icons/hi2";
// import { AiOutlineDelete } from "react-icons/ai";
import MailReplyForm from "../../../components/chat/MailReplyForm";
import Button from "../../../components/styled/button";
import {
  getDetailsMailApi,
  readMailApi,
  replyMailApi,
} from "../../../api/mail.api";
import IStaff from "../../../types/staff";
import MailReply from "../../../components/mail/MailReply";
import ValidateUtils from "../../../utils/validate";
import { questionAlert, successAlert } from "../../../config/sweetAlertConfig";
import { toast } from "react-toastify";
import { useAppContext } from "../../../store/context";
import { FetchAllMailBoxesAction } from "../../../store/actions/mail.action";
import { EMailStatus } from "../../../types/enum";

interface PropsType {
  mail: IMail;
  staff: IStaff | null;
  onReloadMailBox: () => any;
}

type FormErrorsType = Map<string, { message: string }>;

const MailDetails: React.FC<PropsType> = ({ mail, staff, onReloadMailBox }) => {
  const [mailDetails, setMailDetails] = useState<IMailDetails>();
  const [replyForm, setReplyForm] = useState<IMailForm>({
    mailId: 0,
    subject: "",
    body: "",
  });
  const [formErrors, setFormErrors] = useState<FormErrorsType>(new Map());
  const [isReply, setIsReply] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

  const { dispatch } = useAppContext();

  const handleChangeForm = (name: keyof IMailForm, value: any) => {
    setReplyForm((prevForm) => ({
      ...prevForm,
      [name]: value,
    }));
  };

  const handleResetForm = () => {
    setReplyForm((prevForm) => ({
      ...prevForm,
      subject: "",
      body: "",
    }));
  };

  const validateReplyForm = (formData: IMailForm) => {
    const { isValid, errors } = ValidateUtils({
      form: formData,
      rules: {
        subject: {
          required: true,
        },
        body: {
          required: true,
        },
      },
      messages: {
        subject_required: "(*) Tiêu đề không được để trống!",
        body_required: "(*) Nội dung không được để trống!",
      },
    });
    setFormErrors(errors);
    return isValid;
  };

  const handleCloseForm = () => {
    handleResetForm();
    setFormErrors(new Map());
    setIsReply(false);
  };

  const handleSubmitReplyMail = () => {
    const isValid = validateReplyForm(replyForm);
    if (!isValid) {
      toast.error("Thông tin không hợp lệ!", {
        position: "bottom-right",
        autoClose: 1000,
        theme: "colored",
      });
      return;
    }

    questionAlert(
      "Cảnh báo",
      "Vui lòng bấm xác nhận để gửi",
      "Xác nhận",
      "Quay lại"
    ).then((button) => {
      if (!button.isConfirmed) return;
      setIsLoading(true);
      replyMailApi(replyForm)
        .then((res) => {
          setIsLoading(false);
          handleCloseForm();
          successAlert("Thành công", "Đã trả lời mail thành công!", "OK");
          getMailDetails();
          if (staff && dispatch) FetchAllMailBoxesAction(dispatch, staff.email);
        })
        .catch((err) => {
          toast.error("Gửi mail thất bại", { position: "bottom-right" });
          setIsLoading(false);
        });
    });
  };

  const getMailDetails = useCallback(() => {
    if (!staff) return;

    getDetailsMailApi(staff.email, mail.id)
      .then((res) => {
        setMailDetails(res.data);
      })
      .catch((err) => {});
  }, [mail, staff]);

  const readMail = useCallback(() => {
    if (!staff || mail.status !== EMailStatus.NEW) return;

    readMailApi(staff.email, mail.id)
      .then((res) => {
        onReloadMailBox();
      })
      .catch((err) => {});

    // eslint-disable-next-line
  }, [mail, staff]);

  useEffect(() => {
    readMail();
  }, [readMail]);

  useEffect(() => {
    getMailDetails();
  }, [getMailDetails]);

  useEffect(() => {
    handleChangeForm("mailId", mail.id);
  }, [mail]);

  return (
    <Box
      $transition="all 50ms linear"
      $backgroundColor="#fff"
      $height={"100%"}
      className="mail-detail read"
      $display="flex"
      $flexDirection="column"
      $justifyContent="space-between"
    >
      <Box
        $padding={"10px 15px"}
        $height={"100%"}
        $fontSize={".8rem"}
        $display="flex"
        $flexDirection="column"
        $alignItems="flex-start"
        $gap={10}
        $transition="all 100ms linear"
        $overflowY="scroll"
        className="cs-scroll"
      >
        <Box $display="flex" $alignItems="center" $gap={10}>
          <Box $minWidth={60}>Tiêu đề</Box>
          <BiSolidRightArrow color="var(--gray-color)" />
          <b>{mail.subject}</b>
        </Box>
        <Box $display="flex" $alignItems="center" $gap={10}>
          <Box $minWidth={60}>Ngày gửi</Box>
          <BiSolidRightArrow color="var(--gray-color)" />
          <span
            className="mail-detail__header__sent-at"
            style={{ fontSize: ".6rem" }}
          >
            {new Date(mail.sentAt).toLocaleString("vi-VN", {
              dateStyle: "medium",
              timeStyle: "short",
            })}
          </span>
        </Box>
        <Box
          $display="flex"
          $flexDirection="column"
          $alignItems="flex-start"
          $gap={20}
        >
          <Box $display="flex" $alignItems="center" $gap={10}>
            <Box $minWidth={60}>Nội dung</Box>
            <BiSolidDownArrow color="var(--gray-color)" />
          </Box>
          <Box>{mail.body}</Box>
        </Box>
      </Box>
      <Box $height="fit-content" $fontSize={".8rem"} $backgroundColor="#f2f2f2">
        {mailDetails?.reply && <MailReply mailReply={mailDetails.reply} />}
        {isReply ? (
          <MailReplyForm
            formData={replyForm}
            formErrors={formErrors}
            isLoading={isLoading}
            handleChangeForm={handleChangeForm}
            handleSubmitForm={handleSubmitReplyMail}
            handleCloseForm={handleCloseForm}
          />
        ) : (
          <Box
            $display="flex"
            $gap={15}
            $padding={15}
            $background="#fff"
            className="mail-details__action_btn"
          >
            {!mailDetails?.reply && (
              <Button
                text="Trả lời"
                icon={HiMiniArrowUturnLeft}
                onClick={() => {
                  setIsReply(true);
                }}
              />
            )}
            {/* <Button text="Xóa thư này" icon={AiOutlineDelete} /> */}
          </Box>
        )}
      </Box>
    </Box>
  );
};

export default MailDetails;
