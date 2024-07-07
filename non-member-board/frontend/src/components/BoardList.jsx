import { useEffect } from "react";
import axios from "axios";
import { useState } from "react";
import Table from "react-bootstrap/Table";
import BoardItem from "./BoardItem";
import Pagination from "./Pagination";

const BoardList = () => {
  const [content, setContent] = useState([]);
  const [page, setPage] = useState();

  useEffect(() => {
    axios
      .get(`${import.meta.env.VITE_API_URL}/board`)
      .then((response) => {
        const data = response.data;
        setContent(data.content);
        setPage(data.page);
        console.log(data.page);
      })
      .catch((error) => {
        console.error("API 호출 중 오류 발생:", error);
      });
  }, []);

  return (
    <div>
      <Table striped bordered hover variant="dark" size="sm">
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>닉네임</th>
            <th>작성일</th>
          </tr>
        </thead>
        <tbody>
          {content.map((board) => (
            <BoardItem key={board.id} {...board} />
          ))}
        </tbody>
      </Table>
      <Pagination {...page} />
    </div>
  );
};

export default BoardList;
