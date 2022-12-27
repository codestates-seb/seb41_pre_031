import React, { useState } from "react";
import styled from "styled-components";
import { BREAK_POINT_TABLET } from "../data/breakpoints";
import User from "../components/User";
import Pagination from "../components/Pagination";

const UsersBox = styled.div`
  width: 100%;
  h1 {
    font-size: 27px;
    margin-bottom: 24px;
  }
  .userList {
    display: grid;
    grid-template-columns: repeat(4, minmax(0, 1fr));
    gap: 12px 12px;
  }
  @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
    .userList {
      grid-template-columns: repeat(2, minmax(0, 1fr));
    }
  }
`;

const PageContainer = styled.div`
  margin: 20px 0;
  display: flex;
  justify-content: space-between;
  float: right;
`;

const Users = () => {
  const [page, setPage] = useState(1);
  const [limit, setLimit] = useState(36);
  //데이터 추가시 삭제
  const test = Array(1000).fill("0");
  const offset = (page - 1) * limit;

  return (
    <UsersBox>
      <h1>Users</h1>
      <div className="userList">
        {test.slice(offset, offset + limit).map((data, index) => {
          return <User key={index} />;
        })}
      </div>
      <PageContainer>
        <Pagination
          total={test.length}
          limit={limit}
          page={page}
          setPage={setPage}
        />
      </PageContainer>
    </UsersBox>
  );
};

export default Users;
