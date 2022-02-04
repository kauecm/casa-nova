import FormLogin from "components/FormLogin";
import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
    <Routes>
      <Route path="/login" element={<FormLogin />} />
    </Routes>
  </BrowserRouter>
  );
}

export default App;
