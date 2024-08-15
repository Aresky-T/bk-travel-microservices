import React from 'react'
import styled from 'styled-components'

const PopupStyled = styled.div`
    min-width: 250px;
    min-height: 250px;
    box-shadow: 0 0 15px 5px rgba(47, 47, 47, 0.2);
    border-radius: 20px;
    overflow: hidden;
    display: flex;
    flex-direction: row;
    z-index: 900;
    width: ${(props) => props.$width || "auto"};
    height: ${(props) => props.$height || "auto"};
    background-color: ${(props) => props.$backgroundColor || "transparent"};
    font-size: ${(props) => props.$fontSize || "1rem"};
`

const CustomPopup = ({ children, width, height, className, backgroundColor, fontSize, style }) => {
    const props = { width, height, backgroundColor, fontSize };
    const newProps = {};

    for (const key in props) {
        if (Object.hasOwnProperty.call(props, key)) {
            newProps[`$${key}`] = props[key];
        }
    }

    return (
        <PopupStyled
            className={className || 'custom-popup'}
            {...newProps}
            style={style}
        >
            {children}
        </PopupStyled>
    )
}

export default CustomPopup