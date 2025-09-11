import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import TestingRouter from './Components/TestingRouter.jsx'
import CreatePoll from './Components/CreatePoll.jsx'
// import CreateUserExample from './Components/CreateUserExample.jsx'
import Vote from './Components/Vote.jsx'
import CreateUser from './Components/CreateUser.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <CreatePoll />
  </StrictMode>,
)
