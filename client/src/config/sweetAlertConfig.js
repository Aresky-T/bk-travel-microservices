import Swal from "sweetalert2"

export const successAlertNoButton = async (title, text) => {
    return await Swal.fire({
        title: title,
        icon: "success",
        text: text,
    })
}

export const successAlert = async (title, text, confirmButtonText) => {
    return await Swal.fire({
        title: title,
        icon: "success",
        text: text,
        confirmButtonText: confirmButtonText || "OK",
    })
}

export const warningAlert = async (title, text, options) => {
    return await Swal.fire({
        title: title,
        icon: "warning",
        text: text,
        showCancelButton: options?.cancelButtonText,
        showConfirmButton: options?.confirmButtonText,
        cancelButtonText: options?.cancelButtonText || "",
        confirmButtonText: options?.confirmButtonText || "",
    })
}

export const warningAlertNoCancel = async (title, text, confirmButtonText) => {
    return await Swal.fire({
        title: title,
        icon: "warning",
        text: text,
        confirmButtonText: confirmButtonText
    })
}

export const warningAlertWithCancel = async (title, text, confirmButtonText, cancelButtonText) => {
    return await Swal.fire({
        title: title,
        icon: "warning",
        text: text,
        confirmButtonText: confirmButtonText,
        showCancelButton: true,
        cancelButtonText: cancelButtonText
    })
}


export const errorAlert = async (title, text, options) => {
    return await Swal.fire({
        title: title,
        icon: "error",
        text: text,
        showCancelButton: options?.cancelButtonText,
        showConfirmButton: options?.confirmButtonText,
        cancelButtonText: options?.cancelButtonText || "",
        confirmButtonText: options?.confirmButtonText || "",
    })
}

export const questionAlert = async (title, text, confirmButtonText, cancelButtonText) => {
    return await Swal.fire({
        title: title,
        icon: "question",
        text: text,
        showCancelButton: true,
        cancelButtonText: cancelButtonText ?? "OK",
        confirmButtonText: confirmButtonText ?? "Confirm"
    })
}

export const swalWithCustomStyleButtons = Swal.mixin({
    customClass: {
        confirmButton: 'custom-alert-btn custom-alert-btn--success',
        cancelButton: 'custom-alert-btn custom-alert-btn--cancel'
    },
    buttonsStyling: false
})