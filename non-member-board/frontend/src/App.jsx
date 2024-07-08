import { BrowserRouter, Routes, Route } from "react-router-dom";
import Board from "./pages/board/Board";
import "bootstrap/dist/css/bootstrap.min.css";

function App() {
  return (
    <>
      <div>
        <BrowserRouter>
          <Routes>
            <Route path="/board/" element={<Board />}></Route>
          </Routes>
        </BrowserRouter>
      </div>
    </>
  );
}

export default App;
