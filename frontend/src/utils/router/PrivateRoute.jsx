import {useAuth} from "../hook";
import {Navigate, Outlet} from "react-router-dom";
/*import {useSelector} from "react-redux";*/

export const PrivateRoute = () => {
    const auth = useAuth()
    return (
        auth ? <Outlet /> : <Navigate to="/auth"/>
    )
}

/*
export const PrivateRoute = () => {
    const loginLi = useSelector((state) => state.auth.isLogged)
    return (
        loginLi ? <Outlet /> : <Navigate to="/auth"/>
    )
}*/
