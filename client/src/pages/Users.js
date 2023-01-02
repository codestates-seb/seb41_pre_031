import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { BREAK_POINT_TABLET } from "../data/breakpoints";
import User from "../components/User";
import Pagination from "../components/Pagination";
import axios from "axios";

const UsersBox = styled.div`
	width: 100%;
	padding: 24px; /* 레이아웃에 padding 빠지면서 추가 */
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

const Users = ({ setFlag, setIsFooter }) => {
	useEffect(() => {
		setFlag(true);
		setIsFooter(true);
	}, []);
	//페이지네이션
	const [page, setPage] = useState(1);
	const [limit, setLimit] = useState(36);
	const offset = (page - 1) * limit;

	const [userList, setUserList] = useState(undefined);
	useEffect(() => {
		axios.get("http://34.64.176.88:8080/members").then((res) => {
			setUserList(res.data.data);
		});
	}, []);

	return (
		<UsersBox>
			<h1>Users</h1>
			<div className="userList">
				{userList &&
					userList.slice(offset, offset + limit).map((data, index) => {
						return <User data={data} key={index} />;
					})}
			</div>
			<PageContainer>{userList && <Pagination total={userList.length} limit={limit} page={page} setPage={setPage} />}</PageContainer>
		</UsersBox>
	);
};

export default Users;
