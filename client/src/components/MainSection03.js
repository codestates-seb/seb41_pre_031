import styled from "styled-components";
import { useState, useRef, useEffect } from "react";
import carouselData from "./../data/carouselData";

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
	min-height: 90px;
	padding: 0 64px;
	opacity: 1;

	li {
		padding: 0 16px;
		margin: 16px 12px;
	}
	li.hide {
		display: none;
	}

	&.view {
		opacity: 1;
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

const useInterval = (callback, delay, isStop) => {
	const savedCollback = useRef();
	useEffect(() => {
		savedCollback.current = callback;
	}, [callback]);
	useEffect(() => {
		const myInterval = () => {
			savedCollback.current();
		};
		let intervalId;
		if (delay !== null && !isStop) {
			intervalId = setInterval(myInterval, delay);
			return () => clearInterval(intervalId);
		} else if (isStop) {
			clearInterval(intervalId);
		}
	}, [delay]);
};

const MainSection03 = () => {
	const [currentPage, setCurrentPage] = useState(0);
	const [isCarouselStop, setIsCarousetStop] = useState(false);

	const viewCount = 4;
	const allPages = Math.ceil(carouselData.length / viewCount);
	const time = 4000;
	const handleShowCarousel = () => {
		setCurrentPage((currentPage) => (currentPage >= allPages - 1 ? 0 : currentPage + 1));
	};

	useInterval(handleShowCarousel, time, isCarouselStop);

	const handleClickCarousel = (e, idx) => {
		setIsCarousetStop(false);
		setCurrentPage(idx);
	};

	return (
		<Section03>
			<p>Thousands of organizations around the globe use Stack Overflow for Teams</p>
			<Carousel>
				{carouselData
					.filter((el, idx) => {
						return idx >= viewCount * currentPage && idx < viewCount * currentPage + viewCount;
					})
					.map((el, idx) => {
						return (
							<li key={idx}>
								<img width={el.width} height={el.height} alt={el.alt} src={el.src} />
							</li>
						);
					})}
			</Carousel>
			<CarouselButtons>
				{[...Array(allPages)].map((el, idx) => {
					return <span key={idx} className={currentPage === idx ? "selected" : ""} onClick={(e) => handleClickCarousel(e, idx)}></span>;
				})}
			</CarouselButtons>
		</Section03>
	);
};
export default MainSection03;
