import "../../css/my-new-nav-bar.css"
export const MyNewNavBar = () => {
    return (
        <div>
            <div className={"header-nav-bar"}>
                <div>
                    <span>NeoBoost</span>
                </div>
                <input className={"search-profile"} type="text" placeholder="Search" />
            </div>
        </div>
    )
}