import Vote from './Vote.jsx'

export default function Poll({ poll, submitVote }) {
    if (!poll || !poll.options || !poll.voteCount) return null
    return (
         <div className="poll-VoteOnPolls" key={poll.id}>
            <h2 className="header-VoteOnPolls">{poll.question}</h2>
            <ul className="options-VoteOnPolls">
                {poll.options.map((opt) => (
                    <Vote key={opt.id} pollId={poll.id} option={opt} voteCount={poll.voteCount} submitVote={submitVote} />
                ))}
            </ul>
        </div>
    )
}