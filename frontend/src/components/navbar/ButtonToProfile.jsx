import {useSelector} from "react-redux";
import {useAuth} from "../../utils/hook";
import {NavLink} from "react-router-dom";
import Tab from "@mui/material/Tab";
import {ButtonLogout} from "./ButtonLogout";


export const ButtonToProfile = () => {
    const withUseAuth = useAuth()
    const user = useSelector((state) => state.auth.user);

    return (<div>
            {withUseAuth && (
                <>
                    {user.author ? (
                        <NavLink to={`/users/${user.id}`}>
                            <Tab label={user.email} />
                        </NavLink>
                    ) : (
                        <Tab label={user.email} />

                    )}
                    <ButtonLogout/>
                </>
            )}
        </div>
    )
}