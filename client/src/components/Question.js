import { Link } from "react-router-dom";
import styled from "styled-components";
import { BREAK_POINT_TABLET } from "../data/breakpoints";
// import { useEffect, useState } from "react";
// import axios from "axios";

const Container = styled.li`
	border-bottom: 1px solid var(--lightgray2);
	width: 100%;
	padding: 16px;
	display: flex;

	@media screen and (max-width: ${BREAK_POINT_TABLET}px) {
		flex-direction: column;
	}
`;

const QuestionInfo = styled.div`
	margin: 0 16px 4px 0;
	display: flex;
	flex-direction: column;
	align-items: flex-end;
	flex-shrink: 0;
	width: 108px;
	gap: 0.6rem;
	line-height: var(--line-height-md);
	font-size: 13px;

	.answer {
		color: var(--gray);
	}
	.view {
		color: var(--gray);
	}

	@media screen and (max-width: ${BREAK_POINT_TABLET}px) {
		flex-direction: row;
		align-items: baseline;
		width: 100%;
	}
`;

const QuestionContent = styled.div`
	width: 100%;
	display: flex;
	flex-direction: column;

	.questionTitle {
		line-height: var(--line-height-md);
		font-size: var(--font-head3-size);
		margin-bottom: 5px;
		padding-right: 24px;
		color: var(--darkblue);

		&:hover {
			color: var(--blue);
			cursor: pointer;
		}
	}

	.questionBody {
		font-size: var(--font-body1-size);
		margin-bottom: 8px;
		line-height: 1.33rem;
		color: #3b4045;
	}
`;

const SubInfo = styled.div`
	width: 100%;
`;

const Tag = styled.button`
	color: var(--darkblue2);
	background: var(--skyblue);
	margin-bottom: 2px;
	margin-right: 2px;
	height: 23.59px;
	padding: 4px 6px;
	width: fit-content;
	line-height: 1;
	font-size: var(--font-caption-size);
`;

const MemberContainer = styled.div`
	display: flex;
	margin-bottom: 0.4rem;
`;

const Member = styled.div`
	margin-left: auto;
	font-size: 12px;
	.memberName {
		margin: 0.2rem;
		color: var(--darkblue);
		&:hover {
			color: var(--blue);
			cursor: pointer;
		}
	}
	.pointCount {
		font-weight: bold;
		color: var(--darkgray);
	}
	.text {
		color: var(--gray);
	}
	.questionDate {
		color: var(--gray);
	}
`;

const Question = ({ question, page }) => {
	// const [answerData, setAnswerData] = useState(0);

	const textLimit = (text, limit = 200) => {
		if (text.length > limit) {
			return `${text.substring(0, limit)}...`;
		}
		return `${text}...`;
	};

	//  answer 개수 받아오는데 안받아와져서 보류
	//   useEffect(() => {
	//     axios
	//         .get(`http://prepro31.iptime.org:8080/questions/${question.questionId}/answers`,
	//         {
	//             headers: {
	//                 Authorization : `${localStorage.getItem("loginToken")}`
	//             }
	//         }
	//         )
	//         .then(res => {
	//             setAnswerData(res.data.data);
	//         // console.log(res.data.data);
	//         });
	// },[answerData]);

	const date = new Date(question.questionRegDate);

	return (
		<>
			<Container>
				<QuestionInfo>
					<div className="votes">{`${question.questionLikes} votes`}</div>
					{/* <div className="answer">{`${question.answers} answer`}</div> */}
					<div className="answer">{`0 answer`}</div>
					<div className="view">{`${question.questionView} views`}</div>
				</QuestionInfo>
				<QuestionContent>
					<Link to={`/questions/${question.questionId}`}>
						<div className="questionTitle">{question.questionTitle}</div>
					</Link>
					{page === "TopQuestion" ? null : (
						<div
							className="questionBody"
							dangerouslySetInnerHTML={{
								__html: question.questionBody.replace(/\n|\r/g, ""),
							}}
						>
							{/* {textLimit(question.questionBody)} */}
						</div>
					)}
					<SubInfo>
						{question.questionTags && question.questionTags.map((el, index) => <Tag key={index}>{el.tagName}</Tag>)}
						<MemberContainer>
							<Member>
								<span className="memberName">{question.memberName}</span>
								<span className="pointCount">{question.pointCount} </span>
								<span className="text"> asked </span>
								<span className="questionDate">{date.toDateString()}</span>
							</Member>
						</MemberContainer>
					</SubInfo>
				</QuestionContent>
			</Container>
		</>
	);
};

export default Question;
