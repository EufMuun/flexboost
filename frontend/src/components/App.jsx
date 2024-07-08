/*import {Navbar} from "./navbar/Navbar";*/
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {Registration} from "./registration/Registration";
import {Auth} from "./auth/Auth";
/*import {RegisterPage} from "../index";*/
import {Provider} from "react-redux";
import store from "../store";
import {PrivateRoute} from "../utils/router/PrivateRoute";
import {RedirectRenderProfile} from "./pages/users/RedirectRenderProfile";
import {NotFoundPage} from "./pages/NotFoundPage";
import {NewNavBar} from "./navbar/NewNavBar";
import {NextUIProvider} from "@nextui-org/react";
import {MyNewNavBar} from "./navbar/MyNewNavBar";
import {UserPage} from "./pages/users/UserPage";

export function App() {
    return (
        <Provider store={store}>
            <NextUIProvider>
                <BrowserRouter>
                    <div>
                        <NewNavBar/>
                        <Routes>
                            <Route element={<PrivateRoute />}>
                            {/*<Route Component={PrivateRoute}>*/}
                                <Route path="/" element={<Home/>} />
                            </Route>
                            <Route path="auth" Component={Auth}/>
                            <Route path="/registration" Component={Registration}/>
                            {/*<Route path="/users/:id" element={<RedirectRenderProfile/>}/>*/}
                            <Route path="/users/:id" element={<UserPage/>}/>
                            <Route  path="*" element={<NotFoundPage/>}/>
                            {/*<Route path="/registration" element={<RegisterPage/>}/>*/}
                        </Routes>
                    </div>
                </BrowserRouter>
            </NextUIProvider>
        </Provider>
    )
}

function Home() {
    return <div>
        <h1>Home Page</h1>
        <h2>Она только для авторизованных!</h2>
    </div>;
}