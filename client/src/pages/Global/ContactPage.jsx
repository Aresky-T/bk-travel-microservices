import React from 'react'
import HelmetTitle from '../../components/helmet/HelmetTitle'
import Contact from '../../components/global/Contact'

const ContactPage = () => {
    return (
        <>
            <HelmetTitle
                title="BK Travel - Liên hệ"
                metaName="meta-contact"
            />
            <Contact/>
        </>
    )
}

export default ContactPage