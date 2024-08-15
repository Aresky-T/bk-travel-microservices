import React, { useEffect } from 'react'
import CustomPopup from '../../../components/styled/popup/CustomPopup'
import Box from '../../../components/styled/popup/Box'
import { MdOutlineHorizontalRule } from 'react-icons/md'
import CircleButton from '../../../components/styled/button/CircleButton'
import WelCome from '../../../components/mail/body/WelCome'
import { IoMdClose } from 'react-icons/io'
import SelectType from '../../../components/mail/body/SelectType'
import Connected from '../../../components/mail/body/Connected'
import { useMail, useProfile } from '../../../redux/selector'
import { sendMailToEmployeeApi } from '../../../api/mail/mail.api'
import { errorAlert, successAlert } from '../../../config/sweetAlertConfig'
import { useDispatch } from 'react-redux'
import { offLoading, onLoading } from '../../../redux/slices/loading.slice'
import { getStompClient2 } from '../../../config/webSocketConfig'
import { resetMail, updateMailFields } from '../../../redux/slices/mail.slice'

const MailBoxContainer = ({ isShowMail, handleHiddenMail }) => {
    const mail = useMail();
    const status = mail.boxStatus;
    const profile = useProfile();
    const dispatch = useDispatch();

    const handleCancelMail = () => {
        dispatch(resetMail());
    }

    const handleSubmitSendMail = async () => {
        dispatch(onLoading());
        try {
            const { email, fullName, title, content, sender } = mail;
            const form = {
                email: email.trim(),
                fullName: fullName.trim(),
                title,
                content,
                sender
            };
            const res = await sendMailToEmployeeApi(form);
            const data = res.data;
            if (data) {
                const employeeId = data.employeeId;
                // const mailBoxId = data.id;
                getStompClient2()
                    .then((client) => {
                        client.publish({
                            destination: `/app/mail/load.mailboxes.of.employee/${employeeId}`
                        });
                        // client.publish({
                        //     destination: `/app/mail/load.mailbox.of.employee/${mailBoxId}`
                        // });
                    })
                    .catch(err => {
                    })
                dispatch(offLoading());
                successAlert("Thành công",
                    "Cảm ơn quý khách đã gửi thư tới BK Travel. Chúng tôi sẽ phản hồi tới email của quý khách trong thời gian sớm nhất!", "OK"
                ).then(() => {
                    dispatch(resetMail());
                })
            }

        } catch (error) {
            dispatch(offLoading());
            errorAlert("Thất bại", "Gửi thư thất bại, quý khách vui lòng thử lại!")
        }
    }

    useEffect(() => {
        if (profile && mail.type === "REGISTERED") {
            dispatch(updateMailFields({
                fullName: profile.fullName,
                email: profile.account.email,
            }))
        }
        //eslint-disable-next-line
    }, [profile, mail.type])

    return (
        <CustomPopup
            className={isShowMail ? "mail-box active" : "mail-box"}
        >
            <Box className="mail-box_header"
                display="flex"
                height="70px"
                justifyContent="space-between"
                alignItems="center"
                padding="0 25px"
            >
                <div className="mail-box_header__logo">
                    <b>BK</b>
                </div>
                <Box className="mail-box_header__icons" display={"flex"} width={"fit-content"}>
                    <CircleButton
                        onClick={handleHiddenMail}
                        border={"none"} outline={"none"} title={"Ẩn hộp thư"}>
                        <MdOutlineHorizontalRule size={21} />
                    </CircleButton>
                    {status.isConnected &&
                        <CircleButton
                            onClick={handleCancelMail}
                            border={"none"} outline={"none"} title={"Thoát"}>
                            <IoMdClose size={21} />
                        </CircleButton>
                    }
                </Box>
            </Box>
            <Box className="mail-box_body"
                height={status.isConnected ? "450px" : "300px"}
                width={status.isConnected ? "500px" : "370px"}
            >
                {!status.isStarted && <WelCome />}
                {(status.isStarted && !status.isConnected) && <SelectType />}
                {(status.isStarted && status.isConnected) && <Connected handleSubmitSendMail={handleSubmitSendMail} />}
            </Box>
        </CustomPopup>
    )
}

export default MailBoxContainer