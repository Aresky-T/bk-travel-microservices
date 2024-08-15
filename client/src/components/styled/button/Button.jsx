import React from 'react'
import styled from 'styled-components'

const ButtonStyled = styled.button`
    margin: ${props => props.$margin || "0"};
    padding: ${props => props.$padding || "0"};
    width: ${props => props.$width || "auto"};
    height: ${props => props.$height || "auto"};
    min-width: ${props => props.$minWidth || "160px"};
    min-height: ${props => props.$minHeight || "54px"};
    outline: ${props => props.$outline || "none"};
    border-radius: ${props => props.$borderRadius || "15px"};
    border: ${props => props.$border || "none"};
    font-weight: ${props => props.$fontWeight || "normal"};
    font-size: ${props => props.$fontSize || "1rem"};
    font-family: inherit;
    line-height: ${props => props.$fontSize || "1rem"};
    background-color: ${props => props.$backgroundColor || "var(--primary-color)"};
    color: ${props => props.$color || "#fff"};
    transition: all 100ms linear;
    cursor: pointer;
    user-select: none;

    &:hover {
        background-color: var(--secondary-color);
    }
`;

const Button = (
    { children, type, width, height, minWidth,
        minHeight, backgroundColor, color, border,
        borderRadius, outline, fontSize, fontWeight,
        margin, padding, style, className, onClick
    }) => {
    const props = {
        width, height, minWidth, minHeight,
        backgroundColor, color, border,
        borderRadius, outline, fontSize, fontWeight,
        margin, padding
    }
    const newProps = {};

    for (const key in props) {
        if (Object.hasOwnProperty.call(props, key)) {
            newProps[`$${key}`] = props[key];
        }
    }

    return (
        <ButtonStyled
            type={type || "button"}
            className={className || "custom-button"}
            style={{ ...style }}
            {...newProps}
            onClick={onClick}
        >{children}
        </ButtonStyled>
    )
}

export default Button