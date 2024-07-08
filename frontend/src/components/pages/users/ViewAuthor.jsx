import {Container} from "@mui/material";
import Box from "@mui/material/Box";
import {useParams} from "react-router-dom";
import {useSelector} from "react-redux";

export const ViewAuthor = () => {
    const {id} = useParams()
    const user = useSelector((state) => state.auth.user);
    return (
        <div>
            <Container maxWidth="sm">
                <Box sx={{ bgcolor: '#cfe8fc',
                    marginTop: '10px',
                    paddingTop: '20px',
                    height: '50vh',
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                    '& h3, & h2': {
                        margin: 0,
                    }}}>
                    <h3>id: {id}</h3>
                    {/*<h1>{location.pathname}</h1>*/}
                    <h2>{user.email}</h2>
                    <h1>Подписаится</h1>
                </Box>
            </Container>
        </div>
    )
}