import styled from "styled-components";
import { NavLink } from "react-router-dom";

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
	a.selected {
		background: var(--white2);
		font-weight: var(--font-bold);
		border-right: 3px solid var(--secondary-color);
		color: var(--black);
	}
	.public {
		li {
			> a {
				padding: 4px 4px 4px 30px;
			}
		}
	}
`;

const Nav = ({ handleClickMenu }) => {
	return (
		<nav>
			<ul>
				<NavItem>
					<NavLink to="/" className={({ isActive }) => (isActive ? "selected" : "")}>
						Home
					</NavLink>
				</NavItem>
				<NavItem>
					<div className="navSubTitle">PUBLIC</div>
					<ul className="public">
						<NavItem>
							<NavLink to="/questions" className={({ isActive }) => (isActive ? "selected" : "")}>
								Questions
							</NavLink>
						</NavItem>
						<NavItem>
							<NavLink to="/Users" className={({ isActive }) => (isActive ? "selected" : "")}>
								Users
							</NavLink>
						</NavItem>
					</ul>
				</NavItem>
			</ul>
		</nav>
	);
};

export default Nav;
