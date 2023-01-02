import styled from "styled-components";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import imageSprites from "./../icons/sprites.svg";
import LoginSignUpStyle from "../components/LoginSignUpStyle";
import LoginSignupDesc from "../components/LoginSignupDesc";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const LogoWrap = styled.div`
	margin-bottom: 24px;
	a {
		display: block;
		width: 32px;
		height: 37px;
		background-image: url(${imageSprites});
		background-position: 0 -8px;
	}
`;

const Login = ({ setFlag, setIsFooter, setIsLogin, setUserInfo }) => {
	const [loginInfo, setLoginInfo] = useState({
		username: "",
		password: "",
	});
	const [errorEmailMsg, setErrorEmailMsg] = useState("");
	const [errorPWMsg, setErrorPWMsg] = useState("");
	const navigate = useNavigate();
	useEffect(() => {
		setFlag(false);
		setIsFooter(false);
	}, []);
	const handleInputValue = (key, e) => {
		setLoginInfo({ ...loginInfo, [key]: e.target.value });
	};
	const handleLoginRequest = () => {
		const { username, password } = loginInfo;
		if (!username) {
			setErrorEmailMsg("Email cannot be empty.");
			return;
		} else {
			setErrorEmailMsg("");
		}
		if (!password) {
			setErrorPWMsg("Password cannot be empty.");
			return;
		} else {
			setErrorPWMsg("");
		}
		return axios
			.put("http://34.64.176.88:8080/auth/login", { username, password })
			.then((res) => {
				localStorage.setItem("loginToken", res.headers.authorization);
				setIsLogin(true);
				setUserInfo({ email: username });
				navigate("/");
			})
			.catch((err) => {
				if (err.response.status === 401) {
					alert("로그인에 실패하였습니다.");
				}
			});
	};
	return (
		<LoginSignUpStyle.Wrap>
			<LogoWrap>
				<Link to="/" />
			</LogoWrap>
			<LoginSignUpStyle.ContentBox>
				<form onSubmit={(e) => e.preventDefault()}>
					<LoginSignUpStyle.InputWrap>
						<label htmlFor="username">Email</label>
						<input type="text" id="username" className={errorEmailMsg === "" ? "" : "errorInput"} onChange={(e) => handleInputValue("username", e)} />
						<p className="errorMessage">{errorEmailMsg}</p>
					</LoginSignUpStyle.InputWrap>
					<LoginSignUpStyle.InputWrap>
						<div className="flexbox">
							<label htmlFor="password">Password</label>
							<a href="https://stackoverflow.com/users/account-recovery" className="link">
								Forgot password?
							</a>
						</div>
						<input type="password" id="password" onChange={(e) => handleInputValue("password", e)} className={errorPWMsg === "" ? "initialInput" : "initialInput errorInput"} />
						<p className="errorMessage">{errorPWMsg}</p>
					</LoginSignUpStyle.InputWrap>
					<button className="btnPrimary" onClick={handleLoginRequest}>
						Log in
					</button>
				</form>
			</LoginSignUpStyle.ContentBox>
			<LoginSignupDesc desc1={"Don’t have an account?"} button1name={"Sign up"} linkTo={"/signup"} />
		</LoginSignUpStyle.Wrap>
	);
};
export default Login;
