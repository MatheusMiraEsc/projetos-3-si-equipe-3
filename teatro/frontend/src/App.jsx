import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import FormPeca from './components/FormPeca';
import ListaPecas from './components/ListaPecas';
import Navbar from './components/Navbar';

function App() {
  return (
    <Router>
      <Navbar />
      <div className="p-4">
        <Routes>
          <Route path="/" element={<FormPeca />} />
          <Route path="/lista" element={<ListaPecas />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;