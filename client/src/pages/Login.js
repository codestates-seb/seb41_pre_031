import styled from "styled-components";
import { useEffect } from "react";
import { Link } from "react-router-dom";
import imageSprites from "./../icons/sprites.svg";
import LoginSignUpStyle from "../components/LoginSignUpStyle";
import LoginSignupDesc from "../components/LoginSignupDesc";

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

const LinkWrap = styled.ul`
	max-width: calc((100rem / 12) * 3);
	width: 100%;
	padding: 16px;
	margin-bottom: 24px;

	li {
		margin-bottom: 16px;
		text-align: center;
		a {
			margin-left: 4px;

			svg {
				margin-left: 4px;
				vertical-align: text-bottom;
				color: inherit;
			}
		}
	}
`;

const Login = ({ setFlag, setIsFooter }) => {
	useEffect(() => {
		setFlag(false);
		setIsFooter(false);
	}, []);
	return (
		<LoginSignUpStyle.Wrap>
			<LogoWrap>
				<Link to="/" />
			</LogoWrap>
			<LoginSignUpStyle.ContentBox>
				<form>
					<LoginSignUpStyle.InputWrap>
						<label htmlFor="email">Email</label>
						<input type="text" id="email" />
					</LoginSignUpStyle.InputWrap>
					<LoginSignUpStyle.InputWrap>
						<div className="flexbox">
							<label htmlFor="password">Password</label>
							<a href="https://stackoverflow.com/users/account-recovery" className="link">
								Forgot password?
							</a>
						</div>
						<input type="text" id="password" />
					</LoginSignUpStyle.InputWrap>
					<button className="btnPrimary">Log in</button>
				</form>
			</LoginSignUpStyle.ContentBox>
			<LoginSignupDesc desc1={"Don’t have an account?"} button1name={"Sign up"} />
		</LoginSignUpStyle.Wrap>
	);
};
export default Login;
