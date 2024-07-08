import "./Pagination.css";
import { Link } from "react-router-dom";

const Pagination = ({ size, number, totalElements, totalPages }) => {
  const elements = Array.from({ length: totalPages }, (_, index) => (
    <li key={index} className={number == index ? "active" : ""}>
      <Link to={`/board?page=${index}`}>{index + 1}</Link>
    </li>
  ));

  return <ul className="pagination">{elements}</ul>;
};

export default Pagination;
