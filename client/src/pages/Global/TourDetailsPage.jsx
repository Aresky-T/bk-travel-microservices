import React from 'react'
import TourDetailsContainer from "../../containers/global/Tour/TourDetailsContainer";
import HelmetTitle from "../../components/helmet/HelmetTitle";

const TourDetailsPage = () => {
    return (
        <>
            <HelmetTitle
                title={"BK Travel - Chi tiết Tour"}
                metaName={"meta-details-tour"}
            />
            <TourDetailsContainer />
        </>
    )
}

export default TourDetailsPage