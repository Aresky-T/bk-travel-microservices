import { useState } from "react";

export default function TextArea({ name, value, label, onChange, required, type }) {
    const [isFocus, setIsFocus] = useState(false);

    const handleFocus = () => {
        setIsFocus(true);
    }

    const handleBlur = () => {
        setIsFocus(false);
    }

    const handleChange = (event) => {
        if (onChange) {
            onChange(name, event.target.value)
        }
    }

    return (
        <div className={`custom-text-area ${isFocus || value ? 'focused' : ''}`}>
            <label className='custom-label'>{label}</label>
            <textarea
                type={type || "text"}
                name={name}
                className='custom-input'
                value={value || ""}
                onChange={handleChange}
                onFocus={handleFocus}
                onBlur={handleBlur}
                required={required || false}
            />
        </div>
    )
}