export const configAPI = (token) => {
    return {
        headers: {
            Authorization: `Bearer ${token}`
        }
    }
}

export const API_GATEWAY_URL = "http://localhost:8080";
export const AUTH_SERVICE_URL = "http://localhost:8081";
export const ACCOUNT_SERVICE_URL = "http://localhost:8082";
export const TOUR_SERVICE_URL = "http://localhost:8083";
export const BOOKING_SERVICE_URL = "http://localhost:8084";
export const STAFF_SERVICE_URL = "http://localhost:8085";

export const BASE_ENDPOINTS = {
    AUTH: "http://localhost:8080/api/v1/auth",
    ACCOUNT: "http://localhost:8080/api/v1/accounts",
    TOUR: "http://localhost:8080/api/v1/tours",
    TOURIST_ATTRACTION: "http://localhost:8080/api/v1/tourist-attraction",
    BOOKING: "http://localhost:8080/api/v1/bookings",
    PAYMENT: "http://localhost:8080/api/v1/payment",
    CHAT: "http://localhost:8080/api/v1/chat",
    MAIL: "http://localhost:8080/api/v1/mail",
    MARKING: "http://localhost:8080/api/v1/marking",
    STAFF: "http://localhost:8080/api/v1/staffs",
    STAFF_DEPARTMENT: "http://localhost:8080/api/v1/departments",
    STAFF_POSITION: "http://localhost:8080/api/v1/positions",
    NOTIFICATION: "http://localhost:8080/api/v1/notifications",
    REVIEW: "http://localhost:8080/api/v1/review"
}

