import { getFormatDate } from "../utils/get-format-date";

const BoardItem = ({ id, title, nickname, createdDate }) => {
  return (
    <tr>
      <td>{id}</td>
      <td>{title}</td>
      <td>{nickname}</td>
      <td>{getFormatDate(createdDate)}</td>
    </tr>
  );
};

export default BoardItem;
