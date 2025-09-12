import {useState, useEffect, createContext } from 'react'

export const PollContext = createContext()

export const PollProvider = ({ children }) => {
    const [polls, setPolls] = useState([])

    const BASE_API_URL = 'http://localhost:8080'

    const fetchPolls = async () => {
        try {
            const response = await fetch(`${BASE_API_URL}/polls`)

            if (!response.ok) throw new Error(`Response status: ${response.status}`)

            const data = await response.json()
            setPolls([...data])
        } catch (error) {
            console.error("Error fetching polls:", error.message)
        }
    }

    useEffect(() => {
        fetchPolls()
    }, [])

    return (
        <PollContext.Provider value={{ polls, setPolls, fetchPolls}}>
            {children}
        </PollContext.Provider>
    )
}