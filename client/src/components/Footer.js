import styled from "styled-components";
import imageSprites from "./../icons/sprites.svg";
import { dataFooterNav, dataFooterSocial } from "../data/dataFooterNav";
import { BREAK_POINT_MOBILE } from "./../data/breakpoints";

const FooterWrap = styled.footer`
	width: 100%;
	background: var(--footer-bg-color);

	.container {
		display: flex;
		flex-flow: row wrap;
		padding: 32px 12px 12px 12px;
	}

	.logoWrap {
		flex: 0 0 64px;
		margin-bottom: 32px;
	}
	.footerNavWrap {
		display: flex;
		flex: 2 1 auto;
		flex-wrap: wrap;
	}
	.copyrightWrap {
		flex: 1 1 150px;
		display: flex;
		flex-direction: column;

		p {
			margin-top: auto;
			margin-bottom: 24px;
			line-height: 14px;
			font-size: 11px;
			color: var(--footer-bg-on-color);

			a {
				font-size: inherit;
				text-decoration: underline;
				color: inherit;
			}
		}
	}

	@media screen and (max-width: ${BREAK_POINT_MOBILE}px) {
		.container {
			padding: 24px;
		}
		.logoWrap {
			display: none;
		}
		.footerNavWrap {
			flex-direction: column;
		}
		.copyrightWrap {
			margin-top: 24px;
		}
	}
`;

const Logo = styled.a`
	display: block;
	width: 32px;
	height: 37px;
	span {
		display: block;
		width: 100%;
		height: 100%;
		margin-bottom: 4px;
		background-image: url(${imageSprites});
		background-position: 0 -8px;
		font-size: 0;
		overflow: hidden;
	}
`;

const FooterLink = styled.a`
	display: inline-block;
	padding: 4px 0;
	color: var(--footer-bg-on-color);
`;

const FooterNavWrap = styled.div`
	flex: 1 0 auto;
	padding: 0 12px 24px 0;

	h5 {
		margin-bottom: 12px;
		line-height: var(--line-height-md);
		font-weight: var(--font-bold);
		> a {
			font-size: inherit;
			color: var(--lightgray);
		}
	}

	@media screen and (max-width: ${BREAK_POINT_MOBILE}px) {
		h5 {
			margin-bottom: 8px;
			font-size: 11px;
		}

		ul {
			display: flex;
			flex-wrap: wrap;
			column-gap: 12px;
			row-gap: 8px;
		}
		a {
			padding: 0;
			font-size: 11px;
		}
	}
`;

const FooterCopyrightWrap = styled.ul`
	display: flex;
	li {
		margin-left: 12px;

		> a {
			font-size: 11px;
		}
	}
	li:first-child {
		margin-left: 0;
	}

	@media screen and (max-width: ${BREAK_POINT_MOBILE}px) {
		margin-bottom: 8px;
	}
`;

const Footer = () => {
	return (
		<FooterWrap>
			<div className="container">
				<div className="logoWrap">
					<Logo>
						<span>Stack Overflow</span>
					</Logo>
				</div>
				<div className="footerNavWrap">
					{dataFooterNav.map((el, idx) => {
						return (
							<FooterNavWrap key={idx}>
								<h5>
									<FooterLink href={el.titleLink}>
										{el.title}
									</FooterLink>
								</h5>
								<ul>
									{el.list.map((childEl, childIdx) => {
										return (
											<li key={`${idx}_${childIdx}`}>
												<FooterLink href={childEl.link}>
													{childEl.linkTitle}
												</FooterLink>
											</li>
										);
									})}
								</ul>
							</FooterNavWrap>
						);
					})}
				</div>
				<div className="copyrightWrap">
					<FooterCopyrightWrap>
						{dataFooterSocial.map((el, idx) => {
							return (
								<li key={idx}>
									<FooterLink href={el.link}>
										{el.title}
									</FooterLink>
								</li>
							);
						})}
					</FooterCopyrightWrap>
					<p>
						Site design / logo &#169; 2022 Stack Exchange Inc; user
						contributions licensed under{" "}
						<span>
							<a href="https://stackoverflow.com/help/licensing">
								CC BY-SA
							</a>
						</span>
						. rev&nbsp;2022.12.21.43127
					</p>
				</div>
			</div>
		</FooterWrap>
	);
};

export default Footer;
