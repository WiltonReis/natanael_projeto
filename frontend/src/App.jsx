import React from 'react'
import { Routes, Route } from 'react-router-dom'
import Mapa from './pages/Mapa'
import Login from './pages/Login'
import Cadastro from './pages/Cadastro'

export default function App() {
  return (
    <Routes>
      <Route path="/" element={<Mapa />} />
      <Route path="/login" element={<Login />} />
      <Route path="/cadastro" element={<Cadastro />} />
    </Routes>
  )
}