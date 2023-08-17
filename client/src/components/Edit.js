import React, { useState } from "react";
import { useNavigate } from "react-router";
import styled from "styled-components";
import TextEdit from "./TextEdit";
import IconGithub from "../icons/IconGithub";
import axios from "axios";
import { BREAK_POINT_MOBILE } from "./../data/breakpoints";
import { ROOT_URL } from "./../data/rootUrl";

const MainHeader = styled.div`
	display: flex;
	width: 100%;
	padding-bottom: 16px;
	margin-bottom: 24px;
	align-items: flex-end;
	flex-direction: row;
	border-bottom: 1px solid rgb(214 217 220);
	justify-content: space-between;
	h1 {
		color: var(--black);
		font-size: 27px;
	}
`;
const MainBody = styled.form`
	.subTitle {
		font-size: 21px;
		margin-bottom: 8px;
	}
	.subBox {
		margin-bottom: 48px;
		padding: 24px;
		border-radius: 5px;
		border: 1px solid var(--lightgray2);
	}
	.about {
		height: 547px;
	}
	.fromBox {
		display: flex;
		flex-direction: column;
		margin: -6px 0;
	}
	.inputBox {
		display: flex;
		align-items: flex-end;
		flex-wrap: wrap;
		margin: -8px;
	}
	.fromItem {
		margin: 4px 0;
	}
	label {
		display: block;
	}
	input[type="text"] {
		display: block;
		width: 100%;
		max-width: 421px;
		padding: 7.8px;
		border: 1px solid rgb(186 191 196);
		margin: 8px 0;
	}
	.textarea {
		position: relative;
		margin: 7px 0 0;
		height: 200px;
		max-width: 794px;
		width: 100%;
	}
	.linkBox {
		display: flex;
		flex-direction: column;
		margin: -2px 0;
	}
	.linkInputBox {
		display: flex;
		position: relative;
		margin: 2px 0;
	}
	.linkInputBox input {
		padding: 7.8px 9.1px 7.8px 32px;
	}
	.linkInputBox svg {
		position: absolute;
		top: 15px;
		left: 9px;
	}
	@media screen and (max-width: ${BREAK_POINT_MOBILE}px) {
		.subBox {
			padding: 12px;
		}
		input[type="text"] {
			width: 100%;
			max-width: none;
		}
	}
`;

const BtnBox = styled.div`
	padding: 10px 0 15px 0;
	.btnBox {
		display: flex;
		flex-wrap: wrap;
		margin: -4px;
	}
	button {
		cursor: pointer;
	}
	.link {
		cursor: pointer;
		padding: 10.4px;
		margin: 4px 4px 4px 6px;
	}
	.link:hover {
		color: var(--link-color);
	}
`;

const Edit = ({ userData, id }) => {
	const navigate = useNavigate();
	const [content, setContent] = useState();
	const [form, setForm] = useState({
		profileTitle: userData.profileTitle,
		homepage: userData.homepage,
		location: userData.location,
		about: content,
	});

	const profileUpdate = () => {
		const config = {
			method: "patch",
			url: `${ROOT_URL}/profiles/${id}`,
			headers: {
				Authorization: localStorage.getItem("loginToken"),
			},
			data: form,
		};
		axios(config)
			.then((res) => {
				console.log("성공한듯");
				window.location.replace("/users/profile/8");
			})
			.catch((err) => {
				console.log(err);
			});
	};
	return (
		<>
			<MainHeader>
				<h1>Edit your profile</h1>
			</MainHeader>
			<MainBody>
				<div className="subTitle">Public information</div>
				<div className="subBox about">
					<div className="fromBox">
						<div className="fromItem">
							<label htmlFor="name">Display name</label>
							<input type="text" id="name" maxLength="30" tabIndex="1" />
						</div>
						<div className="fromItem">
							<label htmlFor="location">Location</label>
							<input type="text" value={form.location || ""} onChange={(e) => setForm({ ...form, location: e.target.value })} id="location" maxLength="100" tabIndex="3" />
						</div>
						<div className="fromItem">
							<label htmlFor="title">Title</label>
							<input
								type="text"
								value={form.profileTitle || ""}
								onChange={(e) => setForm({ ...form, profileTitle: e.target.value })}
								id="title"
								maxLength="225"
								tabIndex="3"
								placeholder="No title has been set"
							/>
						</div>
						<div className="fromItem">
							<label htmlFor="about">About me</label>
							<div className="textarea">
								<TextEdit setContent={setContent} />
							</div>
						</div>
					</div>
				</div>
				<div className="subTitle">GitHub Link</div>
				<div className="subBox">
					<div className="linkBox">
						<label>GitHub link</label>
						<div className="linkInputBox">
							<IconGithub />
							<input type="text" value={form.homepage || ""} onChange={(e) => setForm({ ...form, homepage: e.target.value })} maxLength="200" />
						</div>
					</div>
				</div>
			</MainBody>
			<BtnBox>
				<div className="btnBox">
					<button onClick={profileUpdate} className="btnPrimary">
						Save profile
					</button>
					<a className="link">Cancel</a>
				</div>
			</BtnBox>
		</>
	);
};

export default Edit;
