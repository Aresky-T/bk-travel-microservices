import { EActivationStatus } from "../enum";

export default interface IStaff {
    id: number,
    email: string,
    fullName: string,
    avatarUrl: string,
    status: EActivationStatus
}