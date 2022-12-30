import React, {useEffect} from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";
import AnswerBlock from "../components/AnswerBlock";
import QuestionBlock from "../components/QuestionBlock";

const DetailBox = styled.div`
  width: 100%;
  padding:24px;
`;

const DetailHeader = styled.div`
  display: flex;
  flex-flow: row nowrap;
  justify-content: space-between;
  h1 {
    overflow-wrap: break-word;
    font-size: 27px;
    margin-bottom: 8px;
    flex: 1 auto;
    color: rgb(59 64 69);
  }
  .btnPrimary {
    margin-left: 12px;
    font-size: 13px;
    padding: 10.4px;
    width: 102px;
    height: 38px;
  }
`;
const DetailDate = styled.div`
  display: flex;
  padding-bottom: 8px;
  margin-bottom: 16px;
  flex-wrap: wrap;
  border-bottom: 1px solid var(--lightgray2);
  .DateBox {
    white-space: nowrap;
    margin-bottom: 8px;
    margin-right: 16px;
    font-size: 13px;
  }
  .DateBox2 {
    white-space: nowrap;
    margin-bottom: 8px;
    font-size: 13px;
  }
  span {
    color: var(--gray);
    margin-right: 2px;
  }
`;
const DetailBody = styled.div`
  width: 100%;
  float: left;
`;

const QuestionsDetail = ({ setFlag, setIsFooter }) => {
    useEffect(() => {
		setFlag(true);
		setIsFooter(true);
	}, []);
  return (
    <DetailBox>
      <DetailHeader>
        <h1>Uncaught ReferenceError: cnt is not defined</h1>
        <div>
        <Link to="/questions/ask">
          <button className="btnPrimary">Ask Question</button>
        </Link>
        </div>
      </DetailHeader>
      <DetailDate>
        <div className="DateBox">
          <span>Asked</span>
          <time>today</time>
        </div>
        <div className="DateBox">
          <span>Modified</span>
          <time>today</time>
        </div>
        <div className="DateBox2">
          <span>Viewed</span>
          <time>19 times</time>
        </div>
      </DetailDate>
      <DetailBody>
        <QuestionBlock />
        <AnswerBlock />
      </DetailBody>
    </DetailBox>
  );
};

export default QuestionsDetail;
