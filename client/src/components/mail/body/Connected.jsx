import React from 'react'
import Box from '../../styled/popup/Box'
import TextField from '../../styled/TextField'
import Button from '../../styled/button/Button'
import TextArea from '../../styled/TextArea'
import { useMail } from '../../../redux/selector'
import { useDispatch } from 'react-redux'
import { updateMailField } from '../../../redux/slices/mail.slice'

const Connected = ({ handleSubmitSendMail }) => {
    const mail = useMail();
    const dispatch = useDispatch();

    const handleChange = (name, value) => {
        dispatch(updateMailField({
            key: name,
            value
        }))
    }

    return (
        <Box
            className="mail-box_body--connected"
            padding={"0 25px"}
            display={"flex"}
            flexDirection={"column"}
            style={{
                rowGap: 20,
            }}
        >
            <Box
                padding={"10px 0"}
                style={{
                    textAlign: "center",
                    fontSize: 17,
                }}
            >
                <strong>Điền thông tin mail theo mẫu dưới đây</strong>
            </Box>
            <Box display={"flex"} justifyContent={"space-between"}>
                <TextField
                    label={"Email của bạn"}
                    name={"email"}
                    value={mail.email || ""}
                    onChange={handleChange}
                />
                <TextField
                    label={"Tên của bạn"}
                    name={"fullName"}
                    value={mail.fullName || ""}
                    onChange={handleChange}
                />
            </Box>
            <Box>
                <TextField
                    label={"Tiêu đề thư"}
                    name={"title"}
                    value={mail.title || ""}
                    onChange={handleChange}
                />
            </Box>
            <Box>
                <TextArea
                    name={"content"}
                    label={"Nội dung thư"}
                    value={mail.content || ""}
                    onChange={handleChange}
                />
            </Box>
            <Box>
                <p style={{ fontSize: 12, lineHeight: "1.3", color: "var(--font-color)" }}>
                    <i>
                        (*) Lưu ý: Nếu quý khách sử dụng tài khoản đã đăng nhập,
                        hệ thống sẽ sử dụng <b>họ tên</b> và <b>địa chỉ email</b> của quý khách để điền thông tin cho thư!
                    </i>
                </p>
            </Box>
            <Box>
                <Button height={"40px"} width={"70px"} fontSize={"1rem"}
                    style={{
                        minWidth: "unset",
                        minHeight: "unset",
                    }}
                    onClick={() => {
                        handleSubmitSendMail()
                    }}
                >
                    Gửi
                </Button>
            </Box>
        </Box>
    )
}

export default Connected