import React from 'react'
import styled from "styled-components";
import { BREAK_POINT_TABLET } from "../data/breakpoints";
import User from '../components/User';

const UsersBox = styled.div`
    width: 100%;
    h1{
        font-size: 27px;
        margin-bottom: 24px;
    }
    .userList{
        display: grid;
        grid-template-columns: repeat(4, minmax(0, 1fr));
        gap: 12px 12px;
    }
    @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
        .userList{
            grid-template-columns: repeat(2, minmax(0, 1fr));
        }
    }
`
const Users = () => {
    const test = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
  return (
    <UsersBox>
        <h1>Users</h1>
        <div className='userList'>
            {test.map(data => {
                return <User/>
            })}
        </div>
    </UsersBox>
  )
}

export default Users