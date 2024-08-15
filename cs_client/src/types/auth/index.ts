// {
//     "id": 0,
//     "token": "string",
//     "type": "string",
//     "username": "string",
//     "email": "string",
//     "role": "string",
//     "status": "string"
//   }

import { EAccountRole, EAccountStatus } from "../enum"

export interface IAuth {
    id: number | null,
    token: string | null,
    type: string | null
    username: string | null,
    email: string | null,
    role: EAccountRole | null,
    status: EAccountStatus | null,
}

export interface IAuthState {
    data: IAuth,
    isLoading: boolean,
    isSuccess: boolean,
    isError: boolean,
    errorMessage: string
}