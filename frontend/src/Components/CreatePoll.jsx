import { useState, useContext } from 'react'
import '../Styling/CreatePoll.css'
import AppContext from '../Contexts/AppContext'
import { PollContext } from '../Contexts/PollContext'

export default function CreatePoll({onSuccess}) {
    const [question, setQuestion] = useState("")
    const [options, setOptions] = useState([{ id: 0, value: "" }])
    const [publishedAt, setPublishedAt] = useState("")
    const [validUntil, setValidUntil] = useState("")
    const { userId, setPollId } = useContext(AppContext)

    const { fetchPolls } = useContext(PollContext)

    const BASE_API_URL = 'http://localhost:8080'

    const addOption = () => {
        setOptions(prev => [
            ...prev, { id: prev.length > 0 ? prev[prev.length - 1].id + 1 : 0, value: "" }
        ])
    }
    const handleChange = (id, newValue) => {
        setOptions(prev =>
            prev.map(option => (option.id === id ? { ...option, value: newValue } : option))
        )
    }
    const removeOption = (id) => {
        setOptions(prev => prev.filter(option => option.id !== id))
    }

    const trimQuestion = () => setQuestion(question.trim())
    const trimOption = (id) => {
        setOptions(prev =>
            prev.map(opt =>
                opt.id === id ? { ...opt, value: opt.value.trim() } : opt
            )
        )
    }

    const submitPoll = async () => {
        if (!userId) {
            alert("You must be logged in to create a poll!")
            return
        }

        if (!question.trim()) {
            alert("Question is required!")
            return
        }

        const checkOptions = options.filter(opt => opt.value.trim() !== "")
        if (checkOptions.length === 0) {
            alert("At least one option is required!")
            return
        }

        if (!publishedAt || !validUntil) {
            alert("Publish date and valid until date are requried!")
            return
        }

        const poll = {
            question: question,
            options: options
                .filter(opt => opt.value.trim() !== "")
                .map(opt => ({ id: opt.id, option: opt.value })),
            publishedAt: new Date(publishedAt).toISOString(),
            validUntil: new Date(validUntil).toISOString(),
            userId: userId
        }

        try {
            const response = await fetch(`${BASE_API_URL}/polls`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(poll)
            })

            if (!response.ok) throw new Error(`Response status: ${response.status}`)

            const result = await response.json()
            console.log(result)

            const pollId = JSON.parse(JSON.stringify(result))
            setPollId(pollId.id)

            if (onSuccess) onSuccess()
            alert("Poll successfully created!")

            await fetchPolls()

        } catch (error) {
            console.error("Error creating poll:", error.message)
        }
    }
    return (
        <div className="container-CreatePoll">
            <div className="header-CreatePoll">
                <header className="text">Creating A Poll</header>
                <div className="underline-CreatePoll"></div>
            </div>

            <div className="inputs-CreatePoll">
                <div className="input-CreatePoll">
                    <label className="question-label-CreatePoll"> Enter Question: </label>
                    <input type="text" value={question}
                        onChange={(event) => setQuestion(event.target.value)} className="Question"
                        onBlur={trimQuestion} placeholder='Enter question here'
                    />
                </div>

                <div className="options-CreatePoll">
                    <div className="options-label-CreatePoll">Enter Your Options Below:</div>
                    {options.map((option, index) => (
                        <div key={option.id} className="option-CreatePoll">
                            <input className="input-CreatePoll"
                                type="text" value={option.value}
                                onChange={(event) => handleChange(option.id, event.target.value)}
                                onBlur={() => trimOption(option.id)} placeholder={`Enter option ${index + 1} here`}
                            />
                            <button className="remove-button-CreatePoll" onClick={() => removeOption(option.id)} disabled={options.length === 1}>
                                Remove Option
                            </button>
                        </div>
                    ))}

                    <button className="add-button-CreatePoll" onClick={addOption}>
                        Click To Add Option
                    </button>
                </div>

                <div className="publishedAt-CreatePoll">
                    <label className="publish-label"> Publish Date: </label>
                    <input type="datetime-local" value={publishedAt} onChange={(event) => setPublishedAt(event.target.value)} required />
                </div>

                <div className="validUntil-CreatePoll">
                    <label className="valid-label"> Valid Until: </label>
                    <input type="datetime-local" value={validUntil} onChange={(event) => setValidUntil(event.target.value)} required />
                </div>
            </div>

            <div className="submit-container-CreatePoll">
                <button className="submit" onClick={() => submitPoll()}>Submit Poll</button>
            </div>
        </div>
    )
}