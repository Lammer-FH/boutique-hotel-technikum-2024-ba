import {defineStore} from "pinia";
import {Address} from "@/stores/dataStructures/CustomerInfo";

export const useCustomerStore = defineStore({
    id: 'customer',
    state: () => ({
        firstName: "",
        lastName: "",
        eMail: "",
        address: {
            street: "",
            postCode: undefined as number | undefined,
            city: "",
            country: ""
        }
    }),
    getters: {
        isValid(state) {
          return !!(state.firstName && state.lastName && state.eMail);
        },
        hasAnyAddressInfo(state) {
            return !!(state.address.street || state.address.postCode || state.address.city || state.address.country);
        },
        addressToString(state) {
            return `${state.address.street}, ${state.address.postCode} ${state.address.city}, ${state.address.country}`;
        }
    },
    actions: {
        setName(firstName: string, lastName: string) {
            this.firstName = firstName;
            this.lastName = lastName;
        },
        setEMail(eMail: string) {
            this.eMail = eMail;
        },
        setAddress(address: Address) {
            this.address = address;
        }
    }
})