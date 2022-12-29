import styled from "styled-components";
import IconSearchMain from "../icons/IconSearchMain";
import IconLockMain from "../icons/IconLockMain";
import { BREAK_POINT_TABLET, BREAK_POINT_MOBILE } from "../data/breakpoints";
import { Link } from "react-router-dom";

//styled Components
const Section01 = styled.section`
	max-width: 1950px;
	width: 100%;
	padding: 48px;
	margin: 0 auto;

	.contentsWrap {
		padding: 32px 32px 128px 32px;
		margin-bottom: -128px;
		border-radius: 7px;
		background: linear-gradient(180deg, var(--dark) 0%, var(--darkgray) 130%);
		clip-path: url(#curve);

		.h2Contents {
			display: flex;
			max-width: calc((100rem / 12) * 9);
			margin: 0 auto;
		}
	}
	@media screen and (max-width: ${BREAK_POINT_MOBILE}px) {
		& {
			padding: 12px 12px 128px 12px;

			.contentsWrap {
				padding: 12px;
			}

			.h2Contents {
				flex-direction: column;
			}
		}
	}
`;

const H2SingleContent = styled.div`
	flex-basis: 50%;
	padding: 16px;
	font-family: "Source Sans Pro", sans-serif;

	> div {
		position: relative;
		padding: 24px;
		height: 100%;
		border-radius: var(--border-radius-lg);
		background: ${(props) => {
			if (props.theme === "orange") return "var(--lightorange)";
			else if (props.theme === "blue") return "var(--lightblue)";
			else return "var(--white)";
		}};
		text-align: center;

		svg {
			margin: 0 0 16px 0;
		}

		h2 {
			max-width: calc((100rem / 12) * 3);
			margin: 0 auto 13px auto;
			line-height: 1.3;
			font-size: 19px;
			font-weight: var(--font-normal);
			color: var(--dark);
		}

		a {
			display: block;
			max-width: calc((100rem / 12) * 2);
			width: 100%;
			padding: 12px 32px;
			margin: 0 auto 28px auto;
			border-radius: var(--border-radius-sm);
			background: ${(props) => {
				if (props.theme === "orange") return "var(--orange)";
				else if (props.theme === "blue") return "var(--blue)";
				else return "var(--dark)";
			}};
			font-weight: var(--font-bold);
			color: var(--white);
		}

		&:after {
			clip-path: polygon(18px 0, 32px 0, 0 40px, 0 38px, 0 0, 18px 0);
			background: ${(props) => {
				if (props.theme === "orange") return "var(--lightorange)";
				else if (props.theme === "blue") return "var(--lightblue)";
				else return "var(--white)";
			}};
			width: 32px;
			height: 32px;
			position: absolute;
			bottom: -30px;
			left: 0;
			display: block;
			content: "";
			border-radius: 0 5px 0;
		}
	}

	&:nth-child(1) {
		> div {
			border-bottom-right-radius: 0;
			&:after {
				transform: scaleX(-1);
				right: 0;
				left: auto;
			}
		}
	}
	&:nth-child(2) {
		> div {
			border-bottom-left-radius: 0;
		}
	}
`;

const H1 = styled.h1`
	padding: 64px 0;
	text-align: center;
	line-height: 1.3;
	font-family: "Roboto Slab", serif;
	font-size: 55px;
	font-weight: var(--font-bold);
	color: var(--white3);

	span {
		color: var(--orange);
	}

	@media screen and (max-width: ${BREAK_POINT_MOBILE}px) {
		& {
			font-size: 3rem;
		}
	}
`;

const GrayBar = styled.div`
	width: 64px;
	height: 8px;
	margin: 0 auto;
	background: var(--gray);
	border-radius: 8px;
`;

const DescList = styled.div`
	display: flex;
	max-width: calc((100rem / 12) * 9);
	padding: 32px 12px 64px 12px;
	margin: 0 auto;
	text-align: center;

	dl {
		flex-bsais: 25%;
		padding: 32px 0;

		dt {
			margin-bottom: 4px;
			font-family: "Roboto Slab", serif;
			font-weight: var(--font-bold);
			font-size: var(--font-title-size);
			color: var(--white3);
		}
		dd {
			padding: 0 24px;
			font-size: var(--font-label-size);
			color: var(--darkgray3);
		}
	}
	@media screen and (max-width: ${BREAK_POINT_TABLET}px) {
		& {
			flex-direction: column;
		}
	}
	@media screen and (max-width: ${BREAK_POINT_MOBILE}px) {
		& {
			flex-direction: column;
			padding: 32px 12px 128px 12px;
		}
	}
`;

//MainBeforeLogin Component
const MainSection01 = () => {
	const section01DescList = [
		{
			title: "100+ million",
			desc: "monthly visitors to Stack Overflow &amp; Stack Exchange",
		},
		{
			title: "45.1+ billion",
			desc: "Times a developer got help since 2008",
		},
		{
			title: "191% ROI",
			desc: "from companies using Stack Overflow for Teams",
		},
		{
			title: "5,000+",
			desc: "Stack Overflow for Teams instances active every day",
		},
	];

	return (
		<Section01>
			<div className="contentsWrap">
				<div className="h2Contents">
					<H2SingleContent theme={"orange"}>
						<div>
							<IconSearchMain color={"var(--orange2)"} />
							<h2>Find the best answer to your technical question, help others answer theirs</h2>
							<Link to="/signup">Join the community</Link>
						</div>
					</H2SingleContent>
					<H2SingleContent theme={"blue"}>
						<div>
							<IconLockMain color={"var(--blue)"} />
							<h2>Want a secure, private space for your technical knowledge?</h2>
							<a href="https://stackoverflow.co/teams/">Discover Teams</a>
						</div>
					</H2SingleContent>
				</div>
				<H1>
					Every <span>developer</span>
					<br className="onlyViewMobile" /> has a <br className="onlyViewPC" />
					tab open to <br className="onlyViewMobile" />
					Stack Overflow
				</H1>
				<GrayBar />
				<DescList>
					{section01DescList.map((el, idx) => {
						return (
							<dl key={idx}>
								<dt>{el.title}</dt>
								<dd>{el.desc}</dd>
							</dl>
						);
					})}
				</DescList>
			</div>
			<svg width="0" height="0">
				<defs>
					<clipPath id="curve" clipPathUnits="objectBoundingBox">
						<path d="M0,0 H1 V0.988 a0.007,0.012,0,0,1,-0.009,0.011 C0.955,0.983,0.802,0.925,0.501,0.925 C0.2,0.925,0.045,0.984,0.009,1 A0.007,0.012,0,0,1,0,0.988" />
					</clipPath>
				</defs>
			</svg>
		</Section01>
	);
};

export default MainSection01;
