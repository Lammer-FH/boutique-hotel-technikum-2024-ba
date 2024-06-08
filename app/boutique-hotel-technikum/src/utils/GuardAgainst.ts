import {isLess} from "@/utils/GuardIs";

export function less(value: number, boundary: number) {
    if (isLess(value, boundary)) {
        throw new RangeError(`${value} must not be less than ${boundary}`);
    }
}