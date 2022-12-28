import styled from "styled-components";
import { useEffect } from "react";
import { Link } from "react-router-dom";
import imageSprites from "./../icons/sprites.svg";
import IconLinkLogin from "../icons/IconLinkLogin";
import LoginSignUpStyle from "../components/LoginSignUpStyle";

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
						<input type="text" name="email" />
					</LoginSignUpStyle.InputWrap>
					<LoginSignUpStyle.InputWrap>
						<div className="flexbox">
							<label htmlFor="password">Password</label>
							<a href="https://stackoverflow.com/users/account-recovery" className="link">
								Forgot password?
							</a>
						</div>
						<input type="text" name="password" />
					</LoginSignUpStyle.InputWrap>
					<button className="btnPrimary">Log in</button>
				</form>
			</LoginSignUpStyle.ContentBox>
			<LinkWrap>
				<li>
					Don’t have an account?{" "}
					<a href="/users/signup?ssrc=head" className="link">
						Sign up
					</a>
				</li>
				<li>
					Are you an employer?
					<a href="https://careers.stackoverflow.com/employer/login" className="link">
						Sign up on Talent
						<IconLinkLogin />
					</a>
				</li>
			</LinkWrap>
		</LoginSignUpStyle.Wrap>
	);
};
export default Login;
