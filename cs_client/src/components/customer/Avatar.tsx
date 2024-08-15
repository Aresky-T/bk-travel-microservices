import React from "react";
import { IconType } from "react-icons";
import styled, { CSSProperties } from "styled-components";

type AvatarType =
  | { type: "text"; text: string }
  | { type: "url"; url: string }
  | { type: "icon"; icon: IconType };

type PropsType = {
  style?: CSSProperties;
} & AvatarType;

const AvatarStyled = styled.div`
  width: 100%;
  height: 100%;
  background-color: #fff;
  color: #fff;
  border: 1px solid #ccc;
  border-radius: 50%;
  overflow: hidden;
  font-size: 1.5rem;
  font-weight: 600;
  line-height: 1.5rem;
  display: flex;
  align-items: center;
  justify-content: center;

  & > img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
`;

const Avatar: React.FC<PropsType> = (props) => {
  function renderAvatar() {
    const type = props.type;
    if (type === "text") {
      return <>{String(props.text).trim().charAt(0).toUpperCase()}</>;
    } else if (type === "icon") {
      return (
        <>
          <props.icon />
        </>
      );
    } else {
      return <img src={props.url} alt="avatar" />;
    }
  }

  return (
    <AvatarStyled style={props.style || {}}>{renderAvatar()}</AvatarStyled>
  );
};

export default Avatar;
