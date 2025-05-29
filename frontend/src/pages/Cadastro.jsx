import React, { useState } from 'react'
import axios from 'axios'

export default function Cadastro() {
  const [form, setForm] = useState({
    nome: '', cidade: '', latitude: '', longitude: '', tipoProducao: '', experiencia: false, descricao: ''
  })

  function handleChange(e) {
    const { name, value, type, checked } = e.target
    setForm({ ...form, [name]: type === 'checkbox' ? checked : value })
  }

  function handleSubmit(e) {
    e.preventDefault()
    axios.post("http://localhost:8080/api/produtores", form, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token')
      }
    }).then(() => alert("Cadastrado com sucesso!"))
      .catch(() => alert("Erro no cadastro"))
  }

  return (
    <form onSubmit={handleSubmit}>
      <h2>Cadastrar Produtor</h2>
      <input name="nome" placeholder="Nome" onChange={handleChange} />
      <input name="cidade" placeholder="Cidade" onChange={handleChange} />
      <input name="latitude" placeholder="Latitude" onChange={handleChange} />
      <input name="longitude" placeholder="Longitude" onChange={handleChange} />
      <input name="tipoProducao" placeholder="Tipo de Produção" onChange={handleChange} />
      <textarea name="descricao" placeholder="Descrição" onChange={handleChange} />
      <label>
        <input type="checkbox" name="experiencia" onChange={handleChange} />
        Oferece experiência gastronômica?
      </label>
      <button type="submit">Cadastrar</button>
    </form>
  )
}