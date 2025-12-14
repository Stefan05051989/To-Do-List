import { StrictMode } from "react"
import { createRoot } from "react-dom/client"
import { TanStackDevtools } from "@tanstack/react-devtools"
import App from "./App"
import "./styles/index.css"
// checked, correct!



createRoot(document.getElementById('root')!).render(
  <StrictMode>
      <App />
      <TanStackDevtools/>
  </StrictMode>,
)