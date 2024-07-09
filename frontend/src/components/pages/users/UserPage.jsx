import {Link, useParams} from "react-router-dom";
import {useSelector} from "react-redux";
import "../../../css/user-page.css"
import edit_icon from "../../../assets/img/userpage/edit_Icon.svg"
import report_icon from "../../../assets/img/userpage/report-icon.svg"

import avatar from "../../../assets/img/userpage/avatar.svg"
import {Button, Progress, Spinner} from "@nextui-org/react";
import {useEffect, useState} from "react";
import axios from "axios";
import {api} from "../../../utils/axios/api";


export const UserPage = () => {
    const {id} = useParams()
    const user = useSelector((state) => state.auth.user);
    /*    const dispatch = useDispatch();*/

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
                const response = await api.get(`loadprofileinfo?profileURL=${id}`)
                setUserData(response.data)
                console.log(userData)
            } catch (e) {
                alert(e)
            }
        }
        getUserInfo()
    }, [id]) //id в массив зависимостей, то есть, если изменится айди, происходит новый запрос

    if (!userData) {
        return /*<div>Loading...</div>*/ <Spinner/>;
    }

    const checkId = () => {
        return id === user.id;
    }


    /*    const handleUpdateUser = () => {
            dispatch(updateUser({ email: "xdddd@gmail.com"}));
        };*/

    return (
        <div>
            <div className={"frame-border"}>
                <div className={"profile-container"}>
                    <div className={"info-ava-profile"}>
                        {/*<img src={avatar} alt={"txt"} className={"img-avatar"}/>*/}
                        <img src={userData.img_avatar} alt={"txt"} className={"img-avatar"}/>
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
                            <span className={"display-name-header-profile"}>{userData.displayname}</span>
                            <div className={"id-bio-container"}>
                                <span className={"id-profile-header"}>@{userData.url_prof}</span>
                                {/*<span className={"id-profile-header"}>@{idProfileURL()}</span>*/}
                                <span className={"id-profile-header overflow-hide-bio"}>{userData.micro_bio}</span>
                            </div>
                        </div>
                    </div>
                    <div className={"banner"}>
                        {/*<img src={userData.avatar} alt={"banner"} className={"banner"}/>*/}
                        <img src={userData.img_banner} alt={"banner"} className={"banner"}/>
                    </div>
                </div>

                <div className={"three-container"}>
                    <div className={"panel-about-container"}>
                        <div className={"interactive-panel-container"}>
                            <div className={"stats-user-info"}>
                                <span>228,133 Платных подписчиков</span>
                                <span>1,337,228 Подписчиков</span>
                            </div>


                            {!checkId() ? (
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
                            <span className={"about-author"}>{userData.about_info}</span>
                            {/*<span className={"about-author"}>Lorem ipsum Lorem ip Lorem ipsum Lorem ip Lorem ipsum ipsum m ipsum Lorem ip Lorem ipsum m ipsum Lorem ip Lorem Lor Lorem ip Lor Lorem ip Lor Lorem ip Lorem ipsum orem ipsum Lorem ip Lorem ipsum m ipsum Lorem ip Lorem ipsum m ipsum Lorem ip Lorem ipsum... read more</span>*/}
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

                    {/*посты*/}
                    <div className={"post-frame"}>
                        <div className={"post-banner-info-user"}>
                            <div className={"post-banner"}>
                                <span>header</span>
                            </div>
                            <div className={"post-banner-about"}>
                                <span>@hello</span>
                            </div>
                        </div>

                        <div className={"post-media-info"}>
                            <div className={"post-media-preview"}>
                                <span>img</span>
                            </div>
                            <div className={"post-info-with-likes"}>
                                <span className={"post-span-info"}>info info info</span>
                                <div className={"post-likes-comments"}>
                                    likes <br/> comments
                                </div>
                            </div>
                        </div>


                    </div>
                </div>




            </div>

        </div>
    )
}