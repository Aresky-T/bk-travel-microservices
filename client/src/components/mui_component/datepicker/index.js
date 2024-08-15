import { DatePicker, LocalizationProvider } from "@mui/x-date-pickers";
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import dayjs from "dayjs";

const CustomDatePicker = ({label, value, setValue}) => {
    return (
        <LocalizationProvider dateAdapter={AdapterDayjs}>
            <DatePicker
                label={label}
                value={dayjs(value)}
                onChange={setValue}
            />
        </LocalizationProvider>
    )
}

export default CustomDatePicker;