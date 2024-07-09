import { BrowserRouter, Routes, Route } from "react-router-dom";
import Board from "./pages/board/Board";
import "bootstrap/dist/css/bootstrap.min.css";
import NotFound from "./pages/NotFound";

function App() {
  return (
    <>
      <div>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Board />}></Route>
            <Route path="/board/" element={<Board />}></Route>
            <Route path="/*" element={<NotFound />}></Route>
          </Routes>
        </BrowserRouter>
      </div>
    </>
  );
}

export default App;
