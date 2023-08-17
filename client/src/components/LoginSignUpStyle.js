import styled from "styled-components";

const LoginSignUpStyle = {};

LoginSignUpStyle.Wrap = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	width: 100%;
	height: 100%;
	padding: 24px 16px;
	background: var(--white2);
`;

LoginSignUpStyle.ContentBox = styled.div`
	max-width: calc((100rem / 12) * 3);
	width: 100%;
	padding: 24px;
	margin-bottom: 24px;
	border-radius: var(--border-radius-lg);
	background: var(--white);
	box-shadow: 0 10px 24px hsla(0, 0%, 0%, 0.05), 0 20px 48px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.1);

	form {
		display: flex;
		flex-direction: column;
	}

	button {
		width: 100%;
		margin: 2px 0;
	}
`;

LoginSignUpStyle.InputWrap = styled.div`
	width: 100%;
	margin: 6px 0 12px 0;

	.flexbox {
		display: flex;
		align-items: center;
		justify-content: space-between;
	}

	label {
		display: block;
		margin: 2px 0 4px 0;
	}
	input {
		width: 100%;
	}
`;

LoginSignUpStyle.LinkWrap = styled.ul`
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

export default LoginSignUpStyle;
