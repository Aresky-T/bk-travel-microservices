import Swal from "sweetalert2"

export const successAlert = async (title: string, text: string, confirmButtonText: string) => {
    return await Swal.fire({
        title: title,
        icon: "success",
        text: text,
        confirmButtonText: confirmButtonText,
    })
}

export const warningAlertNoCancel = async (title: string, text: string, confirmButtonText: string) => {
    return await Swal.fire({
        title: title,
        icon: "warning",
        text: text,
        confirmButtonText: confirmButtonText
    })
}

export const warningAlertWithCancel = async (title: string, text: string, confirmButtonText: string, cancelButtonText: string) => {
    return await Swal.fire({
        title: title,
        icon: "warning",
        text: text,
        confirmButtonText: confirmButtonText,
        showCancelButton: true,
        cancelButtonText: cancelButtonText
    })
}


export const errorAlert = async (title: string, text: string) => {
    return await Swal.fire({
        title: title,
        icon: "error",
        text: text,
    })
}

export const questionAlert = async (title: string, text: string, confirmButtonText: string, cancelButtonText: string) => {
    return await Swal.fire({
        title: title,
        icon: "question",
        text: text,
        showCancelButton: true,
        cancelButtonText: cancelButtonText,
        confirmButtonText: confirmButtonText
    })
}

export const swalWithCustomStyleButtons = Swal.mixin({
    customClass: {
        confirmButton: 'custom-alert-btn custom-alert-btn--success',
        cancelButton: 'custom-alert-btn custom-alert-btn--cancel'
    },
    buttonsStyling: false
})