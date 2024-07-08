import {configureStore} from "@reduxjs/toolkit";
import authReducer from "./slice/auth/auth-index";


const store = configureStore({
    reducer: {
        /*auth: authSlice.reducer*/
        auth: authReducer
    }

})



export default store