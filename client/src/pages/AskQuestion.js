import styled from "styled-components";
import background from "../icons/askPageBackground.svg";
import React, { useEffect, useState } from "react";
import TextEdit from "../components/TextEdit";
import PencilIconSearch from "../icons/askPageIconSearch";
import { BREAK_POINT_TABLET } from "../data/breakpoints";

const Container = styled.div`
    display: flex;
    flex-direction: column;
    padding: 0 1.6rem 2.4rem 1.6rem;
    background: #f8f9f9;
    width: 100%;
    align-items: center;

    @media screen and (max-width: 1272px) {
        align-items: stretch;
    }
`;

const Content = styled.div`
    display: flex;
    flex-direction: column;
`;

const Head = styled.div`
    width: 100%;
    background-image: url(${background});
    background-repeat: no-repeat;
    background-position: right;
    display: flex;
    flex-direction: column;

    .headTitle {
        width: 851.2px;
        margin: 2.9rem 0 3.4rem 0;
        font-size: 27px;
        font-weight: 550;
        color: #232629;
    }

    @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
        background-image: none;
    }
`;

const TipContainer = styled.div`
    display: flex;

    .tipSideBox {
        width: 348.81px;
        margin-left: 1.2rem;

        @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
            display: none;
        }
    }
`;

const QuestionTip = styled.div`
    border: 1px solid var(--darkskyblue);

    padding: 24px;
    margin: 1.2rem 0 1rem 0;
    background: #ebf4fb;

    border-radius: 3px;
    color: #3b4045;
    width: 851.2px;

    @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
        width: 100%;
    }

    h2 {
        font-size: 21px;
        margin-bottom: 0.8rem;
    }

    p {
        font-size: var(--font-label-size);

        a {
            font-size: var(--font-label-size);
            color: var(--darkblue);
        }
    }

    ul {
        list-style: disc outside none;
        margin-left: 1.8rem;
        line-height: 130%;
        font-size: var(--font-body1-size);
    }

    .bodyLine1 {
        margin-bottom: 0.2rem;
        line-height: 120%;
    }

    .bodyLine2 {
        margin-bottom: 1.5rem;
    }

    h5 {
        font-size: var(--font-body1-size);
        font-weight: bold;
        margin-bottom: 0.5rem;
    }
`;

const TitleContainer = styled.div`
    display: flex;
    @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
        flex-direction: column;
    }
    .titleSideBlank {
        width: 348.81px;
        margin-left: 1.2rem;

        @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
            display: none;
        }
    }
`;

const Title = styled.div`
    border: 1px solid var(--lightgray2);
    padding: 24px;
    margin-bottom: 1rem;
    border-radius: 3px;
    background: var(--bg-color);
    width: 851.2px;

    @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
        width: 100%;
    }

    .title {
        font-size: var(--font-label-size);
        font-weight: 550;
    }

    .description {
        font-size: var(--font-caption-size);
        margin: 0.5rem 0;
    }

    input {
        padding: 7.8px 9.1px;
        width: 100%;
        border: 1px solid var(--lightgray);

        ::placeholder {
            color: var(--lightgray);
        }

        :focus {
            box-shadow: 0px 0px 0px 4px #ebf4fb;
            transition: 0.5s;
            border-color: var(--darkblue);
        }
    }

    .button {
        margin-top: 0.8rem;

        button {
            cursor: pointer;
        }
    }
`;

const SideBox = styled.div`
    border: 1px solid var(--lightgray2);
    border-radius: 3px;
    margin-left: 1.2rem;
    width: 348.81px;
    height: 153.36px;
    background: var(--white);
    box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05),
        0 2px 8px hsla(0, 0%, 0%, 0.05);

    @media screen and (max-width: 1266px) {
        height: 180.36px;
    }

    @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
        margin: 0 0 1rem 0;
        width: 100%;
        height: 134.758px;
    }

    .title {
        font-size: var(--font-label-size);
        padding: 1rem;
        background: #f8f9f9;
        border-bottom: 1px solid var(--lightgray2);
    }
    .sideContainer {
        display: flex;
        padding: 1rem 1.5rem;

        .description {
            margin-left: 16px;
            font-size: var(--font-caption-size);

            .line1 {
                line-height: 135%;
                margin-bottom: 12px;
            }

            .line2 {
                line-height: 135%;
            }
        }
    }
`;

const BodyContainer = styled.div`
    display: flex;
    @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
        flex-direction: column;
    }

    .bodySideBlank {
        width: 348.81px;
        margin-left: 1.2rem;
        @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
            display: none;
        }
    }

    .opacityBodySideBlank {
        width: 348.81px;
        margin-left: 1.2rem;
        @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
            display: none;
        }
    }

    .opacityTagSideBlank {
        width: 348.81px;
        margin-left: 1.2rem;
        @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
            display: none;
        }
    }
`;

const Opacity = styled.div`
    opacity: 0.33;
    cursor: not-allowed;
    display: flex;
`;

const BodyOpacity = styled.div`
    opacity: 0.33;
    cursor: not-allowed;
    border: 1px solid var(--lightgray2);
    padding: 24px;
    margin-bottom: 1rem;
    border-radius: 3px;
    background: var(--bg-color);
    width: 851.2px;
    height: 397.76px;

    @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
        width: 100%;
    }

    .title {
        font-size: var(--font-label-size);
        font-weight: 550;
    }

    .description {
        font-size: var(--font-caption-size);
        margin: 0.5rem 0;
    }

    .editor {
        margin-top: 0.8rem;
    }

    .button {
        margin-top: 4rem;

        button {
            cursor: pointer;
        }
    }
`;

const Body = styled.div`
    border: 1px solid var(--lightgray2);
    padding: 24px;
    margin-bottom: 1rem;
    border-radius: 3px;
    background: var(--bg-color);
    width: 851.2px;
    height: 397.76px;

    @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
        width: 100%;
    }

    .title {
        font-size: var(--font-label-size);
        font-weight: 550;
    }

    .description {
        font-size: var(--font-caption-size);
        margin: 0.5rem 0;
    }

    .editor {
        margin-top: 0.8rem;
    }

    .button {
        margin-top: 4rem;

        button {
            cursor: pointer;
        }
    }
`;

const TagContainer = styled.div`
    display: flex;
    @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
        flex-direction: column;
    }

    .tagSideBlank {
        width: 348.81px;
        margin-left: 1.2rem;
        @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
            display: none;
        }
    }
    .opacityTagSideBlank {
        width: 348.81px;
        margin-left: 1.2rem;
        @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
            display: none;
        }
    }
`;

const Tags = styled.div`
    border: 1px solid var(--lightgray2);
    padding: 24px;
    margin-bottom: 1rem;
    border-radius: 3px;
    background: var(--bg-color);
    width: 851.2px;
    height: 170px;

    :focus {
        transition: 0.5s;
        border-color: var(--darkblue);
    }

    @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
        width: 100%;
    }

    .title {
        font-size: var(--font-label-size);
        font-weight: 550;
    }

    .description {
        font-size: var(--font-caption-size);
        margin: 0.5rem 0;
    }

    .button {
        margin-top: 0.8rem;
        button {
            cursor: pointer;
        }
    }

    .selected{
        border: 1px solid var(--darkblue);
        box-shadow: 0px 0px 0px 4px #ebf4fb;
        transition: 0.5s;
    }
`;

const TagsOpacity = styled.div`
    opacity: 0.33;
    cursor: not-allowed;
    border: 1px solid var(--lightgray2);
    padding: 24px;
    margin-bottom: 1rem;
    border-radius: 3px;
    background: var(--bg-color);
    width: 851.2px;
    height: 170px;

    @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
        width: 100%;
    }

    .title {
        font-size: var(--font-label-size);
        font-weight: 550;
    }

    .description {
        font-size: var(--font-caption-size);
        margin: 0.5rem 0;
    }
`;

const TagsInput = styled.div`
    display: flex;
    align-items: flex-start;
    flex-wrap: wrap;
    border: 1px solid var(--lightgray);
    padding: 0 0.3rem;
    border-radius: 3px;

    > ul {
        display: flex;
        flex-wrap: wrap;
        padding: 0;
        margin: 7px 0 0 0;

        > .tag {
            width: auto;
            height: 25px;
            padding: 0px 4px;
            font-size: var(--font-caption-size);
            display: flex;
            align-items: center;
            justify-content: center;
            color: #39739d;
            background: #e1ecf4;
            border-radius: 3px;
            margin: 0 7px 7px 0;
            > li {
                display: flex;
                align-items: center;
                justify-content: center;
            }
            .tagContent {
                padding: 0 4px;
            }
            .tagRemove {
                margin-left: 4px;
                font-size: 18px;
                font-weight: 500;
                padding-bottom: 2px;
                :hover {
                    cursor: pointer;
                }
            }
        }
    }

    > input {
        flex: 1;
        padding: 7.8px 9.1px;
        width: 100%;
        border: none;

        ::placeholder {
            color: var(--lightgray);
        }
    }
`;

const TagSideBox = styled.div`
    border: 1px solid var(--lightgray2);
    border-radius: 3px;
    margin-left: 1.2rem;
    width: 348.81px;
    height: 220px;
    background: var(--white);
    box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05),
        0 2px 8px hsla(0, 0%, 0%, 0.05);

    @media screen and (max-width: 1229px) {
        height: 270px;
    }

    @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
        margin: 0 0 1rem 0;
        width: 100%;
        height: 160px;
    }

    .title {
        font-size: var(--font-label-size);
        padding: 1rem;
        background: #f8f9f9;
        border-bottom: 1px solid var(--lightgray2);
    }
    .sideContainer {
        display: flex;
        padding: 1rem 1.5rem;

        .description {
            margin-left: 16px;
            font-size: var(--font-caption-size);

            .line1 {
                line-height: 135%;
                margin-bottom: 12px;
            }

            .line2 {
                line-height: 135%;
                margin-bottom: 12px;
            }

            a {
                color: var(--darkblue);
            }
        }
    }
`;

const BottomButton = styled.div`
    button {
        cursor: pointer;
    }

    .btnPrimary {
        margin-right: 0.8rem;
    }

    .btnDiscard {
        color: #c22e32;
        margin-left: 0.5rem;
    }
`;

const AskQuestion = ({ setFlag, setIsFooter }) => {
    useEffect(() => {
        setFlag(false);
        setIsFooter(true);
    }, []);
    const [tags, setTags] = useState([]);
    const [text, setText] = useState("");
    const [nextStepFirst, setNextStepFirst] = useState(false);
    const [nextStepSecond, setNextStepSecond] = useState(false);
    const [nextStepThird, setNextStepThird] = useState(false);
    const [content, setContent] = useState("");
    const [titleSidebox, setTitleSidebox] = useState(true);
    const [bodySidebox, setBodySidebox] = useState(false);
    const [tagSidebox, setTagSidebox] = useState(false);
    const [isActive, setIsActive] = useState(false);

    const onChange = (event) => {
        setText(event.target.value);
    };

    const removeTags = (removeIdx) => {
        tags.splice(removeIdx, 1);
        setTags([...tags]);
    };

    const addTags = (event) => {
        if (
            window.event.keyCode === 32 &&
            event !== "" &&
            !tags.includes(event)
        ) {
            setTags([...tags, event]);
            setText("");
        }
        if (window.event.keyCode === 32 && tags.includes(event)) {
            setText("");
        }
    };

    return (
        <>
            <Container>
                <Content>
                    <Head>
                        <div className="headTitle">Ask a public question</div>
                        <div className="background"></div>
                    </Head>

                    <TipContainer>
                        <QuestionTip>
                            <h2 className="title">Writing a good question</h2>
                            <p className="bodyLine1">
                                You’re ready to{" "}
                                <a href="https://stackoverflow.com/help/how-to-ask">
                                    ask
                                </a>{" "}
                                a{" "}
                                <a href="https://stackoverflow.com/help/on-topic">
                                    programming-related question
                                </a>{" "}
                                and this form will help guide you through the
                                process.
                            </p>
                            <p className="bodyLine2">
                                Looking to ask a non-programming question? See{" "}
                                <a href="https://stackexchange.com/sites#technology-traffic">
                                    the topics here
                                </a>{" "}
                                to find a relevant site.
                            </p>
                            <h5>Steps</h5>
                            <ul>
                                <li>
                                    Summarize your problem in a one-line title.
                                </li>
                                <li>Describe your problem in more detail.</li>
                                <li>
                                    Describe what you tried and what you
                                    expected to happen.
                                </li>
                                <li>
                                    Add “tags” which help surface your question
                                    to members of the community.
                                </li>
                                <li>
                                    Review your question and post it to the
                                    site.
                                </li>
                            </ul>
                        </QuestionTip>
                        <div className="tipSideBox"></div>
                    </TipContainer>

                    <TitleContainer>
                        <Title>
                            <div className="title">Title</div>
                            <div className="description">
                                Be specific and imagine you’re asking a question
                                to another person.
                            </div>
                            <div className="input">
                                <input
                                    placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
                                    onClick={() => {
                                        setTitleSidebox(true);
                                        setBodySidebox(false);
                                        setTagSidebox(false);
                                        setIsActive(false);
                                    }}
                                ></input>
                            </div>
                            {nextStepFirst === true ? null : (
                                <div className="button">
                                    <button
                                        className="buttonLink btnPrimary"
                                        onClick={() => {
                                            setNextStepFirst(true);
                                            setTitleSidebox(false);
                                            setBodySidebox(true);
                                        }}
                                    >
                                        Next
                                    </button>
                                </div>
                            )}
                        </Title>
                        {titleSidebox === true ? null : (
                            <div className="titleSideBlank"></div>
                        )}

                        {titleSidebox === true ? (
                            <SideBox>
                                <div className="title">
                                    Writing a good title
                                </div>
                                <div className="sideContainer">
                                    <div className="icon">
                                        <PencilIconSearch />
                                    </div>
                                    <div className="description">
                                        <p className="line1">
                                            Your title should summarize the
                                            problem.
                                        </p>
                                        <p className="line2">
                                            You might find that you have a
                                            better idea of your title after
                                            writing out the rest of the
                                            question.
                                        </p>
                                    </div>
                                </div>
                            </SideBox>
                        ) : null}
                    </TitleContainer>

                    <BodyContainer>
                        {nextStepFirst === true ? (
                            <>
                                <Body>
                                    <div className="title">
                                        What are the details of your problem?
                                    </div>
                                    <div className="description">
                                        Introduce the problem and expand on what
                                        you put in the title. Minimum 20
                                        characters.
                                    </div>
                                    <div
                                        className="editor"
                                        onClick={() => {
                                            setTitleSidebox(false);
                                            setBodySidebox(true);
                                            setTagSidebox(false);
                                            setIsActive(false);
                                        }}
                                    >
                                        <TextEdit setContent={setContent} />
                                    </div>
                                    {nextStepSecond === true ? null : (
                                        <div className="button">
                                            <button
                                                className="buttonLink btnPrimary"
                                                onClick={() => {
                                                    setNextStepSecond(true);
                                                    setBodySidebox(false);
                                                    setTagSidebox(true);
                                                    setTitleSidebox(false);
                                                }}
                                            >
                                                Next
                                            </button>
                                        </div>
                                    )}
                                </Body>
                                {bodySidebox === true ? null : (
                                    <div className="bodySideBlank"></div>
                                )}
                                {bodySidebox === true ? (
                                    <SideBox>
                                        <div className="title">
                                            Introduce the problem
                                        </div>
                                        <div className="sideContainer">
                                            <div className="icon">
                                                <PencilIconSearch />
                                            </div>
                                            <div className="description">
                                                <p className="line2">
                                                    Explain how you encountered
                                                    the problem you’re trying to
                                                    solve, and any difficulties
                                                    that have prevented you from
                                                    solving it yourself.
                                                </p>
                                            </div>
                                        </div>
                                    </SideBox>
                                ) : null}
                            </>
                        ) : (
                            <>
                                <BodyOpacity>
                                    <div className="title">
                                        What are the details of your problem?
                                    </div>
                                    <div className="description">
                                        Introduce the problem and expand on what
                                        you put in the title. Minimum 20
                                        characters.
                                    </div>
                                    <div className="editor">
                                        <TextEdit setContent={setContent} />
                                    </div>
                                </BodyOpacity>

                                <div className="opacityBodySideBlank"></div>
                            </>
                        )}
                    </BodyContainer>

                    <TagContainer>
                        {nextStepSecond === true ? (
                            <>
                                <Tags>
                                    <div className="title">Tags</div>
                                    <div className="description">
                                        Add up to 5 tags to describe what your
                                        question is about. Start typing to see
                                        suggestions.
                                    </div>
                                    <TagsInput
                                        onClick={() => {
                                            setTitleSidebox(false);
                                            setBodySidebox(false);
                                            setTagSidebox(true);
                                            setIsActive(true);
                                        }}
                                        className={isActive ? 'selected' : ''}
                                    >
                                        <ul className="tags">
                                            {tags.map((tag, index) => (
                                                <li key={index} className="tag">
                                                    <span className="tagContent">
                                                        {tag}
                                                    </span>
                                                    <span
                                                        className="tagRemove"
                                                        onClick={() =>
                                                            removeTags(index)
                                                        }
                                                    >
                                                        &times;
                                                    </span>
                                                </li>
                                            ))}
                                        </ul>
                                        <input
                                            className="tagInput"
                                            type="text"
                                            onKeyUp={(el) => {
                                                addTags(el.target.value);
                                            }}
                                            onChange={onChange}
                                            value={text}
                                            placeholder="e.g. (asp.net wordpress mongodb)"
                                        ></input>
                                    </TagsInput>
                                    {nextStepThird === true ? null : (
                                        <div className="button">
                                            <button
                                                className="buttonLink btnPrimary"
                                                onClick={() =>
                                                    setNextStepThird(true)
                                                }
                                            >
                                                Next
                                            </button>
                                        </div>
                                    )}
                                </Tags>

                                {tagSidebox === true ? (
                                    <TagSideBox>
                                        <div className="title">Adding tags</div>
                                        <div className="sideContainer">
                                            <div className="icon">
                                                <PencilIconSearch />
                                            </div>
                                            <div className="description">
                                                <p className="line1">
                                                    Tags help ensure that your
                                                    question will get attention
                                                    from the right people.
                                                </p>
                                                <p className="line2">
                                                    Tag things in more than one
                                                    way so people can find them
                                                    more easily. Add tags for
                                                    product lines, projects,
                                                    teams, and the specific
                                                    technologies or languages
                                                    used.
                                                </p>
                                                <a href="https://stackoverflow.com/help/tagging">
                                                    Learn more about tagging
                                                </a>
                                            </div>
                                        </div>
                                    </TagSideBox>
                                ) : null}
                                {tagSidebox === true ? null : (
                                    <div className="tagSideBlank"></div>
                                )}
                            </>
                        ) : (
                            <>
                                <TagsOpacity>
                                    <div className="title">Tags</div>
                                    <div className="description">
                                        Add up to 5 tags to describe what your
                                        question is about. Start typing to see
                                        suggestions.
                                    </div>
                                    <TagsInput>
                                        <input
                                            className="tagInput"
                                            type="text"
                                            onKeyUp={(el) => {
                                                addTags(el.target.value);
                                            }}
                                            onChange={onChange}
                                            value={text}
                                            placeholder="e.g. (asp.net wordpress mongodb)"
                                        ></input>
                                    </TagsInput>
                                    {/* button 조건문 */}
                                </TagsOpacity>
                                <div className="opacityTagSideBlank"></div>
                            </>
                        )}
                        {/* {tagSidebox === true ? null : (
                            <div className="tagSideBlank"></div>
                        )} */}
                    </TagContainer>

                    {nextStepThird === true ? (
                        <BottomButton>
                            <button className="buttonLink btnPrimary">
                                Review your question
                            </button>
                            <button className="btnDiscard">
                                Discard draft
                            </button>
                        </BottomButton>
                    ) : (
                        <Opacity>
                            <BottomButton>
                                <button className="buttonLink btnPrimary">
                                    Review your question
                                </button>
                                <button className="btnDiscard">
                                    Discard draft
                                </button>
                            </BottomButton>
                        </Opacity>
                    )}
                </Content>
            </Container>
        </>
    );
};

export default AskQuestion;