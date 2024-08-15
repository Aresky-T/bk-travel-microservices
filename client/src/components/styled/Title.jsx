import React from 'react'
import styled from 'styled-components'

const TitleStyled = styled.div`
    font-size: 12px;
    color: #fff;
    background-color: rgba(0, 0, 0, .7);
    border-radius: 10px;
    height: 30px;
    line-height: 30px;
    width: fit-content;
    padding: 0 10px;
    text-align: center;
    position: absolute;
    top: calc(100% + 8px);
    right: 50%;
    transition: all .1s learner;
    transform: translateX(50%);
    white-space: nowrap;
`;

const Title = ({ text }) => {
    return (
        <TitleStyled>{text}</TitleStyled>
    )
}

export default Title