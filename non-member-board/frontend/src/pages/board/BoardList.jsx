import { useEffect } from "react";
import axios from "axios";

const BoardList = () => {
  useEffect(() => {
    console.log(import.meta.env.VITE_API_URL);
    axios
      .get(`${import.meta.env.VITE_API_URL}/board`)
      .then((response) => {
        console.log(response.data);
      })
      .catch((error) => {
        console.error("API 호출 중 오류 발생:", error);
      });
  }, []); // 빈 배열을 전달하여 컴포넌트가 처음 마운트될 때만 실행

  return <div>BoardList</div>;
};

export default BoardList;
