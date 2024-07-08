import {useParams} from "react-router-dom";
import {useSelector} from "react-redux";
import {SelfAuthor} from "./SelfAuthor";
import {ViewAuthor} from "./ViewAuthor";

export const RedirectRenderProfile = () => {
    const {id} = useParams()
    const user = useSelector((state) => state.auth.user);
    /*const location = useLocation()*/

    const checkRender = () => {
        if (id === user.id) {
            return <SelfAuthor/>
        } else {
            return <ViewAuthor/>
        }
    }

    return (
        <div>
            <h>{id} -- {user.id}</h>
            {checkRender()}
        </div>
    )
}