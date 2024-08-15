import React from 'react'
import { HiMail } from 'react-icons/hi'

const MailIcon = ({ handleShowMail }) => {
    return (
        <div className="mail-icon" onClick={handleShowMail}>
            <div style={{ marginRight: '6px', marginLeft: '-3px' }}>
                <div style={{ display: "flex", alignItems: "center" }}>
                    <HiMail size={28} />
                </div>
            </div>
            <div className='mail-icon__text'>Mail</div>
        </div>
    )
}

export default MailIcon