import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './props/App.jsx'
import App2 from './condi-rendering/App.jsx'
import './props/index.css'
import './condi-rendering/index.css'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App2 />
  </React.StrictMode>,
)
