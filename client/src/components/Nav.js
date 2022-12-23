import styled from "styled-components";

const NavItem = styled.li`
	width: 100%;
	.navSubTitle {
		padding: 16px 0 4px 8px;
		font-size: 11px;
		color: var(--gray);
	}
	a {
		display: block;
		padding: 4px 4px 4px 8px;
		line-height: 2;
		color: var(--nav-link-color);
	}
	a:hover {
		color: var(--black);
	}
	&.selected {
		a {
			background: var(--white2);
			font-weight: var(--font-bold);
			border-right: 3px solid var(--secondary-color);
			color: var(--black);
		}
	}
	.public {
		li {
			> a {
				padding: 4px 4px 4px 30px;
			}
		}
	}
`;

const Nav = () => {
	return (
		<nav>
			<ul>
				<NavItem>
					<a href="none">Home</a>
				</NavItem>
				<NavItem>
					<div className="navSubTitle">PUBLIC</div>
					<ul className="public">
						<NavItem className="selected">
							<a href="none">Questions</a>
						</NavItem>
						<NavItem>
							<a href="none">Users</a>
						</NavItem>
					</ul>
				</NavItem>
			</ul>
		</nav>
	);
};

export default Nav;
