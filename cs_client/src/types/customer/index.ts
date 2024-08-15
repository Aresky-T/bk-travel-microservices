import { EActivationStatus } from "../enum";

export default interface ICustomer {
    id: number,
    email: string,
    fullName: string,
    avatarUrl?: string | null,
    status: EActivationStatus
}