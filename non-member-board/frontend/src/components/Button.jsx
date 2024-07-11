import "./Button.css";
import { useNavigate } from "react-router-dom";

const Button = ({ url, text, type, onClick }) => {
  const nav = useNavigate();

  const onClickLink = () => {
    if (type === "MODIFY" || type === "DELETE") {
      onClick(type);
    } else {
      nav(url, { replace: true });
    }
  };

  return (
    <button className="Button" onClick={onClickLink}>
      {text}
    </button>
  );
};
export default Button;
