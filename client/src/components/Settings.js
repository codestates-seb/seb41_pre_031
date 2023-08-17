import React, { useState } from "react";
import styled from "styled-components";
import { Link, Route, Routes } from "react-router-dom";
import Delete from "./Delete";
import Edit from "./Edit";
import { BREAK_POINT_TABLET } from "./../data/breakpoints";

const Setting = styled.div`
	display: flex;
	margin-bottom: 48px;
	@media screen and (max-width: ${BREAK_POINT_TABLET}px) {
		flex-direction: column;
	}
`;
const SettingNav = styled.ul`
	flex: none;
	width: 180px;
	margin-right: 32px;
	li {
		cursor: pointer;
	}
	li a {
		display: block;
		text-decoration: none;
		color: inherit;
	}
	.tabTitle {
		padding: 6px 12px;
		line-height: var(--line-height-md);
		font-size: 11px;
		color: var(--dark);
		font-weight: bold;
	}
	.tabBtn {
		width: 100%;
		padding: 6px 12px;
		border: none;
		border-radius: 20px;
		background-color: var(--white);
		line-height: 17px;
		color: var(--nav-link-color);
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
	.deleteBox {
		width: calc(100% - 196px);
		flex-grow: 1;
	}
	@media screen and (max-width: ${BREAK_POINT_TABLET}px) {
		width: 100%;
		margin-right: 0;
		margin-bottom: 24px;
		.tabTitle {
			width: 100%;
		}
		.tabBtn {
			float: left;
			width: auto;
		}
	}
`;
const SettingMain = styled.div`
	flex: 1;
`;

const Settings = ({ id, userData }) => {
	const [openTab, setOpenTab] = useState([
		{
			id: 0,
			name: "Edit profile",
			url: "profile/edit/" + id,
		},
		{ id: 1, name: "Delete profile", url: "profile/delete/" + id },
	]);
	const [checked, setChecked] = useState(0);
	function tabHandle(index) {
		setChecked(index);
	}

	return (
		<Setting>
			<SettingNav>
				<li className="tabTitle">PERSONAL INFORMATION</li>
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
			</SettingNav>
			<SettingMain>{userData && checked === 0 ? <Edit userData={userData} id={id} /> : <Delete className="deleteBox" id={id} />}</SettingMain>
		</Setting>
	);
};

export default Settings;
