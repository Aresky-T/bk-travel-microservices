import { LocalizationProvider } from "@mui/x-date-pickers";
import { MobileDateTimePicker } from "@mui/x-date-pickers/MobileDateTimePicker"
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import dayjs from "dayjs";
import utc from 'dayjs/plugin/utc';
import timezone from 'dayjs/plugin/timezone';

dayjs.extend(utc);
dayjs.extend(timezone);

export default function CustomDateTimePicker({ label, value, setValue }) {
    return (
        <LocalizationProvider dateAdapter={AdapterDayjs}>
            <MobileDateTimePicker
                timezone="Asia/Ho_Chi_Minh"
                value={dayjs.tz(new Date(value), "Asia/Ho_Chi_Minh")}
                label={label}
                onChange={(value) => {
                    setValue(new Date(value));
                }}
            />
        </LocalizationProvider>
    )
}