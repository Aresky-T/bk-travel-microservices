import React from 'react'
import { AiOutlineClose } from 'react-icons/ai';
import Button from '../../styled/button/Button';
import Box from '../../styled/popup/Box';
import CircleButton from '../../styled/button/CircleButton';
import { useAuth, useMail } from '../../../redux/selector';
import toast from 'react-hot-toast';
import { useDispatch } from 'react-redux';
import { updateMailFields } from '../../../redux/slices/mail.slice';

const SelectType = () => {
    const auth = useAuth();
    const mail = useMail();
    const dispatch = useDispatch();
    const status = mail.boxStatus;

    const handleConnect = (type) => {
        dispatch(updateMailFields({
            type,
            boxStatus: { ...status, isConnected: true }
        }))
    }

    return (
        <Box className="mail-box_body--select-type"
            width={"100%"}
            height={"100%"}
            padding={"80px 0 0 0"}
            style={{
                position: "absolute",
                top: 0,
                left: 0,
                backgroundColor: " #80808078",
                fontSize: "20px"
            }}
        >
            <Box
                className="mail-box_body--select-type_main"
                width={"100%"}
                height={"100%"}
                padding={"0 25px"}
                display={"flex"}
                flexDirection={"column"}
                justifyContent={"center"}
                alignItems={"center"}
                style={{
                    backgroundColor: "#fff",
                    borderRadius: "15px 15px 0 0",
                    rowGap: 15,
                }}
            >
                <Box
                    display={"flex"}
                    alignItems={"center"}
                    justifyContent={"center"}
                    margin={" 0 0 35px 0"} style={{
                        position: "relative",
                    }}
                >
                    <strong>Đăng nhập</strong>
                    <CircleButton
                        border={"none"}
                        outline={"none"}
                        style={{ position: "absolute", right: 0 }}
                        onClick={() => {
                            dispatch(updateMailFields({ boxStatus: { ...status, isStarted: false } }));
                        }}>
                        <AiOutlineClose />
                    </CircleButton>
                </Box>
                <Box>
                    <Button
                        width={"100%"}
                        type="button"
                        onClick={() => {
                            if (!auth.accessToken || auth.role !== "USER") {
                                toast.error("Vui lòng đăng nhập trước khi tiếp tục!")
                            } else {
                                handleConnect("REGISTERED");
                            }
                        }}>
                        Bạn đã đăng nhập?
                    </Button>
                </Box>
                <Box>
                    <Button
                        className={"guest-customer"}
                        backgroundColor={"#f2f2f2"}
                        color={"#000"}
                        width={"100%"}
                        type="button"
                        onClick={() => handleConnect("GUEST")}>
                        Tiếp tục với vai trò khách
                    </Button>
                </Box>
            </Box>
        </Box >
    )
}

export default SelectType