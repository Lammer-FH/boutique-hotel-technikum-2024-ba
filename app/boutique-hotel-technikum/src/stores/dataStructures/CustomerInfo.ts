export interface Address {
    street: string,
    postCode: number,
    city: string,
    country: string
}

export interface CustomerInfo {
    firstName: string,
    lastName: string,
    eMail: string,
    address: Address
}