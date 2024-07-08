import {createSlice} from "@reduxjs/toolkit";

const initialState = {
    /*user: {},*/
    user: {
        email: "lololo@gmail.com",
        author: true,
        id: "1",

    },
    isLogged: true
}

export const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        /*login: (state, action) => {*/
        login(state, action) {
            state.user = action.payload
            state.isLogged = true
/*            console.log('Action', action.payload)
            console.log('User from state', state.user)
            console.log('Login', state.isLogged)*/
        },
        logout(state) {
            state.user = {};
            state.isLogged = false;
        },
        updateUser(state, action) {
            state.user = {
                ...state.user,
                ...action.payload,
            }
        }
    }
})

export const {login, logout, updateUser} = authSlice.actions
export default authSlice.reducer
