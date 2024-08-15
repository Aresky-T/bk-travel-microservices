import React from 'react'
import ToursContainer from '../../containers/global/Tour/ToursContainer'
import HelmetTitle from "../../components/helmet/HelmetTitle";

const TourPage = () => {
    return (
        <>
            <HelmetTitle
                title={"BK Travel - Tour"}
                metaName={"meta-tours"}
            />
            <ToursContainer />
        </>
    )
}

export default TourPage