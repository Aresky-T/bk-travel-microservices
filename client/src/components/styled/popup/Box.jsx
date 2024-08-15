import React from 'react'
import styled from 'styled-components'

const BoxStyled = styled.div`
    width: ${props => props.$width || "100%"};
    height: ${props => props.$height || "auto"};
    font-family: inherit;
    display: ${props => props.$display || "block"};
    flex-direction: ${props => props.$flexDirection || "row"};
    justify-content:${props => props.$justifyContent || "normal"};
    align-items: ${props => props.$alignItems || "normal"};
    padding: ${props => props.$padding || 0};
    margin:${props => props.$margin || 0};
`;

const Box = ({ children, className, style, width, height, display, flexDirection, justifyContent, alignItems, padding, margin }) => {
    const props = { width, height, display, flexDirection, justifyContent, alignItems, padding, margin };

    const newProps = {};

    for (const key in props) {
        if (Object.hasOwnProperty.call(props, key)) {
            newProps[`$${key}`] = props[key];
        }
    }

    return (
        <BoxStyled
            className={className || "custom-box"}
            style={{ ...style }}
            {...newProps}
        >
            {children}
        </BoxStyled>
    )
}

export default Box