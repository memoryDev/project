import "./ModalPopup.css";
import { useState, useRef } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";

const ModalPopup = ({ onClick, popup }) => {
  const param = useParams();
  const paramId = param["id"];
  const ref = useRef();
  const nav = useNavigate();
  const [password, setPassword] = useState("");

  const onChangePassword = (e) => {
    setPassword(e.target.value);
  };

  const onClickClose = () => {
    onClick();
    setPassword("");
  };

  const onSubmit = () => {
    if (!password) {
      alert("게시글 비밀번호를 입력해주세요");
      ref.current.focus();
      return;
    }

    axios
      .delete(`${import.meta.env.VITE_API_URL}/board/${paramId}`, {
        data: {
          password: password,
        },
      })
      .then((response) => {
        const data = response.data;

        alert("성공하였습니다.");
        onClickClose();
        nav("/board", { replace: true });
      })
      .catch((error) => {
        if (!error.response) {
          console.log("API 전송오류 : " + error);
          return;
        }

        alert(error.response.data);
      });
  };

  return (
    <div className="ModalPopupWrap">
      <div className="modalBg"></div>
      <div className="ModalPopup">
        <h2 className="title">{popup.title}</h2>
        <p className="content"></p>
        <h3 className="subTitle">게시글 비밀번호</h3>
        <input
          type="password"
          className="input"
          value={password}
          onChange={onChangePassword}
          ref={ref}
        />
        <div className="btn_wrap">
          <button className="confirm" onClick={onSubmit}>
            확인
          </button>
          <button className="cancel" onClick={onClickClose}>
            취소
          </button>
        </div>
      </div>
    </div>
  );
};

export default ModalPopup;
