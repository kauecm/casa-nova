import Home from "pages/Home";
import ListaPresentes from "pages/ListaPresentes";
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
        <Route path="/" element={<PageLogin />} />
        <Route path="/home" element={<Home />} />
        <Route path="/lista-presentes" element={<ListaPresentes/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
