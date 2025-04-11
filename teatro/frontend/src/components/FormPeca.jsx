import { useState } from 'react';
import axios from 'axios';
import './FormPeca.css';


function FormPeca() {
  const [peca, setPeca] = useState({
    nome: '',
    descricao: '',
    data: '',
    hora: '',
    valorIngresso: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setPeca({ ...peca, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('http://localhost:8080/pecas', {
        ...peca,
        valorIngresso: parseFloat(peca.valorIngresso),
      });
      alert('Peça cadastrada com sucesso!');
      setPeca({
        nome: '',
        descricao: '',
        data: '',
        hora: '',
        valorIngresso: ''
      });
    } catch (error) {
      console.error('Erro ao cadastrar peça:', error);
      alert('Erro ao cadastrar peça.');
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Cadastrar Peça</h2>

      <label>Nome:</label>
      <input type="text" name="nome" value={peca.nome} onChange={handleChange} required />

      <label>Descrição:</label>
      <input type="text" name="descricao" value={peca.descricao} onChange={handleChange} required />

      <label>Data:</label>
      <input type="date" name="data" value={peca.data} onChange={handleChange} required />

      <label>Hora:</label>
      <input type="time" name="hora" value={peca.hora} onChange={handleChange} required />

      <label>Valor do Ingresso:</label>
      <input type="number" step="0.01" name="valorIngresso" value={peca.valorIngresso} onChange={handleChange} required />

      <button type="submit">Cadastrar</button>
    </form>
  );
}

export default FormPeca;
