import { BrowserRouter, Routes, Route } from "react-router-dom";
import Board from "./pages/board/List";
import "bootstrap/dist/css/bootstrap.min.css";
import NotFound from "./pages/NotFound";
import Detail from "./pages/board/Detail";

function App() {
  return (
    <>
      <div>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Board />}></Route>
            <Route path="/board/" element={<Board />}></Route>
            <Route path="/board/:id" element={<Detail />}></Route>
            <Route path="/*" element={<NotFound />}></Route>
          </Routes>
        </BrowserRouter>
      </div>
    </>
  );
}

export default App;
