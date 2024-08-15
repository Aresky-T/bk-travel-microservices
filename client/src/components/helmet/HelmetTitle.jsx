import {Helmet} from "react-helmet-async";
import React from "react";

const HelmetTitle = ({title, metaName, metaContent}) => {
    return (
        <Helmet>
            <meta charSet='utf-8' />
            <title>{title}</title>
            <meta name={metaName || "meta-app"} content={metaContent || "BK travel application"} />
        </Helmet>
    )
}

export default HelmetTitle