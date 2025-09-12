import { createContext } from 'react'

const AppContext = createContext({
    userId: null,
    pollId: null,
    setUserId: () => {},
    setPollId: () => {},
})

export default AppContext