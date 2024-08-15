export interface IProfile {
    id: number,
    avatarUrl: string,
    fullName: string,
    address: string,
    phone: string,
    dateOfBirth: string,
    gender: string
}

export interface IProfileState {
    data: IProfile | null
}