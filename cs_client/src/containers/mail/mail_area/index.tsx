import React, { useCallback, useEffect, useState } from "react";
import { RiMailForbidLine } from "react-icons/ri";
import Box from "../../../components/styled/box";
import { FiLogOut } from "react-icons/fi";
import { MdDeleteOutline } from "react-icons/md";
import Avatar from "../../../components/customer/Avatar";
import MailTagContainer from "../mail_tag";
import MailDetails from "../mail_details";
import { HiMiniArrowUturnLeft } from "react-icons/hi2";
import { TbReload } from "react-icons/tb";
import { useAppContext } from "../../../store/context";
import Button from "../../../components/styled/button";
import { deleteMailBoxByIdApi, getAllMailsApi } from "../../../api/mail.api";
import { toast } from "react-toastify";
import { questionAlert } from "../../../config/sweetAlertConfig";
import {
  FetchAllMailBoxesAction,
  RemoveCurrentMailAction,
  RemoveCurrentMailBoxAction,
  ResetMailFormAction,
} from "../../../store/actions/mail.action";
import { IMail } from "../../../types/mail";

const MailArea = () => {
  const [mailList, setMailList] = useState<IMail[]>([]);
  const [isReloadMailList, setIsReloadMailList] = useState(false);
  const { state, dispatch } = useAppContext();
  const { staff, currentMail, currentMailBox } = state.mail;

  function onLeaveBox() {
    if (dispatch) {
      currentMail && RemoveCurrentMailAction(dispatch);
      RemoveCurrentMailBoxAction(dispatch);
      ResetMailFormAction(dispatch);
    }
  }

  function deleteMailBox() {
    questionAlert(
      "Cảnh báo",
      "Tất cả thư và thông tin khách hàng sẽ bị xóa đi, bạn có chắn chắn muốn xóa hộp thư này không?",
      "Xóa",
      "Hủy"
    ).then((result) => {
      if (!result.isConfirmed) return;

      const token = state.auth.data.token;
      if (token && currentMailBox) {
        deleteMailBoxByIdApi(currentMailBox.id)
          .then((res) => {
            if (res.data) {
              toast.success("Đã xóa hộp thư thành công!");
              onLeaveBox();
              if (staff && dispatch)
                FetchAllMailBoxesAction(dispatch, staff.email);
            } else {
              toast.error("Xóa hộp thư thất bại!");
            }
          })
          .catch((err) => {});
      }
    });
  }

  function reloadMailBox() {
    setIsReloadMailList(true);
  }

  const getAllMails = useCallback(() => {
    if (!currentMailBox) return;

    getAllMailsApi(currentMailBox.id, 10, 0)
      .then((res) => {
        setMailList(res.data);
        setIsReloadMailList(false);
      })
      .catch((err) => {});
  }, [currentMailBox]);

  useEffect(() => {
    if (isReloadMailList) {
      getAllMails();
    }

    //eslint-disable-next-line
  }, [isReloadMailList]);

  useEffect(() => {
    getAllMails();
  }, [getAllMails]);

  return (
    <section className={currentMailBox ? "mail-area selected" : "mail-area"}>
      {currentMailBox ? (
        <Box
          className="mail-box"
          $width="100%"
          $height="100%"
          $boxShadow="0 0 10px rgba(0, 0, 0, .3)"
          $borderRadius={15}
          $overflow="hidden"
        >
          <Box className="mail-box__header">
            <div className="mail-box__header__customer">
              <Box className="customer__avatar">
                <Avatar
                  type="url"
                  url="https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png"
                />
              </Box>
              <div className="customer__name-n-email">
                <div>{currentMailBox.customer.fullName}</div>
                <div>{currentMailBox.customer.email}</div>
              </div>
            </div>
            <Box
              className="mail-box__header__options"
              $display="flex"
              $alignItems="center"
              $justifyContent="flex-end"
              $gap={5}
            >
              {currentMail && (
                <Button
                  icon={HiMiniArrowUturnLeft}
                  onClick={() => {
                    if (dispatch) {
                      dispatch({ type: "MAIL|REMOVE|CURRENT_MAIL" });
                      dispatch({ type: "MAIL|RESET|MAIL_FORM" });
                    }
                  }}
                />
              )}
              <Button icon={TbReload} onClick={reloadMailBox} />
              <Button icon={MdDeleteOutline} onClick={deleteMailBox} />
              <Button icon={FiLogOut} onClick={onLeaveBox} />
            </Box>
          </Box>
          <Box className="mail-box__body">
            <Box className="mail-box__body__mails cs-scroll">
              {currentMail ? (
                <MailDetails
                  mail={currentMail}
                  staff={staff}
                  onReloadMailBox={reloadMailBox}
                />
              ) : (
                <>
                  {mailList?.map((mail) => (
                    <MailTagContainer mail={mail} key={mail.id} />
                  ))}
                </>
              )}
            </Box>
          </Box>
        </Box>
      ) : (
        <Box
          $height="100%"
          $display="flex"
          $flex-direction="column"
          $alignItems="center"
          $justifyContent="center"
          $color="var(--primary-color)"
          $fontWeight={600}
          $fontSize="1.5rem"
        >
          <div>
            <RiMailForbidLine size={100} />
          </div>
          <Box $fontFamily="inherit" $marginTop={16} $lineHeight="2rem">
            Không có hộp thư nào được chọn
          </Box>
        </Box>
      )}
    </section>
  );
};

export default React.memo(MailArea);
