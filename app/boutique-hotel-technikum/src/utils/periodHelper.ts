import {DatePeriod, Period} from "@/network/Booking";

export function periodToDatePeriod(period: Period): DatePeriod {
    return {
        start: new Date(period.start),
        end: new Date(period.end)
    }
}

export function isBooked(date: Date, booked: DatePeriod[]) {
    for (const period of booked) {
        if (period.start > date) {
            return false;
        }
        if (period.end > date && period.start < date) {
            return true;
        }
    }
    return false;
}