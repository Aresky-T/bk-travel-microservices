import React from 'react'

interface TagProps {
    render: () => JSX.Element
}

const Tag: React.FC<TagProps> = (props) => {
    return (
        <>{props.render()}</>
    )
}

export default Tag