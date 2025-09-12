import { StrictMode, useState } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import CreatePoll from './Components/CreatePoll.jsx'
import VoteOnPolls from './Components/VoteOnPolls.jsx'
import CreateUser from './Components/CreateUser.jsx'
import AppContext from './Contexts/AppContext.jsx'
import { PollProvider  } from './Contexts/PollContext.jsx'


function AppWrapper() {
  const [userId, setUserId] = useState(null)
  const [pollId, setPollId] = useState(null)

  return (
    <AppContext.Provider value={{ userId, setUserId, pollId, setPollId}}>
      <CreateUser />
      <PollProvider>
        <CreatePoll />
        <VoteOnPolls />
      </PollProvider>
    </AppContext.Provider>
  )

} 
createRoot(document.getElementById('root')).render(
  <StrictMode>
    <AppWrapper />
  </StrictMode>,
)
