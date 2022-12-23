import React, { useState } from "react";
import styled from "styled-components";
import Profile from "../components/Profile";
import Settings from "../components/Settings";

const ProfileBox = styled.div`
    width: 100%;
`;

const ProfileHeader = styled.div`
    position: relative;
    margin-bottom: 16px;
    .memberDataBox {
        display: flex;
        align-items: center;
        flex-wrap: wrap;
    }
    .memberImg {
        width: 128px;
        height: 128px;
        margin: 8px;
    }
    .memberImg img {
        border-radius: 5px;
    }
    .memberData {
        margin: 8px;
    }
    .memberName {
        font-size: 34px;
        margin: 4px 4px 12px;
    }
    .svgIcon {
        margin: 0 2px;
    }
    ul {
        display: flex;
    }
    li {
        display: flex;
        align-items: center;
        justify-content: center;
        margin: 4px 2px;
    }
`;

const ProfileTab = styled.div`
    margin-bottom: 16px;
    .tabBox {
        display: flex;
        gap: 4px;
    }
    li a {
        display: block;
        text-decoration: none;
        color: inherit;
    }
    .tabBtn {
        padding: 6px 12px;
        border: none;
        border-radius: 20px;
        background-color: var(--white);
        color: var(--nav-link-color);
        cursor: pointer;
    }
    .tabChecked {
        color: var(--white);
        background-color: var(--orange);
    }
    .tabBtn:hover {
        background-color: #e3e6e8;
    }
    .tabBtn.tabChecked:hover {
        background-color: var(--orange);
    }
`;

const ProfileBody = styled.div``;
const UserProfile = () => {
    const [openTab, setOpenTab] = useState([
        {
            id: 0,
            name: "profile",
            url: "profile/12",
        },
        { id: 1, name: "settings", url: "profile/edit/12" },
    ]);
    const [checked, setChecked] = useState(0);
    function tabHandle(index) {
        setChecked(index);
    }

    return (
        <ProfileBox>
            <ProfileHeader>
                <div className="memberDataBox">
                    <a className="memberImg">
                        <img
                            width="128"
                            height="128"
                            src="https://i.stack.imgur.com/hMDvl.jpg?s=256&g=1"
                        />
                    </a>
                    <div className="memberData">
                        <div className="memberName">jezrael</div>
                        <ul>
                            <li>
                                <div className="svgIcon">
                                    <svg
                                        width="18"
                                        height="18"
                                        viewBox="0 0 18 18"
                                    >
                                        <path d="M9 4.5a1.5 1.5 0 0 0 1.28-2.27L9 0 7.72 2.23c-.14.22-.22.48-.22.77 0 .83.68 1.5 1.5 1.5Zm3.45 7.5-.8-.81-.81.8c-.98.98-2.69.98-3.67 0l-.8-.8-.82.8c-.49.49-1.14.76-1.83.76-.55 0-1.3-.17-1.72-.46V15c0 1.1.9 2 2 2h10a2 2 0 0 0 2-2v-2.7c-.42.28-1.17.45-1.72.45-.69 0-1.34-.27-1.83-.76Zm1.3-5H10V5H8v2H4.25C3 7 2 8 2 9.25v.9c0 .81.91 1.47 1.72 1.47.39 0 .77-.14 1.03-.42l1.61-1.6 1.6 1.6a1.5 1.5 0 0 0 2.08 0l1.6-1.6 1.6 1.6c.28.28.64.43 1.03.43.81 0 1.73-.67 1.73-1.48v-.9C16 8.01 15 7 13.75 7Z"></path>
                                    </svg>
                                </div>
                                <p>Member for 9 years, 2months</p>
                            </li>
                            <li>
                                <div className="svgIcon">
                                    <svg
                                        width="18"
                                        height="18"
                                        viewBox="0 0 18 18"
                                    >
                                        <path d="M9 17c-4.36 0-8-3.64-8-8 0-4.36 3.64-8 8-8 4.36 0 8 3.64 8 8 0 4.36-3.64 8-8 8Zm0-2c3.27 0 6-2.73 6-6s-2.73-6-6-6-6 2.73-6 6 2.73 6 6 6ZM8 5h1.01L9 9.36l3.22 2.1-.6.93L8 10V5Z"></path>
                                    </svg>
                                </div>
                                <p>Last seen this week</p>
                            </li>
                        </ul>
                        <ul>
                            <li>
                                <div className="svgIcon">
                                    <svg
                                        width="17"
                                        height="18"
                                        viewBox="0 0 17 18"
                                    >
                                        <path d="M2 6.38C2 9.91 8.1 17.7 8.1 17.7c.22.29.58.29.8 0 0 0 6.1-7.8 6.1-11.32A6.44 6.44 0 0 0 8.5 0 6.44 6.44 0 0 0 2 6.38Zm9.25.12a2.75 2.75 0 1 1-5.5 0 2.75 2.75 0 0 1 5.5 0Z"></path>
                                    </svg>
                                </div>
                                <p>Bratislava, Slovakia</p>
                            </li>
                        </ul>
                    </div>
                </div>
            </ProfileHeader>
            <ProfileTab>
                <ul className="tabBox">
                    {openTab.map((data, index) => {
                        return (
                            <li
                                key={index}
                                className={
                                    checked === index
                                        ? "tabBtn tabChecked"
                                        : "tabBtn"
                                }
                                onClick={() => {
                                    tabHandle(index);
                                }}
                            >
                                {data.name}
                            </li>
                        );
                    })}
                </ul>
            </ProfileTab>
            <ProfileBody>
                {checked === 0 ? <Profile /> : <Settings />}
            </ProfileBody>
        </ProfileBox>
    );
};

export default UserProfile;
