import styled from "styled-components";

const PageContainer = styled.span``;

const Button = styled.button`
    margin: 0 0.2rem;

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
                {Array(pagesNum)
                    .fill()
                    .map((el, index) => (
                        <Button
                            key={index + 1}
                            onClick={() => {
                                setPage(index + 1);
                                window.scrollTo({ top: 0 });
                            }}
                            aria-current={page === index + 1 ? "page" : null}
                        >
                            {index + 1}
                        </Button>
                    ))}
                <Button
                    onClick={() => {
                        setPage(page + 1);
                        window.scrollTo({ top: 0 });
                    }}
                    disabled={page === pagesNum}
                >
                    Next
                </Button>
            </PageContainer>
        </>
    );
};

export default Pagination;
