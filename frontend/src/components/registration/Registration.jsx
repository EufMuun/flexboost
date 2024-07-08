import axios from 'axios';
import {Input} from "../../utils/registration/Input";
import {useState} from "react";
import "../../css/auth-reg.css"

export const Registration = () => {
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [confirmPassword, setConfirmPassword] = useState("")

    const regAction = async (email, password) => {
        try {
            const response = await axios.post('http://localhost:8080/api/registration', {
                email,
                password
            })
            /*alert(response.data)*/
            /*alert(response.data.message)*/
            alert(response.data.result)
        } catch (e) {
            alert(e)
            /*alert(e.response.data.message)*/
        }
    }

    return (
        <div className="auth-body">
            <div className="rectangle">
                <div className="auth-form">
                    <p className={"p-auth"}>Регистрация</p>
                    <Input value={email} setValue={setEmail} type="email" placeholder="Почта" />
                    <Input value={password} setValue={setPassword} type="password" placeholder="Пароль"/>
                    <Input value={confirmPassword} setValue={setConfirmPassword} type="password" placeholder="Подтвердите пароль"/>
                    <button className={"auth-button"} onClick={() => regAction(email, password)} >Зарегистрироваться</button>
                </div>
            </div>
        </div>
    )
}