import React, { useState } from 'react'
import styled from 'styled-components'
import Title from '../Title';

const CircleBtnStyled = styled.button`
    background-color: ${props => props.$backgroundColor || "#f3f3f3"};
    border-radius: 50%;
    border: ${props => props.$border || "1px solid #ccc"};
    outline: ${props => props.$outline || "1px solid #ccc"};
    width: ${props => props.$width || "28px"};
    height: ${props => props.$width || "28px"};
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: ${props => props.$fontSize || "1rem"};
    cursor: pointer;
    padding: 3px;
    transition: all 50ms linear;
    font-weight: ${props => props.$fontWeight || "normal"};
    position: relative;
    &:hover {
        background-color: #ebe8e8;
    }
`;

const CircleButton = ({ children, width, backgroundColor, border, outline, fontSize, fontWeight, className, style, onClick, title }) => {
    const props = { width, backgroundColor, border, outline, fontSize, fontWeight };
    const [isHovered, setIsHovered] = useState(false);

    const newProps = {};

    for (const key in props) {
        if (Object.hasOwnProperty.call(props, key)) {
            newProps[`$${key}`] = props[key];
        }
    }

    return (
        <CircleBtnStyled
            className={className || "custom-circle-button"}
            style={{ ...style }}
            {...newProps}
            onClick={onClick}
            onMouseEnter={() => setIsHovered(true)}
            onMouseLeave={() => setIsHovered(false)}
        >
            {children}
            {(isHovered && title) && <Title text={title} />}
        </CircleBtnStyled>
    )
}



export default CircleButton