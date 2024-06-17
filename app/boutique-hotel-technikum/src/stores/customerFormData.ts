import {defineStore} from "pinia";

export const useCustomerFormData = defineStore({
    id: 'customerFormData',
    state: () => ({
        firstName: "",
        lastName: "",
        eMail: "",
        eMailRepeat: "",
        breakfast: true,
        address: {
            street: "",
            postCode: undefined as number | undefined,
            city: "",
            country: ""
        }
    })
})