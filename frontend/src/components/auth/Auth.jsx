/*import axios from 'axios';*/
import {useState} from "react";
import {Input} from "../../utils/registration/Input";
import {useDispatch/*, useSelector*/} from "react-redux";
import {api} from "../../utils/axios/api";
import {login} from "../../store/slice/auth/auth-index";
import {useNavigate} from "react-router-dom";
/*import {useAuth} from "../../utils/hook";*/
import "../../css/auth-reg.css"

export const Auth = () => {
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const dispatch = useDispatch()
    const navigate = useNavigate()

    const authAction = async (email, password) => {
        try {
            const response = await api.post('login', {
                email,
                password
            })
            /*alert(response.data)*/
            await dispatch(login(response.data)); //передаем поулченные данные в редьюсер в auth
            navigate('/')
            alert(response.data)
        } catch (e) {
            alert(e)
        }

/*        return async dispatch => {
            try {
                const response = await axios.post('http://localhost:3000/api/auth/auth', {
                    email,
                    password
                })
                alert(response.data)
            } catch (e) {
                alert(e)
                /!*alert(e.response.data.message)*!/
            }
        }*/
    }

    //Для проверки
    /*const withUseAuth = useAuth()
    const vLoggineLi = useSelector((state) => state.auth.isLogged)*/

    return (
        <div className="auth-body">
            {/*<h1>Log: {vLoggineLi ? "Yes" : "No"}</h1>
            <h2>Log2: {withUseAuth ? "Yes" : "No"}</h2>*/}
            <div className="rectangle">
                <div className="auth-form">
                    <p className={"p-auth"}>Авторизация</p>
                    <Input value={email} setValue={setEmail} type="email" placeholder="Почта" />
                    <Input value={password} setValue={setPassword} type="password" placeholder="Пароль"/>
                    {/*<button className={"auth-button"} onClick={() => dispatch(authAction(email, password))} >Войти</button>*/}
                    <button className={"auth-button"} onClick={() => authAction(email, password)}>Войти</button>

                </div>
                <hr className="hr-form"/>
                <p className="p-question">Еще не зарегистрированы?</p>
                <button className="button-to-reg">Зарегистрироваться</button>
            </div>
        </div>
    )
}