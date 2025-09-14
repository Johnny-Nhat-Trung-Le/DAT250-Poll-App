import { StrictMode, useState } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import VoteOnPolls from './Components/VoteOnPolls.jsx'
import AppContext from './Contexts/AppContext.jsx'
import { PollProvider } from './Contexts/PollContext.jsx'
import CreateUserModal from './Components/CreateUserModal.jsx'
import CreatePollModal from './Components/CreatePollModal.jsx'


function AppWrapper() {
  const [userId, setUserId] = useState(null)
  const [pollId, setPollId] = useState(null)
  const [showLogin, setShowLogin] = useState(false)
  const [showCreatePoll, setShowCreatePoll] = useState(false)

  return (
    <AppContext.Provider value={{ userId, setUserId, pollId, setPollId }}>
      <div className="sign-in-containter">
        <button className="sign-in-button" onClick={() => setShowLogin(true)}> Login/Sign Up</button>
      </div>
      <CreateUserModal show={showLogin} onClose={() => setShowLogin(false)} />

      <PollProvider>
        <div className="create-poll-container">
          <button className="create-poll-button" onClick={() => setShowCreatePoll(true)}> Create Poll</button>
        </div>
        {/* {showCreatePoll && ( */}
          <CreatePollModal show={showCreatePoll} onClose={() => setShowCreatePoll(false)}
            onSuccess={() => setShowCreatePoll(false)} />
        {/* )} */}
        <VoteOnPolls />
      </PollProvider>
    </AppContext.Provider>
  )

}
createRoot(document.getElementById('root')).render(
  <StrictMode>
    <h2 className="poll-application-title">Poll Application</h2>
    <AppWrapper />
  </StrictMode>,
)
