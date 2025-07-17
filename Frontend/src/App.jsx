import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import HomePage from "./pages/HomePage";
import { Toaster } from "react-hot-toast";
import AddSweetPage from "./components/AddSweetPopup";

const App = () => {
  return (
    <Router>
      <Toaster position="top-center" />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/add-sweet" element={<AddSweetPage />} />
      </Routes>
    </Router>
  );
};

export default App;
