import Title from "../components/Title";
import { useState } from "react";
import styled from "styled-components";
import dummyQuestions from "../data/dummyQuestions";
import Question from "../components/Question";
import Pagination from "../components/Pagination";
import "../globalStyle.css";

const Container = styled.div`
    .content {
        margin-bottom: 4rem;
    }
`;

const TopContainer = styled.div`
    margin: 0 0 1.2rem;
    padding: 2rem 0 0 1.6rem;
    display: flex;
    justify-content: space-between;

    .askBtn {
        margin-right: 16px;
    }
`;

const QuestionContainer = styled.ul`
    padding: 0px;

    .questionCount {
        padding: 0 0 1.2rem 1.6rem;
        border-bottom: 1px solid var(--lightgray2);
        font-size: var(--font-head3-size);
    }
`;

const PageContainer = styled.div`
    margin: 4rem 0 3rem 1.6rem;
    display: flex;
    justify-content: space-between;
`;

const Button = styled.button`
    margin: 0 0.2rem;
    font-size: var(--font-button-size);

    &[aria-current] {
        background: var(--orange);
        color: var(--white);
        &:hover {
            background: var(--orange);
        }
    }

    &:hover {
        background: var(--lightgray2);
        cursor: pointer;
    }
`;

const PerpageText = styled.span`
    padding: 0 0.8rem;
    font-size: var(--font-button-size);
`;

const AllQuestions = () => {
    const [data, setData] = useState(dummyQuestions);
    // 현재 페이지
    const [page, setPage] = useState(1);
    // 보여줄 최대 게시글 수
    const [limit, setLimit] = useState(15);

    // 첫 게시물의 index
    const offset = (page - 1) * limit;

    return (
        <>
            <Container>
                <div className="content">
                    <TopContainer>
                        <span>
                            <Title title="All Questions" />
                        </span>
                        <span className="askBtn">
                            <button className="btnPrimary">Ask Question</button>
                        </span>
                    </TopContainer>
                    <QuestionContainer>
                        <div className="questionCount">
                            {data.length} questions
                        </div>
                        <div>
                            {data.slice(offset, offset + limit).map((el) => (
                                <Question key={el.questionId} question={el} />
                            ))}
                        </div>
                    </QuestionContainer>
                </div>
                <PageContainer>
                    <Pagination
                        total={data.length}
                        limit={limit}
                        page={page}
                        setPage={setPage}
                    />
                    <span>
                        <Button
                            onClick={() => {
                                setLimit(15);
                                window.scrollTo({ top: 0 });
                            }}
                            aria-current={limit === 15 ? "page" : null}
                        >
                            15
                        </Button>
                        <Button
                            onClick={() => {
                                setLimit(30);
                                window.scrollTo({ top: 0 });
                            }}
                            aria-current={limit === 30 ? "page" : null}
                        >
                            30
                        </Button>
                        <Button
                            onClick={() => {
                                setLimit(50);
                                window.scrollTo({ top: 0 });
                            }}
                            aria-current={limit === 50 ? "page" : null}
                        >
                            50
                        </Button>
                        <PerpageText>per page</PerpageText>
                    </span>
                </PageContainer>
            </Container>
        </>
    );
};

export default AllQuestions;
