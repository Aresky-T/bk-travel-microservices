import React, { PropsWithChildren } from 'react'
import styled, { Attrs, CSSProperties } from 'styled-components'

type ObjectType = { [key: string]: any }

type PropsType = {
    style?: CSSProperties & { [key: string]: string | number | {} },
    className?: string,
    onClick?: () => void,
    onMouseEnter?: (event?: React.MouseEvent<HTMLDivElement>) => void,
    onMouseLeave?: (event?: React.MouseEvent<HTMLDivElement>) => void,
    onMouseDown?: (event?: React.MouseEvent<HTMLDivElement>) => void,
    onMouseUp?: (event?: React.MouseEvent<HTMLDivElement>) => void,
    onMouseOver?: (event?: React.MouseEvent<HTMLDivElement>) => void,
    onMouseMove?: (event?: React.MouseEvent<HTMLDivElement>) => void,
} & Attrs & CSSProperties & PropsWithChildren & ObjectType;

const BoxStyled = styled.div((props: any) => {
    const myStyle: any = {};
    for (const key in props) {
        if (key.includes("$")) {
            myStyle[`${key.replace("$", "")}`] = props[key];
        }
    }
    return { ...myStyle };
});

const Box: React.FC<PropsType> = (props) => {
    return (
        <BoxStyled
            {...props}
        >
            {props.children}
        </BoxStyled>
    )
}

export default Box
