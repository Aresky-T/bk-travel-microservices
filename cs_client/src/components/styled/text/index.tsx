import React, { PropsWithChildren } from "react";
import styled, { Attrs, CSSProperties } from "styled-components"

type ObjectType = { [key: string]: any }

const TextStyled = styled.p((props: any) => {
    const myStyle: any = {};
    for (const key in props) {
        if (key.includes("$")) {
            myStyle[`${key.replace("$", "")}`] = props[key];
        }
    }
    return { ...myStyle };
})

type PropsType = {
    style?: CSSProperties & { [key: string]: string | number | {} },
    className?: string,
} & CSSProperties & PropsWithChildren & ObjectType & Attrs;

const Text: React.FC<PropsType> = (props) => {
    return (
        <TextStyled
            {...props}
        >
            {props.children}
        </TextStyled>
    )
}

export default Text