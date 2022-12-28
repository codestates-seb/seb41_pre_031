import styled from "styled-components";

const Section03 = styled.section`
	padding: 96px 0 64px 0;

	p {
		text-align: center;
		padding: 0 24px;
		margin-bottom: 20px;
		line-height: var(--line-height-md);
		font-size: 1.3rem;
		color: var(--gray);
	}
`;

const Carousel = styled.ul`
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
	align-items: center;
	padding: 0 64px;

	li {
		padding: 0 16px;
		margin: 16px 12px;
	}
`;
const CarouselButtons = styled.div`
	display: flex;
	justify-content: center;
	padding: 12px 0;
	margin: 0 auto;
	text-align: center;

	span {
		display: block;
		width: 24px;
		height: 6px;
		margin: 6px 4px;
		border-radius: 100px;
		background: var(--lightgray);

		&.selected {
			background: var(--dark);
		}
	}
`;

const MainSection03 = () => {
	return (
		<Section03>
			<p>Thousands of organizations around the globe use Stack Overflow for Teams</p>
			<Carousel>
				<li>
					<img width="185" height="37" alt="Instacart" src="https://cdn.sstatic.net/Img/product/teams/logos/instacart-alt.svg?v=15bd0b39b197" />
				</li>
				<li>
					<img width="49" height="55" alt="Chevron" src="https://cdn.sstatic.net/Img/product/teams/logos/chevron-alt.svg?v=3bfd2c06a64b" />
				</li>
				<li>
					<img width="187" height="36" alt="Dialpad" src="https://cdn.sstatic.net/Img/product/teams/logos/dialpad-alt.svg?v=4e63facf7f79" />
				</li>
				<li>
					<img width="165" height="37" src="https://cdn.sstatic.net/Img/product/teams/logos/expensify-alt.svg?v=375099b85ce5" alt="Expensify" />
				</li>
			</Carousel>
			<CarouselButtons>
				<span></span>
				<span className="selected"></span>
				<span></span>
				<span></span>
			</CarouselButtons>
		</Section03>
	);
};
export default MainSection03;
