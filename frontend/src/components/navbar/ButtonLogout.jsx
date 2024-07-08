import {useDispatch} from "react-redux";
import {useNavigate} from "react-router-dom";
import {logout} from "../../store/slice/auth/auth-index";
import Tab from "@mui/material/Tab";
import {DropdownItem} from "@nextui-org/react";

export const ButtonLogout = () => {
    const dispatch = useDispatch()
    const navigate = useNavigate()

    const logoutAction = () => {
        dispatch(logout())
        navigate("/auth")
    }

    return (
        <DropdownItem key="logout" color="danger" className="text-danger" onClick={logoutAction}>
            Выйти
        </DropdownItem>
    )
}