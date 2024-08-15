import React from "react";
import Box from "../styled/box";

interface ListTagProps {
  renderListTags: () => JSX.Element | JSX.Element[];
}

const ListTag: React.FC<ListTagProps> = (props) => {
  return <Box className="list-tags cs-scroll">{props.renderListTags()}</Box>;
};

export default ListTag;
