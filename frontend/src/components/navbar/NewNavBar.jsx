import {useAuth} from "../../utils/hook";
import {
    Navbar,
    NavbarBrand,
    NavbarContent,
    NavbarItem,
    NavbarMenuToggle,
    NavbarMenu,
    NavbarMenuItem,
    Link,
    Button, DropdownItem, DropdownMenu, DropdownTrigger, Dropdown, Avatar
} from "@nextui-org/react";

import "../../css/my-new-nav-bar.css"

import pfp from "../../assets/img/PFP.png"
import {useDispatch, useSelector} from "react-redux";
import {useNavigate, useParams} from "react-router-dom";
import {logout} from "../../store/slice/auth/auth-index";

export const NewNavBar = () => {
    const {id} = useParams()
    const user = useSelector((state) => state.auth.user);
    const withUseAuth = useAuth()

    const dispatch = useDispatch()
    const navigate = useNavigate()

    const logoutAction = () => {
        dispatch(logout())
        navigate("/auth")
    }

    const ToSignUo = () => {
        navigate("/registration")
    }

    const ToFeed = () => {
        navigate("/")
    }

    const ToAuth = () => {
        navigate("/auth")
    }

    const ToProfile = () => {
        navigate(`/users/${user.id}`)
    }

    return (
        <div>
            <Navbar className={"new-nav-bar-header"}> {/* shouldHideOnScroll*/}
                <NavbarBrand>
                    <p className={"logo-name"}>NeoBoost</p>
                </NavbarBrand>
                <NavbarContent className="hidden sm:flex gap-12" justify="center" >
                    <NavbarItem>
                        <Link onClick={ToFeed} className="center-buttons">
                            Лента
                        </Link>
                    </NavbarItem>

                    <NavbarItem>
                        <Link className={"active-nav-button"}>
                            Button
                        </Link>
                    </NavbarItem>

                    <NavbarItem>
                        <Link color="foreground" to="#" className="center-buttons">
                            Подписки
                        </Link>
                    </NavbarItem>
                </NavbarContent>

                {!withUseAuth ? (
                    <NavbarContent justify="end">
                        <Button variant="flat" onClick={ToAuth} className={"to-signup-header"}>
                            Login
                        </Button>
                        <Button variant="flat" onClick={ToSignUo} className={"to-signup-header"}>
                            Sign up
                        </Button>
                    </NavbarContent>
                ) : (
                    <NavbarContent as="div" className="items-center" justify="end">
                        <Dropdown placement="bottom-end">
                            <DropdownTrigger>
                                <Avatar className="avatar"
                                        size="lg"
                                        as="button"
                                        src={user.img_avatar}
                                />
                            </DropdownTrigger>
                            <DropdownMenu>
                                <DropdownItem key="profile">
                                    <Link className="to-profile-header" onClick={ToProfile}>{user.email}</Link>
                                </DropdownItem>
                                <DropdownItem key="settings">My Settings</DropdownItem>
                                <DropdownItem key="logout" color="danger" className="text-danger" onClick={logoutAction}>
                                    Выйти
                                </DropdownItem>
                            </DropdownMenu>
                        </Dropdown>
                    </NavbarContent>
                )}
            </Navbar>
        </div>

    )
}