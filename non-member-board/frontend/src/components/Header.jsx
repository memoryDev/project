import { Container, Navbar, Nav } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const Header = () => {
  const nav = useNavigate();

  return (
    <>
      <Navbar bg="dark" data-bs-theme="dark">
        <Container>
          <Navbar.Brand onClick={() => nav("/")}>Home</Navbar.Brand>
          <Nav className="me-auto">
            <Nav.Link href="#home">게시판</Nav.Link>
          </Nav>
        </Container>
      </Navbar>
    </>
  );
};

export default Header;
