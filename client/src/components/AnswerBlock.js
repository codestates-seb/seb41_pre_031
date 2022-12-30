import React from "react";
import styled from "styled-components";
import QuestionBlock from "./QuestionBlock";
import TextEdit from "./TextEdit";

const AnswerBox = styled.div`
	width: 100%;
	border-bottom: 1px solid rgb(227 230 232);
	padding: 16px 0;
	.title {
		margin: 10px 0px 8px;
	}
	.title h2 {
		font-size: 19px;
	}
	.answer {
		margin: 16px 0px;
		border-bottom: 1px solid rgb(227 230 232);
	}
	.yourAnswer {
		font-size: 19px;
		font-weight: 400;
		padding-top: 20px;
		margin-bottom: 19px;
	}
	.btnPrimary {
		font-size: 13px;
		margin: 0px 2px;
		padding: 10.4px;
	}
`;

const AnswerBlock = () => {
	return (
		<AnswerBox>
			<div className="title">
				<h2>2 Answers</h2>
			</div>
			<div className="answer">
				<QuestionBlock />
			</div>
			<div className="answer">
				<QuestionBlock />
			</div>
			<h2 className="yourAnswer">Your Answer</h2>
			<div className="editBox">
				<TextEdit />
			</div>
			<button className="btnPrimary">Post Your Answer</button>
		</AnswerBox>
	);
};

export default AnswerBlock;
