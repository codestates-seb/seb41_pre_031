import styled from "styled-components";
import imageSprites from "./../icons/sprites.svg";
import IconSearch from "./../icons/IconSearch";
import Nav from "./Nav";
import { BREAK_POINT_TABLET, BREAK_POINT_MOBILE } from "./../data/breakpoints";
import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import jwt_decode from "jwt-decode";

const HeaderWrap = styled.header`
	position: fixed;
	width: 100%;
	height: 50px;
	z-index: 100;
	border-top: 3px solid var(--orange);
	background: #f8f9f9;
	box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05), 0 2px 8px hsla(0, 0%, 0%, 0.05);
	.container {
		position: relative;
		display: flex;
		align-items: center;
		height: 100%;
	}
	/* 네비게이션 노출시 닫기 위한 배경입니다. 문제시 삭제 */
	.backDrop {
		position: fixed;
		left: 0;
		top: 0;
		width: 100%;
		height: 100%;
		z-index: 150;
	}
`;

const Logo = styled(Link)`
	padding: 0 8px;
	height: 100%;
	display: flex;
	align-items: center;
	span {
		display: block;
		width: 150px;
		height: 30px;
		margin-bottom: 4px;
		background-image: url(${imageSprites});
		background-position: 0 -500px;
		font-size: 0;
		overflow: hidden;
	}
	&:hover {
		background: var(--nav-link-hover-color);
	}
	@media screen and (max-width: ${BREAK_POINT_MOBILE}px) {
		span {
			width: 25px;
		}
	}
`;

const MenuBtn = styled.button`
	padding: 0 16px;
	height: 100%;
	span {
		position: relative;
		display: block;
		width: 16px;
		height: 2px;
		background: var(--darkgray);
	}
	span:before,
	span:after {
		content: "";
		position: absolute;
		left: 0;
		top: -5px;
		display: block;
		width: 16px;
		height: 2px;
		background: var(--darkgray);
		transition: top, transform;
		transition-duration: 0.1s;
		transition-timing-function: ease-in-out;
	}
	span:after {
		top: 5px;
	}
	&:hover {
		background: var(--nav-link-hover-color);
	}
	&.active {
		span {
			background: transparent;
		}
		span:before {
			top: 0;
			transform: rotate(45deg);
		}
		span:after {
			top: 0;
			transform: rotate(-45deg);
		}
	}
`;

const GnbWrap = styled.div`
	position: absolute;
	left: calc((100% - 1264px) / 2);
	top: 47px;
	width: 240px;
	padding: 24px 0 8px 0;
	background: var(--bg-color);
	box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05), 0 2px 8px hsla(0, 0%, 0%, 0.05);
	@media screen and (max-width: 1264px) {
		& {
			left: 0;
		}
	}
`;

const NavItems = styled.ul`
	display: flex;
	li {
		a {
			display: block;
			padding: 6px 12px;
			color: var(--nav-link-color);
		}
		a:hover {
			border-radius: 50px;
			background: var(--nav-link-hover-color);
		}
	}

	@media screen and (max-width: ${BREAK_POINT_TABLET}px) {
		display: none;
	}
`;

const SearchWrap = styled.div`
	position: relative;
	padding: 0 8px;
	flex-grow: 1;
	display: flex;
	> svg {
		position: absolute;
		top: 50%;
		left: 15px;
		transform: translateY(-50%);
	}
	input[type="text"] {
		width: 100%;
		padding-left: 32px;
	}
	.searchMobile {
		display: none;
		padding: 0 10px;
	}
	.searchMobile:hover {
		background: var(--nav-link-hover-color);
	}

	@media screen and (max-width: ${BREAK_POINT_MOBILE}px) {
		& {
			justify-content: flex-end;
			height: 100%;
			padding-right: 0;
			> svg {
				display: none;
			}
			input {
				display: none;
			}
			.searchMobile {
				display: block;
			}
		}
	}
`;

const ProfileWrap = styled.div`
	display: flex;
	align-items: center;
	padding: 0 12px;
	.avatarWrap {
		display: block;
		width: 24px;
		height: 24px;
		margin-right: 4px;
		border-radius: 3px;
		overflow: hidden;
		span {
			width: 100%;
			height: 100%;
			object-fit: cover;
			/* 이미지일 경우 삭제 */
			display: block;
			background: var(--lightgray);
			font-size: 0;
		}
	}
	.reputation {
		font-size: var(--font-caption-size);
		font-weight: var(--font-bold);
	}
	@media screen and (max-width: ${BREAK_POINT_MOBILE}px) {
		.reputation {
			display: none;
		}
	}
`;

const BtnsWrap = styled.div`
	display: flex;
	padding-right: 12px;
	text-align: center;
	.buttonLink {
		flex: none;
		display: block;
		margin-right: 4px;
	}
	a,
	button {
		padding: 8px 10px 8px 10px;
		line-height: 1;
	}
`;

const Header = ({ flag, isLogin, setIsLogin }) => {
	const [isMenuOn, setIsMenuOn] = useState(false);
	// const [token, setToken] = useState(null);
	const navigate = useNavigate();

	const handleClickLogout = () => {
		let logoutResult = window.confirm("로그아웃 하시겠습니까?");
		if (logoutResult) {
			localStorage.removeItem("loginToken");
			setIsLogin(false);
			navigate("/");
		}
	};

	const handleClickMenu = (e) => {
		setIsMenuOn(!isMenuOn);
	};

	let decoded;
	let loginToken = window.localStorage.getItem("loginToken");

	if (loginToken) {
		decoded = jwt_decode(loginToken);
	}
	return (
		<HeaderWrap>
			<div className="container">
				{/* 로그인 전, 왼쪽 사이드바가 있을 경우 MenuBtn 삭제해야 함 */}
				{flag ? null : (
					<MenuBtn onClick={(e) => handleClickMenu(e)} className={isMenuOn ? "active" : ""}>
						<span></span>
					</MenuBtn>
				)}
				{isMenuOn ? (
					<div className="backDrop" onClick={(e) => handleClickMenu(e)}>
						<GnbWrap>
							<Nav />
						</GnbWrap>
					</div>
				) : null}

				<Logo to="/">
					<span>Stack Overflow</span>
				</Logo>
				<NavItems>
					<li>
						<a href="https://stackoverflow.co/">About</a>
					</li>
					<li>
						<a href="https://stackoverflow.co/teams/">For Teams</a>
					</li>
				</NavItems>
				<SearchWrap>
					<IconSearch color={"#838c95"} />
					<input type="text" placeholder="Search..." />
					{/* mobile에서만 노출 */}
					<button className="searchMobile">
						<IconSearch color={"#838c95"} />
					</button>
				</SearchWrap>
				{isLogin && decoded !== undefined ? (
					<ProfileWrap>
						<Link to={`/users/profile/${decoded.memberId}`} className="avatarWrap">
							<span>사용자 프로필 대체</span>
						</Link>
						<div className="reputation">1</div>
					</ProfileWrap>
				) : null}
				{isLogin ? (
					<BtnsWrap>
						<button className="buttonLink btnSecondary" onClick={handleClickLogout}>
							Log out
						</button>
					</BtnsWrap>
				) : (
					<BtnsWrap>
						<Link to="/login" className="buttonLink btnSecondary">
							Log in
						</Link>
						<Link to="/signup" className="buttonLink btnPrimary">
							Sign up
						</Link>
					</BtnsWrap>
				)}
			</div>
		</HeaderWrap>
	);
};
export default Header;
