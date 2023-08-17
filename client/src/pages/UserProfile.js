import React, { useState, useEffect } from "react";
import styled from "styled-components";
import Profile from "../components/Profile";
import Settings from "../components/Settings";
import { useParams } from "react-router";
import axios from "axios";
import { userProfileData } from "../API/ServerAPI";
import { BREAK_POINT_TABLET, BREAK_POINT_MOBILE } from "./../data/breakpoints";

const ProfileBox = styled.div`
	width: 100%;
	padding: 24px;
	@media screen and (max-width: ${BREAK_POINT_TABLET}px) {
		padding: 24px 16px;
	}
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
		width: 100%;
		height: 100%;
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
		margin: 4px;
		color: var(--gray);

		svg {
			color: inherit;
			path {
				fill: currentColor;
			}
		}
	}
	@media screen and (max-width: ${BREAK_POINT_TABLET}px) {
		.memberImg {
			width: 96px;
			height: 96px;
		}
	}
	@media screen and (max-width: ${BREAK_POINT_MOBILE}px) {
		.memberImg {
			width: 64px;
			height: 64px;
		}
		.memberName {
			font-size: 25px;
		}
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
		line-height: var(--line-height-md);
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

const UserProfile = ({ setFlag, setIsFooter }) => {
	const { id } = useParams();
	const [userData, setUserData] = useState(undefined);
	const [userDate, setUserDate] = useState(undefined);
	useEffect(() => {
		const userDataList = async () => {
			const userData2 = await userProfileData(id);
			setUserData(userData2);

			const 지금 = new Date();
			const 프로필 = new Date(userData2.profileRegDate);
			const diffDate = 지금.getTime() - 프로필.getTime();
			const day = Math.abs(diffDate / (1000 * 60 * 60 * 24));
			setUserDate(day < 1 ? "today" : Math.ceil(day));
		};
		userDataList();
	}, []);

	const [openTab, setOpenTab] = useState([
		{
			id: 0,
			name: "profile",
			url: `profile/${id}`,
		},
		{ id: 1, name: "settings", url: `profile/edit/${id}` },
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
							src="https://static.wikia.nocookie.net/pokemon/images/1/18/%EB%82%98%EC%98%A4%ED%95%98_%EA%B3%B5%EC%8B%9D_%EC%9D%BC%EB%9F%AC%EC%8A%A4%ED%8A%B8.png/revision/latest/scale-to-width-down/1200?cb=20220301123010&path-prefix=ko"
							alt="user profile image"
						/>
					</a>
					<div className="memberData">
						<div className="memberName">{userData && userData.name}</div>
						<ul>
							<li>
								<div className="svgIcon">
									<svg width="18" height="18" viewBox="0 0 18 18">
										<path d="M9 4.5a1.5 1.5 0 0 0 1.28-2.27L9 0 7.72 2.23c-.14.22-.22.48-.22.77 0 .83.68 1.5 1.5 1.5Zm3.45 7.5-.8-.81-.81.8c-.98.98-2.69.98-3.67 0l-.8-.8-.82.8c-.49.49-1.14.76-1.83.76-.55 0-1.3-.17-1.72-.46V15c0 1.1.9 2 2 2h10a2 2 0 0 0 2-2v-2.7c-.42.28-1.17.45-1.72.45-.69 0-1.34-.27-1.83-.76Zm1.3-5H10V5H8v2H4.25C3 7 2 8 2 9.25v.9c0 .81.91 1.47 1.72 1.47.39 0 .77-.14 1.03-.42l1.61-1.6 1.6 1.6a1.5 1.5 0 0 0 2.08 0l1.6-1.6 1.6 1.6c.28.28.64.43 1.03.43.81 0 1.73-.67 1.73-1.48v-.9C16 8.01 15 7 13.75 7Z"></path>
									</svg>
								</div>
								<p>
									Member for {userDate} {userData === "today" ? null : "day"}
								</p>
							</li>
							<li>
								<div className="svgIcon">
									<svg width="18" height="18" viewBox="0 0 18 18">
										<path d="M9 17c-4.36 0-8-3.64-8-8 0-4.36 3.64-8 8-8 4.36 0 8 3.64 8 8 0 4.36-3.64 8-8 8Zm0-2c3.27 0 6-2.73 6-6s-2.73-6-6-6-6 2.73-6 6 2.73 6 6 6ZM8 5h1.01L9 9.36l3.22 2.1-.6.93L8 10V5Z"></path>
									</svg>
								</div>
								<p>Last seen this week</p>
							</li>
							<li>
								<div className="svgIcon">
									<svg width="17" height="18" viewBox="0 0 17 18">
										<path d="M2 6.38C2 9.91 8.1 17.7 8.1 17.7c.22.29.58.29.8 0 0 0 6.1-7.8 6.1-11.32A6.44 6.44 0 0 0 8.5 0 6.44 6.44 0 0 0 2 6.38Zm9.25.12a2.75 2.75 0 1 1-5.5 0 2.75 2.75 0 0 1 5.5 0Z"></path>
									</svg>
								</div>
								<p>{userData && userData.location ? userData.location : "미입력"}</p>
							</li>
							<li>
								<div className="svgIcon">
									<svg width="18" height="18" viewBox="0 0 18 18">
										<path d="M9 1a8 8 0 0 0-2.53 15.59c.4.07.55-.17.55-.38l-.01-1.49c-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82a7.42 7.42 0 0 1 4 0c1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48l-.01 2.2c0 .21.15.46.55.38A8.01 8.01 0 0 0 9 1Z"></path>
									</svg>
								</div>
								<p>{userData && userData.homepage ? userData.homepage : "미입력"}</p>
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
								className={checked === index ? "tabBtn tabChecked" : "tabBtn"}
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
			<ProfileBody>{userData && checked === 0 ? <Profile userData={userData} /> : <Settings id={id} userData={userData} />}</ProfileBody>
		</ProfileBox>
	);
};

export default UserProfile;
