import { useContext } from 'react'
import AppContext from '../Contexts/AppContext'
import Poll from './Poll'
import { PollContext } from '../Contexts/PollContext'
import '../Styling/VoteOnPolls.css'

export default function VoteOnPolls() {
    const { userId } = useContext(AppContext)

    const BASE_API_URL = 'http://localhost:8080'

    const { polls, fetchPolls } = useContext(PollContext)

    const submitVote = async (pollId, option) => {
        if (!userId) {
            alert("You must be logged in to vote!")
            return
        }

        try {
            const response = await fetch(`${BASE_API_URL}/polls/${pollId}/vote`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ userId, option })
            })
            console.log(JSON.stringify({ userId, option }))
            if (!response.ok) throw new Error(`Response status: ${response.status}`)
            console.log("Vote got submitted")

            await fetchPolls()
        } catch (error) {
            console.error("Error submitting vote:", error.message)
        }

        <div className="polls-VoteOnPolls">
            {polls?.length > 0 ? (
                polls.map((poll) => (
                    <Poll key={poll.id} poll={poll} submitVote={submitVote} />
                ))
            ) : (
                <p>No polls available.</p>
            )}
        </div>
    }
    return (
        <div className="polls-VoteOnPolls">
            {polls?.length > 0 ? (
                polls?.map((poll) => (
                    <Poll key={poll.id} poll={poll} submitVote={submitVote}/>
                ))
            ) : (
                <p>No polls available.</p>
            )}
        </div>
         
    )
}


