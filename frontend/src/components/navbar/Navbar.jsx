import {Link, NavLink} from "react-router-dom";
import Box from '@mui/material/Box';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import {useState} from "react";
import {ButtonToProfile} from "./ButtonToProfile";
import {useAuth} from "../../utils/hook";

export function Navbar() {
    const [value, setValue] = useState(0);

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    const withUseAuth = useAuth()


    //в base можно подкрутить переключение в зависомости от страницы
    return (
        <div>
            <div>
                <Box sx={{ width: '100%', bgcolor: 'background.paper' }}>
                    <Tabs value={value} onChange={handleChange} centered>
                        <NavLink to="/"><Tab label="На главную">На главную</Tab></NavLink>
                        {!withUseAuth && (
                            <>
                            <NavLink to="/auth"><Tab label="Войти">Войти</Tab></NavLink>
                            <Link to="/registration"><Tab label="Регистрация">Регистрация</Tab></Link>
                            </>
                            )}


                        {/*<NavLink to="/auth"><Tab label="Войти">Войти</Tab></NavLink>
                        <Link to="/registration"><Tab label="Регистрация">Регистрация</Tab></Link>*/}

                        {/*<NavLink to="/users/123">To 123</NavLink>*/}
                        {/*{withUseAuth && (
                            <>
                                {user.author ? (
                                    <NavLink to={`/users/${user.id}`}>
                                        <Tab label={user.email} />
                                    </NavLink>
                                ) : (
                                    <Tab label={user.email} />
                                )}
                            </>
                        )}*/}
                        <ButtonToProfile/>

                    </Tabs>

                    {/*<div><NavLink to="/">На главную</NavLink></div>
                    <div><NavLink to="/auth">Войти</NavLink></div>
                    <div><NavLink to="/registration">Регистрация</NavLink></div>*/}
                </Box>
            </div>
        </div>
    )
}
