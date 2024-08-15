import striptags from 'striptags'
import { CUSTOM_REGEX } from '../constant/regex'

const yup = require('yup')

/**
 * The `validateString` function is a JavaScript function that uses the `yup` library to validate a
 * string property, ensuring that it is not empty and does not contain only whitespace.
 * @param property - The `property` parameter is a string that represents the name or description of
 * the property being validated. It is used in the error messages to provide more context about the
 * validation failure.
 * @returns a Yup string validation schema.
 */
export const validateString = (property) => {
    return yup.string().required(`Yêu cầu ${property}`)
        .test(`Check ${property}`, `${property} không hợp lệ`, function (value) {
            return value.trim().length > 0
        })
}

export const validateBLogItem = yup.object().shape({
    subTitle: validateString('subTitle'),
    content: validateString('content'),
})

export const validateTouristAttraction = yup.object().shape({
    name: validateString('name'),
    imageUrl: validateString('imageUrl'),
    introduction: validateString('intro'),
})

export const validateTour = yup.object().shape({
    title: validateString('Tiêu đề'),
    duration: validateString('Tổng thời gian'),
    departureLocation: validateString('Địa điểm khởi hành'),
    destinations: validateString('Danh sách điểm đến'),
    totalSeats: yup.number().min(1, 'Tổng số chỗ phải lớn hơn 0').required("Yêu cầu nhập tổng số chỗ"),
    vehicle: validateString('Danh sách điểm đến'),
    schedules: yup.string().required(`Required scheduleDescription`)
        .test('Check scheduleDescription', 'Mô tả lịch trình không đúng hợp lệ', function (value) {
            const trimValue = striptags(value).trim();
            return trimValue.length > 0
        }),
    adultPrice: yup.number().min(0, 'Giá cho người lớn phải lớn hơn hoặc bằng 0').required("Yêu cầu nhập giá cho người lớn"),
    childrenPrice: yup.number().min(0, 'Giá cho trẻ em phải lớn hơn hoặc bằng 0').required("Yêu cầu nhập giá cho trẻ em"),
    babyPrice: yup.number().min(0, 'Giá cho em bé phải lớn hơn hoặc bằng 0').required("Yêu cầu nhập giá cho em bé"),
})

export const validateTourGuide = yup.object().shape({
    fullName: validateString('fullName'),
    birthDate: yup.date().required('Required birthDate'),
    gender: validateString('gender'),
    phone: validateString('phone'),
    address: validateString('address'),
    avatarUrl: validateString('avatarUrl'),
    description: validateString('description'),
})

export const validateTourist = yup.object().shape({
    fullName: yup.string().required("Required fullName").matches(CUSTOM_REGEX.REGEX_STRING, "Invalid fullName"),
    birthDate: yup.date().required("Required birthDate"),
    gender: yup.string().required('Required gender').matches(CUSTOM_REGEX.REGEX_STRING, "Invalid gender")
})

export const validateBooking = yup.object().shape({
    fullName: yup.string().required("Required fullName").matches(CUSTOM_REGEX.REGEX_STRING, 'Invalid fullName!'),
    email: yup.string().required('Required email').matches(CUSTOM_REGEX.EMAIL, "Invalid email!"),
    phone: yup.string().required("Required phone").matches(CUSTOM_REGEX.PHONE, "Invalid phone!"),
    address: yup.string().required("Required address").matches(CUSTOM_REGEX.REGEX_STRING, "Invalid address!"),
    adultNumber: yup.number().required("Required adultNumber").min(1, "Adult number must be greater than 1!"),
    childrenNumber: yup.number().required("Required childrenNumber").min(0, "Children number must be greater than 0!"),
    babyNumber: yup.number().required("Required babyNumber").min(0, "Baby number must be greater than 0!"),
    note: yup.string(),
    tourId: yup.number(),
    touristList: yup.array().of(validateTourist)
        .test("Tourist-list-length", 'Tourist List length must be equal to the sum of adultNumber, childrenNumber and babyNumber',
            function (value) {
                const { adultNumber, childrenNumber, babyNumber } = this.parent;
                return value.length === adultNumber + childrenNumber + babyNumber
            }).required("Tourist List must be a array!"),
    adults: yup.array().of(yup.mixed())
        .test('adults-length', 'Adults length must be equal to adultNumber!', function (value) {
            const { adultNumber } = this.parent;
            return value.length === adultNumber;
        }).required("Adults must be a array!"),
    children: yup.array().of(yup.mixed())
        .test('children-length', 'Children length must be equal to childrenNumber!', function (value) {
            const { childrenNumber } = this.parent;
            return value.length === childrenNumber;
        }).required("Children must be a array!"),
    babies: yup.array().of(yup.mixed())
        .test('babies-length', 'Babies length must be equal to babyNumber!', function (value) {
            const { babyNumber } = this.parent;
            return value.length === babyNumber;
        }).required("Babies must be a array!"),
})

export const validateLoginForm = yup.object().shape({
    username: yup.string()
        .required('Tên tài khoản không được để trống!')
        .matches(CUSTOM_REGEX.USERNAME2, 'Tên tài khoản không được chứa dấu cách'),
    password: yup.string()
        .required('Mật khẩu không được để trống!')
})


export const validateRegisterForm = yup.object().shape({
    email: yup.string()
        .required('Email không được để trống'),
    username: yup.string()
        .required('Tên tài khoản không được để trống!')
        .matches(CUSTOM_REGEX.USERNAME2, 'Tên tài khoản không được chứa dấu cách'),
    password: yup.string()
        .required('Mật khẩu không được để trống!')
        .matches(CUSTOM_REGEX.PASSWORD, 'Mật khẩu từ 8 đến 20 ký tự, bao gồm các chữ in hoa, chữ thường, các số và ký tự đặc biệt!'),
    confirmPassword: yup.string()
        .required('Mật khẩu không được để trống!')
        .oneOf([yup.ref('password'), null], 'Mật khẩu xác nhận không khớp!')
})