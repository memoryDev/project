import "./BoardForm.css";
import { useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const BoardForm = () => {
  const nav = useNavigate();
  const [inputs, setInputs] = useState({
    nickname: "",
    password: "",
    title: "",
    content: "",
  });

  const inputRef = useRef({});

  const onChange = (e) => {
    const { name, value } = e.target;

    setInputs({
      ...inputs,
      [name]: value,
    });
  };

  //필수값 체크
  const validationCheck = () => {
    if (!inputs.nickname.trim()) {
      alert("작성자를 입력해주세요.");
      inputRef.current["nickname"].focus();
      return false;
    }

    if (!inputs.password.trim()) {
      alert("비밀번호를 입력해주세요.");
      inputRef.current["password"].focus();
      return false;
    }

    if (!inputs.title.trim()) {
      alert("제목을 입력해주세요.");
      inputRef.current["title"].focus();
      return false;
    }

    if (!inputs.content.trim()) {
      alert("내용을 입력해주세요.");
      inputRef.current["content"].focus();
      return false;
    }

    return true;
  };

  const onClickOnSubmit = () => {
    const inValid = validationCheck();

    // 필수값 입력 하지않았을경우 종료
    if (!inValid) {
      return;
    }
  };

  return (
    <>
      <div className="BoardForm">
        <div className="line">
          <h4>작성자</h4>
          <div>
            <input
              type="text"
              name="nickname"
              onChange={onChange}
              value={inputs.nickname}
              ref={(el) => (inputRef.current["nickname"] = el)}
            />
          </div>
        </div>

        <div className="line">
          <h4>비밀번호</h4>
          <div>
            <input
              type="password"
              name="password"
              onChange={onChange}
              value={inputs.password}
              ref={(el) => (inputRef.current["password"] = el)}
            />
          </div>
        </div>

        <div className="line">
          <h4>제목</h4>
          <div>
            <input
              type="text"
              name="title"
              onChange={onChange}
              value={inputs.title}
              ref={(el) => (inputRef.current["title"] = el)}
            />
          </div>
        </div>

        <div className="line">
          <h4>내용</h4>
          <div>
            <textarea
              name="content"
              onChange={onChange}
              value={inputs.content}
              ref={(el) => (inputRef.current["content"] = el)}
            ></textarea>
          </div>
        </div>
      </div>
      <div className="btn_wrap">
        <button onClick={onClickOnSubmit}>작성</button>
        <button
          onClick={() => {
            nav("/board");
          }}
        >
          취소
        </button>
      </div>
    </>
  );
};

export default BoardForm;
