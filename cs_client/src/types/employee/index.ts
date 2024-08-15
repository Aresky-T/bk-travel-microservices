export interface IEmployee {
    id: number,
    accountId: number,
    accountEmail: string,
    status: string,
    fullName: string,
    avatarUrl: string | null,
    address: string | null,
    phone: string | null,
    dateOfBirth: string | null
    gender: string | null,
}

export interface IEmployeeState {
    data: IEmployee | null,
    isConnected: boolean,
}