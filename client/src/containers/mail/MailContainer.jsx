import React from 'react'
import MailIcon from '../../components/mail/MailIcon';
import MailBoxContainer from './maibox/MailBoxContainer';
import { useMail } from '../../redux/selector';
import { useDispatch } from 'react-redux';
import { updateMailField } from '../../redux/slices/mail.slice'

const MailContainer = () => {
    const { isShowMailBox } = useMail();
    const dispatch = useDispatch();

    const handleShowMail = () => {
        !isShowMailBox && dispatch(updateMailField({
            key: "isShowMailBox",
            value: true
        }));
    }

    const handleHiddenMail = () => {
        isShowMailBox && dispatch(updateMailField({
            key: "isShowMailBox",
            value: false
        }))
    };

    return (
        <div className='customer-support-container mail-container'>
            <MailIcon handleShowMail={handleShowMail} />
            <MailBoxContainer isShowMail={isShowMailBox} handleHiddenMail={handleHiddenMail} />
        </div>
    )
}

export default MailContainer