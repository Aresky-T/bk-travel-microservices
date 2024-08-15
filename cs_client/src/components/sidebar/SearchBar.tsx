import React from 'react'
import { LuSearch } from 'react-icons/lu'
import Box from '../styled/box'

interface SearchBarProps {
    placeholder: string,
    inputValue: string,
    handleChange: (event: React.ChangeEvent<HTMLInputElement>) => void
}

const SearchBar: React.FC<SearchBarProps> = (props) => {
    return (
        <Box className="search">
            <Box>
                <LuSearch />
            </Box>
            <Box>
                <input type="text"
                    placeholder={props.placeholder}
                    value={props.inputValue}
                    onChange={props.handleChange}
                />
            </Box>
        </Box>
    )
}

export default SearchBar