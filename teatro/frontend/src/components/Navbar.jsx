import { Link } from 'react-router-dom';
import './Navbar.css';
function Navbar() {
  return (
    <nav className="bg-gradient-to-r from-blue-700 to-indigo-600 p-4 shadow-md">
      <div className="max-w-6xl mx-auto flex justify-between items-center">
        <h1 className="text-white font-bold text-2xl tracking-wide">🎭 IngressArt</h1>
        <div className="space-x-6 text-white text-md">
          <Link
            to="/"
            className="hover:text-yellow-300 transition duration-200"
          >
            Cadastro de Peças
          </Link>
          <Link
            to="/lista"
            className="hover:text-yellow-300 transition duration-200"
          >
            Visualizar Peças Cadastradas
          </Link>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
