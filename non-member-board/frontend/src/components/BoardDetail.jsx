import "./BoardDetail.css";
import { useParams, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import axios from "axios";
import { isNumbericParameter } from "../utils/is-Numberic-Parameter";
import { getFormatDate } from "../utils/get-format-date";
import Button from "./Button";

const BoardDetail = () => {
  const nav = useNavigate();
  const param = useParams();
  const paramId = param["id"];
  const [board, setBoard] = useState({
    id: "",
    createdDate: "",
    nickname: "",
    title: "",
    content: "",
  });

  useEffect(() => {
    //id값 유효성 체크
    if (!isNumbericParameter(paramId)) {
      nav("/board", { replace: true });
      return;
    }

    axios
      .get(`${import.meta.env.VITE_API_URL}/board/${paramId}`)
      .then((response) => {
        const data = response.data;
        console.log(data);

        setBoard({
          ...board,
          id: data.id,
          createdDate: data.createdDate,
          nickname: data.nickname,
          title: data.title,
          content: data.content,
        });
        console.log(board);
      })
      .catch((error) => {
        console.error("API 호출 중 오류 발생:", error);
      });
  }, [nav, paramId]);

  return (
    <>
      <div className="boardDetail_wrapper">
        <div className="box_line">
          <div className="box_left">
            <h4>번호</h4>
          </div>
          <div className="box_right">
            <p>{board.id}</p>
          </div>
        </div>
        <div className="box_line">
          <div className="box_left">
            <h4>작성일</h4>
          </div>
          <div className="box_right">
            <p>{getFormatDate(board.createdDate)}</p>
          </div>
        </div>

        <div className="box_line">
          <div className="box_left">
            <h4>작성자</h4>
          </div>
          <div className="box_right">
            <p>{board.nickname}</p>
          </div>
        </div>

        <div className="box_line">
          <div className="box_left">
            <h4>제목</h4>
          </div>
          <div className="box_right">
            <p>{board.title}</p>
          </div>
        </div>

        <div className="box_line">
          <div className="box_left">
            <h4 className="content_title">내용</h4>
          </div>
          <div className="box_right">
            <p className="content">{board.content}</p>
          </div>
        </div>
      </div>
      <div className="Button_wrapper">
        <Button url={""} text={"수정하기"} />
        <Button url={"/board"} text={"목록"} />
        <Button url={""} text={"삭제하기"} />
      </div>
    </>
  );
};

export default BoardDetail;
