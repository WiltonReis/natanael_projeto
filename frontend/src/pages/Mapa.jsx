import React, { useEffect, useState } from 'react'
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet'
import 'leaflet/dist/leaflet.css'
import axios from 'axios'

export default function Mapa() {
  const [produtores, setProdutores] = useState([])

  useEffect(() => {
    axios.get("http://localhost:8080/api/produtores")
      .then(res => setProdutores(res.data))
      .catch(err => console.error(err))
  }, [])

  return (
    <div>
      <h2>Mapa de Produtores</h2>
      <MapContainer center={[-21.5, -45.5]} zoom={8} style={{ height: "80vh", width: "100%" }}>
        <TileLayer
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
        {produtores.map((p) => (
          <Marker key={p.id} position={[p.latitude, p.longitude]}>
            <Popup>
              <strong>{p.nome}</strong><br />
              {p.descricao}
            </Popup>
          </Marker>
        ))}
      </MapContainer>
    </div>
  )
}