import React, { useEffect } from 'react'

const PublicRoute = (props: any) => {
    useEffect(() => {
        document.title = props.title;
    })

    return (
        <div>{props.children}</div>
    )
}

export default PublicRoute