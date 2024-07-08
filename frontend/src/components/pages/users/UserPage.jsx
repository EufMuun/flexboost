import {Link, useParams} from "react-router-dom";
import {useSelector} from "react-redux";
import "../../../css/user-page.css"
import edit_icon from "../../../assets/img/userpage/edit_Icon.svg"
import report_icon from "../../../assets/img/userpage/report-icon.svg"

import avatar from "../../../assets/img/userpage/avatar.svg"
import {Button, Progress} from "@nextui-org/react";
import {useEffect, useState} from "react";
import axios from "axios";


export const UserPage = () => {
    const {id} = useParams()
    const user = useSelector((state) => state.auth.user);

    const displayName = () => {
        return "Altushka-1337"
    }

    const idProfileURL = () => {
        return "hochu_skufa"
    }

    const [value, setValue] = useState(10);
    const [subsGoals, updGolas] = useState(100);

    useEffect(() => {
        const interval = setInterval(() => {
            setValue((v) => (v >= 100 ? 0 : v + 10));
            updGolas((g) => (g >= 1000 ? 0 : g + 100));
        }, 2500);

        return () => clearInterval(interval);
    }, []);


    const [userData, setUserData] = useState(null)
    useEffect(() => {
        const getUserInfo = async () => {
            try {
                const response = await axios.get(`https://reqres.in/api/users/${id}`)
                setUserData(response.data.data)
            } catch (e) {
                alert(e)
            }
        }
        getUserInfo()
    }, [id]) //id в массив зависимостей, то есть, если изменится айди, происходит новый запрос

    if (!userData) {
        return <div>Loading...</div>;
    }

    const checkId = () => {
        return id === user.id;
    }

    return (
        <div>
            <div className={"frame-border"}>
                <div className={"profile-container"}>
                    <div className={"info-ava-profile"}>
                        <img src={avatar} alt={"txt"} className={"img-avatar"}/>
                        {checkId() ? (
                            <Link to={"/"}>
                                <img src={edit_icon} alt={"edit"} className={"edit-icon"}/>
                            </Link>
                        ) : (
                            <Link to={"/"}>
                                <img src={report_icon} alt={"report"} className={"edit-icon"}/>
                            </Link>
                        )}

                        <div className={"user-info"}>
                            <span className={"display-name-header-profile"}>{displayName()}</span>
                            <div className={"id-bio-container"}>
                                <span className={"id-profile-header"}>@{userData.first_name}</span>
                                {/*<span className={"id-profile-header"}>@{idProfileURL()}</span>*/}
                                <span className={"id-profile-header overflow-hide-bio"}>Lorem ipsum bio {userData.last_name}</span>
                            </div>
                        </div>
                    </div>
                    <div className={"banner"}>
                        <img src={"https://i.ibb.co/YkNdSM3/Banner.png"} alt={"banner"} className={"banner"}/>
                    </div>
                </div>

                <div className={"three-container"}>
                    <div className={"panel-about-container"}>
                        <div className={"interactive-panel-container"}>
                            <div className={"stats-user-info"}>
                                <span>228,133 Платных подписчиков</span>
                                <span>1,337,228 Подписчиков</span>
                            </div>


                                {checkId() ? (
                                    <div className={"buttons-interact-user"}>
                                        <Button className={"sub-button"}>Подписаться - 169₽</Button>
                                        <Button className={"interactive-buttons"}>Отслеживать</Button>
                                        <Button className={"interactive-buttons"}>Донат</Button>
                                        <Button className={"interactive-buttons"}>Сообщение</Button>
                                    </div>
                                ) : (
                                        <div className={"buttons-interact-user"}>
                                        <Button className={"sub-button"}>Новый пост</Button>
                                        <Button className={"interactive-buttons"}>Статистика</Button>
                                        </div>
                            )}

                        </div>

                        <div className={"about-container"}>
                            <span className={"fs24-fw400"}>About</span>
                            <span className={"about-author"}>Lorem ipsum Lorem ip Lorem ipsum Lorem ip Lorem ipsum ipsum m ipsum Lorem ip Lorem ipsum m ipsum Lorem ip Lorem Lor Lorem ip Lor Lorem ip Lor Lorem ip Lorem ipsum orem ipsum Lorem ip Lorem ipsum m ipsum Lorem ip Lorem ipsum m ipsum Lorem ip Lorem ipsum... read more</span>
                        </div>
                    </div>

                    <div className={"goals-container"}>
                        <span className={"fs24-fw400"}>Goals</span>
                        <div className={"current-goals"}>
                            <Progress
                                size="md"
                                value={value}
                                color="success"
                                showValueLabel={true}
                                className="max-w-md"
                                label={`${subsGoals} из 1000 платных подписчиков`}
                            />
                        </div>
                    </div>
                </div>
            </div>

            <div className={"frame-border frame-border-pink"}>
               <div className={"posts-frame-user"}>
                   <div className={"posts-profile-header"}>
                       <span className={"fs-40-fw400"}>Posts by Altushka-1337</span>
                       <span className={"fs32-fw300"}>69 posts</span>
                   </div>
               </div>

                {/*посты*/}



            </div>

        </div>
    )
}