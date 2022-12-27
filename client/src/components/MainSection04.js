import styled from "styled-components";
import IconQuoteMain from "../icons/IconQuoteMain";

const Section04 = styled.section`
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
	max-width: 1450px;
	padding: 64px 64px 128px 64px;
	margin: 0 auto;

	.item {
		flex-basis: 25%;
		min-width: calc((100rem / 12) * 3);
		padding: 24px;
		line-height: var(--line-height-md);
		font-family: "Source Sans Pro", sans-serif;

		svg {
			margin-bottom: 16px;
		}
		p {
			margin-bottom: 32px;
			font-size: var(--font-head3-size);
			color: var(--darkgray);
		}
		> div {
			font-size: var(--font-head3-size);
			color: var(--darkblack);
			strong {
				padding-top: 16px;
				border-top: 1px solid var(--lightgray2);
				font-weight: var(--font-bold);
			}
		}
	}
`;
const MainSection04 = () => {
	const section04Data = [
		{
			person: "Director of Product Management",
			company: "Microsoft",
			quotation:
				"Stack Overflow for Teams has been a resource for our entire company. Not only for developers to solve problems, it’s also enabled our sales field to answer technical questions that help them close deals.",
		},
		{
			person: "Director of Engineering",
			company: "Elastic Cloud",
			quotation:
				"Engineers should help solve the hardest questions, the unknowns, where being familiar with how the product was built is essential. But we don’t want to keep answering solved              problems over and over again. That’s where Stack Overflow for Teams really helps.",
		},
		{
			person: "Engineering",
			company: "Expensify",
			quotation:
				"As we started to use [Stack Overflow for Teams] and saw how nice it was to have a repository of information, we started to see it spread to other teams. Our customer support team                 started using it, our people success team started using it, next thing we knew, we had [Slack] integrations all over the place.",
		},
		{
			person: "Software Engineer",
			company: "Box",
			quotation:
				"What we love about Stack Overflow for Teams is that it’s a very dynamic tool…there’s just so many ways to use this as a liaison between different teams and different knowledge                 bases.",
		},
	];
	return (
		<Section04>
			{section04Data.map((el, idx) => {
				return (
					<div key={idx} className="item">
						<IconQuoteMain color={"var(--blue)"} />
						<p>{el.quotation}</p>
						<div>
							<strong>{el.person}</strong>
							<br />
							{el.company}
						</div>
					</div>
				);
			})}
		</Section04>
	);
};
export default MainSection04;
