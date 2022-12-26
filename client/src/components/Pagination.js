import styled from "styled-components";

const PageContainer = styled.span``;

const Dot = styled.span`
    padding: 0 0.8rem;
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

const Pagination = ({ total, limit, page, setPage }) => {
    // 필요한 페이지의 개수 -> 개수만큼 루프 돌면서 페이지 번호 버튼 출력
    const pagesNum = Math.ceil(total / limit);

    const handleBtnClick = (event) => {
        setPage(Number(event.target.innerText));
        window.scrollTo({ top: 0 });
    };

    return (
        <>
            <PageContainer>
                {page === 1 ? null : (
                    <Button
                        onClick={() => {
                            setPage(page - 1);
                            window.scrollTo({ top: 0 });
                        }}
                    >
                        Prev
                    </Button>
                )}
                {pagesNum > 5 && page < pagesNum - 3 && page > 4 ? (
                    <>
                        <Button
                            key={1}
                            onClick={() => {
                                setPage(1);
                                window.scrollTo({ top: 0 });
                            }}
                            aria-current={page === pagesNum ? "page" : null}
                        >
                            1
                        </Button>
                        <Dot>...</Dot>
                        {Array(5)
                            .fill()
                            .map((el, index) => (
                                <Button
                                    key={page - 2 + index}
                                    onClick={handleBtnClick}
                                    aria-current={
                                        page - 2 + index === page
                                            ? "page"
                                            : null
                                    }
                                >
                                    {page - 2 + index}
                                </Button>
                            ))}
                        <Dot>...</Dot>
                        <Button
                            key={pagesNum}
                            onClick={() => setPage(pagesNum)}
                            aria-current={page === pagesNum ? "page" : null}
                        >
                            {pagesNum}
                        </Button>
                    </>
                ) : pagesNum > 5 && page >= pagesNum - 3 && page > 5 ? (
                    <>
                        <Button
                            key={1}
                            onClick={() => {
                                setPage(1);
                                window.scrollTo({ top: 0 });
                            }}
                            aria-current={page === 1 ? "page" : null}
                        >
                            1
                        </Button>
                        <Dot>...</Dot>
                        {Array(5)
                            .fill()
                            .map((el, index) => (
                                <Button
                                    key={pagesNum - 4 + index}
                                    onClick={handleBtnClick}
                                    aria-current={
                                        pagesNum - 4 + index === page
                                            ? "page"
                                            : null
                                    }
                                >
                                    {pagesNum - 4 + index}
                                </Button>
                            ))}
                    </>
                ) : pagesNum <= 5 ? (
                    Array(pagesNum)
                        .fill()
                        .map((el, index) => (
                            <Button
                                key={index + 1}
                                onClick={handleBtnClick}
                                aria-current={
                                    page === index + 1 ? "page" : null
                                }
                            >
                                {index + 1}
                            </Button>
                        ))
                ) : (
                    <>
                        {Array(5)
                            .fill()
                            .map((el, index) => (
                                <Button
                                    key={index + 1}
                                    onClick={handleBtnClick}
                                    aria-current={
                                        page === index + 1 ? "page" : null
                                    }
                                >
                                    {index + 1}
                                </Button>
                            ))}
                        <Dot>...</Dot>
                        <Button
                            key={pagesNum}
                            onClick={() => {
                                setPage(pagesNum);
                                window.scrollTo({ top: 0 });
                            }}
                            aria-current={page === pagesNum ? "page" : null}
                        >
                            {pagesNum}
                        </Button>
                    </>
                )}

                {page === pagesNum ? null : (
                    <Button
                        onClick={() => {
                            setPage(page + 1);
                            window.scrollTo({ top: 0 });
                        }}
                        disabled={page === pagesNum}
                    >
                        Next
                    </Button>
                )}
            </PageContainer>
        </>
    );
};

export default Pagination;
