import styled from "styled-components";
import imageSprites from "./../icons/sprites.svg";
import LogoAdvertisingMain from "../icons/LogoAdvertisingMain";
import LogoCollectivesMain from "../icons/LogoCollectivesMain";
import LogoTalentMain from "../icons/LogoTalentMain";
import { BREAK_POINT_TABLET, BREAK_POINT_MOBILE } from "../data/breakpoints";

const Section05 = styled.section`
	padding-bottom: 128px;
	background-image: url(https://cdn.sstatic.net/Img/home/illo-about-left.png?v=586391c9162b), url(https://cdn.sstatic.net/Img/home/illo-about-right.png?v=209eaa986298);
	background-repeat: no-repeat, no-repeat;
	background-position: 0 0, 100% 0;
	background-size: 27% auto;

	h2 {
		max-width: calc((100rem / 12) * 6);
		padding: 24px 24px 0 24px;
		margin: 0 auto;
		line-height: 1.3;
		text-align: center;
		font-family: "Roboto Slab", serif;
		font-size: var(--font-head1-size);
		font-weight: var(--font-normal);
	}
	.linkWrap {
		display: flex;
		flex-flow: row wrap;
		justify-content: center;
		max-width: calc((100rem / 12) * 8);
		margin: 32px auto;

		a {
			display: block;
			flex-basis: calc(50% - 32px);
			min-width: calc((100rem / 12) * 3);
			padding: 32px 24px;
			margin: 16px;
			border-radius: var(--border-radius-lg);
			background: var(--white);
			box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05), 0 2px 8px hsla(0, 0%, 0%, 0.05);
			text-align: center;

			svg {
				margin-bottom: 24px;
			}
			&:hover {
				box-shadow: 0 1px 3px hsla(0, 0%, 0%, 0.06), 0 2px 6px hsla(0, 0%, 0%, 0.06), 0 3px 8px hsla(0, 0%, 0%, 0.09);
				p {
					color: #2f3337;
				}
			}
		}
		p {
			line-height: var(--line-height-md);
			font-family: "Source Sans Pro", sans-serif;
			font-size: 19px;
			color: var(--gray);
		}
	}
	.bottomLink {
		display: block;
		max-width: calc((100rem / 12) * 2);
		padding: 12px 32px;
		margin: 0 auto 2px auto;
		border-radius: var(--border-radius-sm);
		background: var(--orange);
		text-align: center;
		font-family: "Source Sans Pro", sans-serif;
		font-size: 15px;
		font-weigth: var(--font-bold);
		color: var(--white);
	}
	.lineLink {
		display: block;
		max-width: calc((100rem / 12) * 3);
		padding: 12px 32px;
		margin: 0 auto;

		.link {
			color: var(--blue);
		}
	}
	@media screen and (max-width: ${BREAK_POINT_MOBILE}px) {
		& {
			background: none;
		}
	}
`;

const Logo = styled.div`
	width: 187px;
	height: 37px;
	padding-bottom: 32px;
	margin: 0 auto 12px auto;
	border-bottom: 1px solid var(--lightgray2);
	background-image: url(${imageSprites});
	background-position: 0 -8px;
	box-sizing: content-box;
`;

const MainSection05 = () => {
	return (
		<Section05>
			<Logo />
			<h2>Additional products that reach and engage developers &amp; technologists…</h2>
			<div className="linkWrap">
				<a href="https://stackoverflow.co/advertising">
					<LogoAdvertisingMain />
					<p>Reach the world’s largest audience of developers and technologists</p>
				</a>
				<a href="https://stackoverflow.co/collectives">
					<LogoCollectivesMain />
					<p>Connecting communities with the specific technologies they use the most</p>
				</a>
				<a href="https://stackoverflow.co/talent">
					<LogoTalentMain />
					<p className="fs-subheading mb0 wmx3 mx-auto">Build your employer brand</p>
				</a>
			</div>
			<a href="https://stackoverflow.co/" className="bottomLink">
				About the company
			</a>
			<a href="https://stackoverflow.co/company/work-here" className="lineLink">
				Want to work here? <span className="link">Current job openings</span>
			</a>
		</Section05>
	);
};
export default MainSection05;
