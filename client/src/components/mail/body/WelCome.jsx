import React from 'react'
import { BiSolidBadgeCheck } from 'react-icons/bi'
import Box from '../../styled/popup/Box'
import Button from '../../styled/button/Button'
import { useMail } from '../../../redux/selector'
import { useDispatch } from 'react-redux'
import { updateMailField } from '../../../redux/slices/mail.slice'

const WelCome = () => {
    const mail = useMail();
    const dispatch = useDispatch();
    const status = mail.boxStatus;

    return (
        <Box className="mail-box_body--welcome" padding={"0 25px"}>
            <Box className='mail--welcome_item'
                style={{ display: 'flex', alignItems: 'center', fontSize: 20, marginTop: 50 }}
            >
                <strong style={{ fontWeight: 600 }}>Gửi mail tới BK TRAVEL</strong>
                <div style={{ display: 'flex', alignItems: 'center', color: 'var(--primary-color)' }}>
                    <BiSolidBadgeCheck size={23} />
                </div>
            </Box>
            <Box className='chat--welcome_item'
                style={{ fontSize: "15px", marginTop: 20, lineHeight: "1.25" }}
            >
                Xin chào! Chúng tôi có thể giúp gì cho bạn?<br />
                Hãy bấm tiếp tục nếu bạn có điều gì đó thắc mắc nhé!
            </Box>
            <Box className='chat--welcome_item'
                style={{ marginTop: 60 }}
                width={"100%"}
            >
                <Button
                    width={"100%"}
                    onClick={() => {
                        dispatch(updateMailField({
                            key: "boxStatus",
                            value: { ...status, isStarted: true }
                        }))
                    }}>
                    Tiếp tục
                </Button>
            </Box>
        </Box>
    )
}

export default WelCome