import styled from "styled-components";
import { Link } from "react-router-dom";
import imageSprites from "./../icons/sprites.svg";
import LogoForTeamsMain from "../icons/LogoForTeamsMain";
import { BREAK_POINT_TABLET, BREAK_POINT_MOBILE } from "../data/breakpoints";

//styled components
const Section02 = styled.section`
	position: relative;
	max-width: 100rem;
	width: 100%;
	padding: 32px;
	margin: -128px auto 0 auto;
	z-index: 10;

	.contentWrap {
		display: flex;
		margin: 0 48px;
	}
	@media screen and (max-width: ${BREAK_POINT_MOBILE}px) {
		& {
			padding: 48px 12px 32px 12px;
			.contentWrap {
				flex-direction: column;
				margin: 0 auto;
			}
		}
	}
`;

const ContentBox = styled.div`
	flex-basis: 50%;
	padding: 16px;
	font-family: "Roboto Slab", serif;
	font-weight: var(--font-bold);

	.content {
		height: 100%;
		padding: 48px 0;
		border-radius: var(--border-radius-lg);
		background: ${(props) => {
			if (props.theme === "orange") return "linear-gradient(0deg, var(--lightorange) 30%, var(--white))";
			else if (props.theme === "blue") return "linear-gradient(0deg, var(--lightblue) 30%, var(--white))";
			else return "var(--dark)";
		}};
		box-shadow: 0 12px 11px rgb(0 0 0 / 4%), 0 100px 80px rgb(0 0 0 / 7%);

		.topLink {
			display: block;
			width: 187px;
			height: 37px;
			margin: 0 auto;

			&.left {
				background-image: url(${imageSprites});
				background-position: 0 -8px;
			}
		}
		img {
			width: 100%;
			margin: 32px 0;
		}
		h2 {
			margin: 0 48px 12px 48px;
			text-align: center;
			line-height: 1.3;
			font-family: "Roboto Slab", serif;
			font-weight: var(--font-bold);
			font-size: var(--font-head1-size);
		}
		p {
			margin: 0 48px 32px 48px;
			text-align: center;
			line-height: 1.3;
			font-family: "Source Sans Pro", sans-serif;
			font-size: 19px;
			font-weight: var(--font-normal);
			color: var(--darkgray);
		}
		.bottomLinkWrap {
			display: flex;
			justify-content: center;
			a {
				margin: 0;
			}
			a:last-child {
				margin-left: 8px;
			}
		}
		.bottomLink {
			display: block;
			max-width: calc((100rem / 12) * 2);
			padding: 12px;
			margin: 0 auto;
			border-radius: var(--border-radius-sm);
			background: ${(props) => {
				if (props.theme === "orange") return "var(--orange)";
				else if (props.theme === "blue") return "var(--blue)";
				else return "var(--dark)";
			}};
			text-align: center;
			font-family: "Source Sans Pro", sans-serif;
			font-size: 15px;
			color: var(--white);
		}
	}
`;

//MainSection02 components
const MainSection02 = () => {
	return (
		<Section02>
			<div className="contentWrap">
				<ContentBox theme={"orange"}>
					<div className="content">
						<Link to="/questions" className="topLink left" />
						<img src="https://cdn.sstatic.net/Img/home/illo-public.svg?v=14bd5a506009" alt="Illustration of Stack Overflow" />
						<h2>A public platform building the definitive collection of coding questions &amp; answers</h2>
						<p>A community-based space to find and contribute answers to technical challenges, and one of the most popular websites in the world.</p>
						<a href="/users/signup?ssrc=product_home" className="bottomLink">
							Join the community
						</a>
					</div>
				</ContentBox>
				<ContentBox theme={"blue"}>
					<div className="content">
						<a href="https://stackoverflow.co/teams" className="topLink">
							<LogoForTeamsMain />
						</a>
						<img src="https://cdn.sstatic.net/Img/home/illo-teams.svg?v=7e543f14fcc0" alt="Illustration of Stack Overflow for Teams" />
						<h2>A private collaboration &amp; knowledge sharing SaaS platform for companies</h2>
						<p>A web-based platform to increase productivity, decrease cycle times, accelerate time to market, and protect institutional knowledge.</p>
						<div className="bottomLinkWrap">
							<a href="https://stackoverflow.co/explore-teams" className="bottomLink">
								For large organizations
							</a>
							<a href="https://stackoverflow.co/teams" className="bottomLink">
								For small teams
							</a>
						</div>
					</div>
				</ContentBox>
			</div>
		</Section02>
	);
};
export default MainSection02;
