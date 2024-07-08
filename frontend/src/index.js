import React from 'react';
import * as ReactDOMClient from "react-dom/client"
import './index.css';
import {App} from "./components/App";


const app = ReactDOMClient.createRoot(document.getElementById("id1"))
app.render(<App />)

export const RegisterPage = () => {
    return (
        <div>
            <h1>Регистрация</h1>
        </div>
    )
}