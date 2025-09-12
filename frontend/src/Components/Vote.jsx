
export default function Vote({ pollId, option, voteCount, submitVote}) {
    return (
        <li className='voteoption-VoteOnPolls'>
            <span className="option-VoteOnPolls">{option.option} </span>
            <button className="button-VoteOnPolls" onClick={() => submitVote(pollId, option)}>Vote</button>
            <span className="votecount-VoteOnPolls">{voteCount[option.id]}</span>
        </li>
    )
}