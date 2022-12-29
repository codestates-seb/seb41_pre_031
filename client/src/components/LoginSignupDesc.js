import { Link } from "react-router-dom";
import IconLinkLogin from "../icons/IconLinkLogin";
import LoginSignUpStyle from "../components/LoginSignUpStyle";

const LoginSignupDesc = ({ desc1, button1name }) => {
	return (
		<LoginSignUpStyle.LinkWrap>
			<li>
				{desc1}
				<Link to="/signup" className="link">
					{button1name}
				</Link>
			</li>
			<li>
				Are you an employer?
				<a href="https://careers.stackoverflow.com/employer/login" className="link">
					Sign up on Talent
					<IconLinkLogin />
				</a>
			</li>
		</LoginSignUpStyle.LinkWrap>
	);
};
export default LoginSignupDesc;
