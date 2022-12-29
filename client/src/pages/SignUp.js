import styled from "styled-components";
import { useState, useEffect, useRef } from "react";
import IconChatSignUp from "./../icons/IconChatSignUp";
import IconArrowSignUp from "./../icons/IconArrowSignUp";
import IconTagSignUp from "./../icons/IconTagSignUp";
import IconTrophySignUp from "./../icons/IconTrophySignUp";
import LoginSignUpStyle from "../components/LoginSignUpStyle";
import IconQuestionMarkSignUp from "../icons/IconQuestionMarkSignUp";
import LoginSignupDesc from "../components/LoginSignupDesc";
import { BREAK_POINT_TABLET } from "../data/breakpoints";

const Wrap = styled(LoginSignUpStyle.Wrap)`
	flex-direction: row;

	.rightContent {
		display: flex;
		flex-direction: column;
		align-items: center;
		max-width: calc((100rem / 12) * 4);
	}
	.caption {
		margin: 4px 0;
	}
`;

const LeftContent = styled.div`
	max-width: calc((100rem / 12) * 4);
	margin: 0 48px 128px 0;

	h2 {
		margin-bottom: 32px;
		font-size: var(--font-head1-size);
		color: var(--dark);
	}
	> div {
		line-height: var(--line-height-sm);
		color: var(--gray);
	}

	@media screen and (max-width: ${BREAK_POINT_TABLET}px) {
		& {
			display: none;
		}
	}
`;

const ListItem = styled.li`
	display: flex;
	align-items: center;
	margin-bottom: 24px;

	svg {
		margin-right: 8px;
		color: var(--blue);
	}
	span {
		font-size: var(--font-label-size);
		color: var(--dark);
	}
`;

const TabletTitle = styled.p`
	display: none;
	margin-bottom: 24px;
	line-height: var(--line-height-md);
	text-align: center;
	font-size: var(--font-title-size);
	color: var(--dark);

	@media screen and (max-width: ${BREAK_POINT_TABLET}px) {
		& {
			display: block;
		}
	}
`;

const CheckBoxWrap = styled.div`
	display: flex;
	align-items: flex-start;
	margin: 6px 0 12px 0;

	input[type="checkbox"] {
		flex: none;
		margin: 2px 4px 0 0;
		appearance: none;
		width: 12px;
		height: 12px;
	}
	input[type="checkbox"]:checked {
		appearance: auto;
	}
	label {
		line-height: var(--line-height-md);
		font-size: var(--font-caption-size);
		font-weight: var(--font-normal);
		color: var(--black);
	}
	.tooltip {
		position: relative;

		.tooltipBtn {
			width: auto;
			padding: 0;
			flex: none;
		}
		.popover {
			position: absolute;
			left: -222px;
			top: 7px;
			transform: translateY(-50%);
			width: 210px;
			padding: 12px;
			margin: 0px;
			line-height: var(--line-height-md);
			border-radius: var(--border-radius-md);
			background: var(--white);
			border: 1px solid #d6d9dc;
			box-shadow: 2px -2px 4px rgba(0, 0, 0, 0.07);
			color: var(--black);

			&:after {
				content: "";
				position: absolute;
				right: -7px;
				top: calc(50% - 4px);
				transform: rotate(45deg);
				display: block;
				width: 12px;
				height: 12px;
				border-right: 1px solid #d6d9dc;
				border-top: 1px solid #d6d9dc;
				background: var(--white);
				box-shadow: 2px -2px 2px rgba(0, 0, 0, 0.07);
			}

			p:nth-child(1) {
				margin-bottom: 1rem;
			}
		}
	}
`;

const Desc = styled.div`
	margin-top: 32px;
	line-height: var(--line-height-sm);
	font-size: var(--font-caption-size);
	color: var(--gray);

	.link {
		font-size: var(--font-caption-size);
	}
`;

const SignUp = ({ setFlag, setIsFooter }) => {
	useEffect(() => {
		setFlag(false);
		setIsFooter(false);
		//툴팁 핸들러
		window.addEventListener("click", handlerClickTooltipOutside);
		return () => {
			window.removeEventListener("click", handlerClickTooltipOutside);
		};
	}, []);
	const [isTooltip, setIsTooltip] = useState(false);
	const tooltipBtn = useRef(null);
	//툴팁 외부 클릭시 닫힘
	const handlerClickTooltipOutside = (e) => {
		if (isTooltip || !tooltipBtn.current.contains(e.target)) {
			setIsTooltip(false);
		}
	};
	const handlerToggleTooltip = (e) => {
		e.preventDefault();
		setIsTooltip(!isTooltip);
	};

	return (
		<Wrap>
			<LeftContent>
				<h2>Join the Stack Overflow community</h2>
				<ul>
					<ListItem>
						<IconChatSignUp />
						<span>Get unstuck — ask a question</span>
					</ListItem>
					<ListItem>
						<IconArrowSignUp />
						<span>Unlock new privileges like voting and commenting</span>
					</ListItem>
					<ListItem>
						<IconTagSignUp />
						<span>Save your favorite tags, filters, and jobs</span>
					</ListItem>
					<ListItem>
						<IconTrophySignUp />
						<span>Earn reputation and badges</span>
					</ListItem>
				</ul>
				<div>
					<p>Collaborate and share knowledge with a private group for FREE.</p>
					<a href="https://stackoverflow.com/teams?utm_source=so-owned&utm_medium=product&utm_campaign=free-50&utm_content=public-sign-up" className="link">
						Get Stack Overflow for Teams free for up to 50 users
					</a>
				</div>
			</LeftContent>
			<div className="rightContent">
				<TabletTitle>Create your Stack Overflow account. It’s free and only takes a minute.</TabletTitle>
				<LoginSignUpStyle.ContentBox>
					<form>
						<LoginSignUpStyle.InputWrap>
							<label htmlFor="displayName">Display name</label>
							<input type="text" id="displayName" />
						</LoginSignUpStyle.InputWrap>
						<LoginSignUpStyle.InputWrap>
							<label htmlFor="email">Email</label>
							<input type="text" id="email" />
						</LoginSignUpStyle.InputWrap>
						<LoginSignUpStyle.InputWrap>
							<label htmlFor="password">Password</label>
							<input type="text" id="password" />
							<p className="caption">Passwords must contain at least eight characters, including at least 1 letter and 1 number.</p>
						</LoginSignUpStyle.InputWrap>
						<CheckBoxWrap>
							<input type="checkbox" id="check" />
							<label htmlFor="check">Opt-in to receive occasional product updates, user research invitations, company announcements, and digests.</label>
							<div className="tooltip">
								<button onClick={(e) => handlerToggleTooltip(e)} ref={tooltipBtn} className="tooltipBtn">
									<IconQuestionMarkSignUp color={"var(--gray)"} />
								</button>
								{isTooltip ? (
									<div className="popover">
										<p>We know you hate spam, and we do too. That’s why we make it easy for you to update your email preferences or unsubscribe at anytime.</p>
										<p>We never share your email address with third parties for marketing purposes.</p>
									</div>
								) : null}
							</div>
						</CheckBoxWrap>
						<button className="btnPrimary">Sign up</button>
					</form>
					<Desc>
						By clicking “Sign up”, you agree to our&nbsp;
						<a href="https://stackoverflow.com/legal/terms-of-service/public" target="_blank" className="link">
							terms of service
						</a>
						,
						<a href="https://stackoverflow.com/legal/privacy-policy" target="_blank" className="link">
							privacy policy
						</a>
						&nbsp;and&nbsp;
						<a href="https://stackoverflow.com/legal/cookie-policy" target="_blank" className="link">
							cookie policy
						</a>
					</Desc>
				</LoginSignUpStyle.ContentBox>
				<LoginSignupDesc desc1={"Already have an account?"} button1name={"Log in"} linkTo={"/login"} />
			</div>
		</Wrap>
	);
};
export default SignUp;
