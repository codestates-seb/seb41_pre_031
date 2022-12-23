import styled from "styled-components";

const Container = styled.li`
    border-bottom: 1px solid var(--lightgray2);
    width: 100%;
    padding: 16px;
    display: flex;
`;

const QuestionInfo = styled.div`
    margin: 0 1.6rem 0.4rem 0px;
    display: flex;
    flex-direction: column;
    align-items: flex-end;
    flex-shrink: 0;
    width: 108px;
    gap: 0.6rem;
    font-size: 13px;

    .answer {
        color: var(--gray);
    }
    .view {
        color: var(--gray);
    }
`;

const QuestionContent = styled.div`
    width: 100%;
    display: flex;
    flex-direction: column;
    /* padding: 0.1rem; */

    .questionTitle {
        font-size: var(--font-head3-size);
        /* margin: 0.2rem 0 0.5rem; */
        margin-bottom: 0.5rem;
        padding-right: 2.4rem;
        color: var(--darkblue);

        &:hover {
            color: var(--blue);
            cursor: pointer;
        }
    }

    .questionBody {
        font-size: var(--font-body1-size);
        margin-bottom: 0.8rem;
    }
`;

const SubInfo = styled.div`
    width: 100%;
    /* display: flex; */
    /* flex-direction: column; */
    /* justify-content: space-between; */
`;

const Tag = styled.button`
    font-size: var(--font-caption-size);
    color: var(--darkblue2);
    background: var(--skyblue);
    margin-bottom: 13px;
    height: 23.59px;
    padding: 0.4rem 0.6rem;
    width: fit-content;
`;

const MemberContainer = styled.div`
    display: flex;
    margin-bottom: 0.2rem;
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

const Question = ({ question }) => {
    return (
        <>
            <Container>
                <QuestionInfo>
                    <div className="votes">{`${question.questionLikes} votes`}</div>
                    <div className="answer">{`${question.answersNum} answer`}</div>
                    <div className="view">{`${question.questionView} views`}</div>
                </QuestionInfo>
                <QuestionContent>
                    <div className="questionTitle">
                        {question.questionTitle}
                    </div>
                    <div className="questionBody">
                        {question.questionBody}...
                    </div>
                    <SubInfo>
                        <Tag>{question.tagName}</Tag>
                        <MemberContainer>
                            <Member>
                                <span className="memberName">
                                    {question.memberName}
                                </span>
                                <span className="pointCount">
                                    {question.pointCount}{" "}
                                </span>
                                <span className="text"> asked </span>
                                <span className="questionDate">
                                    {question.questionRegDate} ago
                                </span>
                            </Member>
                        </MemberContainer>
                    </SubInfo>
                </QuestionContent>
            </Container>
        </>
    );
};

export default Question;
