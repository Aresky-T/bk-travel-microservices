export const configApi = (token: string) => {
    return {
        headers: {
            Authorization: `Bearer ${token}`
        }
    }
}