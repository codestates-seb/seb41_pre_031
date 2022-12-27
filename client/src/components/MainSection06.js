import styled from "styled-components";
import IconLockMain from "../icons/IconLockMain";
import { BREAK_POINT_TABLET, BREAK_POINT_MOBILE } from "../data/breakpoints";

const Section06 = styled.section`
	display: flex;
	align-items: center;
	justify-content: center;
	padding: 32px 0;
	margin: 32px 0;
	font-size: var(--font-head3-size);

	p {
		margin: 16px 24px;

		strong {
			font-weight: var(--font-bold);
		}
	}
	a {
		padding: 12px 32px;
		border: 1px solid #379fef;
		border-radius: var(--border-radius-md);
		line-height: 1.3;
		font-weight: var(--font-bold);
		color: var(--darkblue);
	}
	@media screen and (max-width: ${BREAK_POINT_MOBILE}px) {
		& {
			flex-direction: column;
		}
	}
`;

const MainSection06 = () => {
	return (
		<Section06>
			<IconLockMain color={"var(--dark)"} />
			<p>
				Build a <strong>private community</strong> to share technical or non-technical knowledge.
			</p>
			<a href="https://stackoverflowteams.com/teams/create/free">Create a free Team</a>
		</Section06>
	);
};
export default MainSection06;
