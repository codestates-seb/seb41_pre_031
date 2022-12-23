import styled from "styled-components";

const Container = styled.li`
    border-bottom: 1px solid hsl(210, 8%, 90%);
    /* width: 100%; */
    padding: 1.6rem 0;
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
    display: flex;
    justify-content: space-between;
`;

const TagName = styled.span`
    font-size: var(--font-caption-size);
    margin-bottom: 1.3rem;
`;

const Member = styled.div`
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
                        <TagName>{question.tagName}</TagName>
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
                    </SubInfo>
                </QuestionContent>
            </Container>
        </>
    );
};

export default Question;
