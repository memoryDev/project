import "./Button.css";
import { useNavigate } from "react-router-dom";

const Button = ({ url, text, onClick }) => {
  const nav = useNavigate();

  const onClickLink = () => {
    if (!url) {
      onClick();
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
