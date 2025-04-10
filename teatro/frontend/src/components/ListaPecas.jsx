import { useEffect, useState } from 'react';
import axios from 'axios';

function ListaPecas() {
  const [pecas, setPecas] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/pecas')
      .then(response => setPecas(response.data))
      .catch(error => console.error('Erro ao buscar peças:', error));
  }, []);

  return (
    <div className="p-4">
      <h2 className="text-xl font-bold mb-4">Peças Cadastradas</h2>
      {pecas.length === 0 ? (
        <p>Nenhuma peça cadastrada.</p>
      ) : (
        <ul className="space-y-2">
          {pecas.map((peca) => (
            <li key={peca.id} className="border p-2 rounded bg-white shadow">
              <strong>{peca.nome}</strong> — {peca.descricao}<br />
              {peca.data} às {peca.hora} — R$ {peca.valorIngresso.toFixed(2)}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default ListaPecas;
