import styled from "styled-components";
import imageSprites from "./../icons/sprites.svg";
import IconSearch from "./../icons/IconSearch";

const HeaderWrap = styled.header`
	position: fixed;
	width: 100%;
	height: 50px;
	border-top: 3px solid var(--orange);
	background: #f8f9f9;
	box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05),
		0 2px 8px hsla(0, 0%, 0%, 0.05);
	.container {
		display: flex;
		align-items: center;
		height: 100%;
	}
`;

const Logo = styled.a`
	display: block;
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
	}
	span:after {
		top: 5px;
	}
	&:hover {
		background: var(--nav-link-hover-color);
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

	/* tablet에서 사라짐 */
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
	input {
		width: 100%;
		padding-left: 32px;
	}
	.searchMobile {
		padding: 0 10px;
	}
	.searchMobile:hover {
		background: var(--nav-link-hover-color);
	}
`;

const ProfileWrap = styled.div`
	display: flex;
	align-items: center;
	padding: 0 12px;
	.avatarWrap {
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
`;

const BtnsWrap = styled.div`
	padding-right: 12px;
	button {
		margin-right: 4px;
	}
`;

const Header = () => {
	return (
		<HeaderWrap>
			<div className="container">
				{/* 로그인 전, 왼쪽 사이드바가 있을 경우 MenuBtn 삭제해야 함 */}
				<MenuBtn>
					<span></span>
				</MenuBtn>
				<Logo>
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
				{/* 로그인 후에만 프로필 버튼 노출 */}
				<ProfileWrap>
					<div className="avatarWrap">
						<span>사용자 프로필 대체</span>
					</div>
					<div className="reputation">1</div>
				</ProfileWrap>
				<BtnsWrap>
					<button className="btnSecondary">Log in</button>
					<button className="btnPrimary">Sign up</button>
					{/* 로그인 시만 노출 */}
					<button className="btnSecondary">Log out</button>
				</BtnsWrap>
			</div>
		</HeaderWrap>
	);
};
export default Header;
