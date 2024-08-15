import React from 'react'
import TouristAttractionContainer from '../../containers/global/TouristAttraction/TouristAttractionContainer'
import HelmetTitle from "../../components/helmet/HelmetTitle";

const TouristAttractionPage = () => {
    return (
        <>
            <HelmetTitle
                title={"BK Travel - Điểm tham quan"}
                metaName={"meta-tourist-attraction"}
            />
            <TouristAttractionContainer />
        </>
    )
}

export default TouristAttractionPage