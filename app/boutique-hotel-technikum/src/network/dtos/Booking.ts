export interface Customer {
    firstName: string,
    secondName: string,
    email: string,
    birthDate: string
}

export interface Period {
    start: string,
    end: string
}

export interface DatePeriod {
    start: Date,
    end: Date
}

export interface Booking {
    customer: Customer,
    breakfast: boolean,
    roomId: number,
    reservationPeriod: Period
}