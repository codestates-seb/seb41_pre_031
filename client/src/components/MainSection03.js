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
	padding: 0 64px;
	opacity: 0;

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

const MainSection03 = () => {
	const [isItemWrapView, setIsItemWrapView] = useState(false);
	const [currentPage, setCurrentPage] = useState(0);
	useEffect(() => {
		// for (let i = 0; i <= allPages; i++) {
		// 	elbtn.addEventListener("click", showCarousel);
		// }
	}, []);
	const elItemWrap = useRef(null);
	const elBtnWrap = useRef(null);
	const viewCount = 4;
	const allPages = Math.ceil(carouselData.length / viewCount);
	const time = 4000;
	let carouselPlay;

	//console.log(allPages);
	//elItemWrap.current.classList.remove("view");

	// //캐러셀 개별 아이템 초기화
	// for (let i = 0; i < elItemWrap.children.length; i++) {
	// 	elItemWrap.children[i].classList.add("hide");
	// }

	// //캐러셀 버튼 생성
	// for (let i = 0; i <= allPages; i++) {
	// 	let elbtn = document.createElement("span");
	// 	elbtn.setAttribute("data-page", i);
	// 	elbtn.addEventListener("click", showCarousel);
	// 	elBtnWrap.appendChild(elbtn);
	// }

	// //버튼 활성화 함수
	// function activeBtn() {
	// 	let elBtns = document.querySelectorAll(".btns span");
	// 	for (let i = 0; i < elBtns.length; i++) {
	// 		if (i === currentPage) {
	// 			elBtns[i].classList.remove("active");
	// 			elBtns[i].classList.add("active");
	// 		} else {
	// 			elBtns[i].classList.add("active");
	// 			elBtns[i].classList.remove("active");
	// 		}
	// 	}
	// }

	// //캐러셀 보여주기 함수
	// function showCarousel(e) {
	// 	elItemWrap.classList.remove("view");
	// 	if (e) {
	// 		clearInterval(carouselPlay);
	// 		currentPage = parseInt(e.currentTarget.getAttribute("data-page") || 0);
	// 	}
	// 	setTimeout(function () {
	// 		for (let i = 0; i < elItemWrap.children.length; i++) {
	// 			if (i >= viewCount * currentPage && i < viewCount * currentPage + viewCount) {
	// 				elItemWrap.children[i].classList.remove("hide"); //보인다
	// 			} else {
	// 				elItemWrap.children[i].classList.add("hide"); //안 보인다
	// 			}
	// 		}

	// 		elItemWrap.classList.add("view");

	// 		activeBtn();

	// 		if (currentPage >= allPages) {
	// 			currentPage = 0;
	// 		} else {
	// 			currentPage++;
	// 		}
	// 	}, 300);
	// }

	const handleShowCarousel = () => {};

	// showCarousel();

	// carouselPlay = setInterval(function () {
	// 	showCarousel();
	// }, time);

	return (
		<Section03>
			<p>Thousands of organizations around the globe use Stack Overflow for Teams</p>
			<Carousel ref={elItemWrap} className={isItemWrapView ? "view" : ""}>
				{carouselData.map((el, idx) => {
					return (
						<li key={idx} className="hide">
							<img width={el.width} height={el.height} alt={el.alt} src={el.src} />
						</li>
					);
				})}
			</Carousel>
			<CarouselButtons ref={elBtnWrap}>
				{[...Array(allPages)].map((el, idx) => {
					return currentPage === idx ? <span key={idx} data-page={el} className="selected"></span> : <span key={idx} data-page={el}></span>;
				})}
			</CarouselButtons>
		</Section03>
	);
};
export default MainSection03;
