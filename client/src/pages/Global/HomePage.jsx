import React from 'react'
import HomeContainer from '../../containers/global/Home/HomeContainer'
import HelmetTitle from "../../components/helmet/HelmetTitle";

const HomePage = () => {
    return (
        <>
            <HelmetTitle
                title={"BK Travel - Trang chủ"}
                metaName={"meta-home"}
            />
            <HomeContainer />
        </>
    )
}

export default HomePage