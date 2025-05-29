import React, { useState } from 'react'
import axios from 'axios'

export default function Login() {
  const [username, setUsername] = useState("")
  const [password, setPassword] = useState("")

  function handleLogin(e) {
    e.preventDefault()
    axios.post("http://localhost:8080/api/auth/login", { username, password })
      .then(res => {
        localStorage.setItem("token", res.data)
        alert("Login efetuado!")
      })
      .catch(() => alert("Usuário ou senha inválidos"))
  }

  return (
    <form onSubmit={handleLogin}>
      <h2>Login</h2>
      <input type="text" placeholder="Usuário" value={username} onChange={e => setUsername(e.target.value)} />
      <input type="password" placeholder="Senha" value={password} onChange={e => setPassword(e.target.value)} />
      <button type="submit">Entrar</button>
    </form>
  )
}