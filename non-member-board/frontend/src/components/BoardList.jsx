import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import axios from "axios";
import Table from "react-bootstrap/Table";
import BoardItem from "./BoardItem";
import Pagination from "./Pagination";

const List = () => {
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const currentPage = searchParams.get("page") || 0;

  const keywordList = [
    {
      value: "title",
      name: "이름",
    },
    {
      value: "nickname",
      name: "닉네임",
    },
  ];
  const [fetchDataTrigger, setFetchDataTrigger] = useState(false);
  const [content, setContent] = useState([]);
  const [page, setPage] = useState();
  const [option, setOption] = useState("title");
  const [keyword, setKeyword] = useState("");

  useEffect(() => {
    axiosSubmit();
  }, [currentPage, fetchDataTrigger]);

  const axiosSubmit = () => {
    axios
      .post(`${import.meta.env.VITE_API_URL}/board?page=${currentPage}`, {
        option: option,
        keyword: keyword,
      })
      .then((response) => {
        const data = response.data;
        setContent(data.content);
        setPage(data.page);
      })
      .catch((error) => {
        console.error("API 호출 중 오류 발생:", error);
      });
  };

  const onChangeOption = (e) => {
    setOption(e.target.value);
  };

  const onChangeKeyWord = (e) => {
    setKeyword(e.target.value);
  };

  const onClickSubmit = () => {
    setFetchDataTrigger(!fetchDataTrigger);
  };

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
      <div>
        <select value={option} onChange={onChangeOption}>
          {keywordList.map(({ value, name }) => {
            <option value={value}>{name}</option>;
          })}

          <option value="title">제목</option>
          <option value="nickname">닉네임</option>
        </select>
        <input type="text" value={keyword} onChange={onChangeKeyWord} />
        <button onClick={onClickSubmit}>검색</button>
      </div>

      <Pagination {...page} />
    </div>
  );
};

export default List;
