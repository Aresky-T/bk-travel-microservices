import { IconType } from "react-icons";
import styled, { CSSProperties } from "styled-components"

const ButtonStyled = styled.button`
    padding: 10px 15px;
    border: 1px solid #777777;
    background-color: #fff;
    border-radius: 20px;
    width: fit-content;
    display: flex;
    align-items: center;
    gap: 10px;
    cursor: pointer;
    transition: all 100ms easy-in-out;
    &:hover {
        background-color: #f2f2f2;
    }
`
type PropsType = {
    style?: CSSProperties & { [key: string]: string | number | {} },
    className?: string,
    icon?: IconType,
    text?: string,
    onClick?: () => void,
    revert?: boolean,
}

const Button: React.FC<PropsType> = (props) => {
    return (
        <ButtonStyled
            style={props.style || {}}
            onClick={props.onClick}
        >
            {props.revert ?
                <>
                    {props.text && <>{props.text}</>}
                    {props.icon && <props.icon />}
                </> :
                <>
                    {props.icon && <props.icon />}
                    {props.text && <>{props.text}</>}
                </>}
        </ButtonStyled>
    )
}

export default Button