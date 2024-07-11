import "./Button.css";
import { useNavigate } from "react-router-dom";

const Button = ({ url, text }) => {
  const nav = useNavigate();

  const onClickLink = () => {
    nav(url, { replace: true });
  };

  return (
    <button className="Button" onClick={onClickLink}>
      {text}
    </button>
  );
};
export default Button;
