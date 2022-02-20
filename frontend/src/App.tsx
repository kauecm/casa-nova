import Home from "pages/Home";
import PageLogin from "pages/LoginPage";
import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";


function App() {
  return (
    <BrowserRouter>

      <Routes>
        <Route path="/login" element={<PageLogin />} />
        <Route path="/login/home" element={<Home />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
