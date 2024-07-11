import { getFormatDate } from "../utils/get-format-date";
import { Link } from "react-router-dom";

const BoardItem = ({ id, title, nickname, createdDate }) => {
  return (
    <tr>
      <td>{id}</td>
      <td>
        <Link to={`/board/${id}`}>{title}</Link>
      </td>
      <td>{nickname}</td>
      <td>{getFormatDate(createdDate)}</td>
    </tr>
  );
};

export default BoardItem;
