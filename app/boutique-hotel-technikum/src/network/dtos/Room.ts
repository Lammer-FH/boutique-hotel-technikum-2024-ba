export interface Position {
    longitude: number,
    latitude: number
}

export interface Hotel {
    id: number,
    name: string,
    position: Position,
    directions: string,
    address: string,
    contactPhoneNumber: string
}
export interface Extra {
    id: number,
    name: string,
    description: string
}

export interface Room {
    id: number,
    hotel: Hotel,
    title: string,
    roomNumber: number,
    description: string,
    extras: Extra[],
    bedAmount: number,
    price: number

}