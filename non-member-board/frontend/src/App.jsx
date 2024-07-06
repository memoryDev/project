import { BrowserRouter, Routes, Route } from "react-router-dom";
import BoardList from "./pages/board/BoardList";

function App() {
  return (
    <>
      <div>
        <BrowserRouter>
          <Routes>
            <Route path="/board/list" element={<BoardList />}></Route>
          </Routes>
        </BrowserRouter>
      </div>
    </>
  );
}

export default App;
