import {Period} from "@/network/Booking";

export interface AvailabilityResponse {
    roomId: number,
    requestedPeriod: Period,
    booked: Period[]
}

