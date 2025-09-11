import React from 'react'
import { useState, useContext } from 'react'

import '../Styling/CreatePoll.css'
import AppContext from '../Contexts/AppContext'

export default function CreatePoll() {
    const [question, setQuestion] = useState("")
    const [options, setOptions] = useState([{id: 0, value: ""}])
    const [publishedAt, setPublishedAt] = useState("")
    const [validUntil, setValidUntil] = useState("")
    const { userId } = useContext(AppContext) 

    const BASE_API_URL = 'http://localhost:8080';

    const addOption = () => {
        setOptions(prev => [
            ...prev,{ id: prev.length > 0 ? prev[prev.length - 1].id + 1 : 0, value: ""}
        ])
    }
    const handleChange = (id, newValue) => {
        setOptions(prev => 
            prev.map(option => (option.id === id ? {...option, value: newValue } : option))
        )
    }
    const removeOption = (id) => {
        setOptions(prev => prev.filter(option => option.id !== id))
    }

    const submitPoll = async () => {
        const poll = {
            question: question,
            options: options
                .filter(opt => opt.value.trim() !== "")
                .map(opt => ({ id: opt.id, option: opt.value})),
            publishedAt: new Date(publishedAt).toISOString(),
            validUntil: new Date(validUntil).toISOString(),
            userId: userId
        }  
        
        try {
            const response = await fetch(`${BASE_API_URL}/polls`, {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify(poll)
            })

            if (!response.ok) {
                throw new Error(`Response status: ${response.status}`)
            }

            const result = await response.json()
            console.log(result)
        } catch (error) {
            console.error("Error creating poll:", error.message)
            console.log(userId)
        }
    }
    return (
        <div className="container">
            <div className="header">
                <header className="text">Creating A Poll</header>
                <div className="underline"></div>
            </div>

            <div className="inputs">
                <div className="input">
                    <label className="question-label"> Enter Question: </label>
                    <input type="text" value={question} 
                           onChange={(event) => setQuestion(event.target.value)} className="Question" 
                           placeholder='Enter question here'       
                    />
                </div>

                <div className="options">
                    <div className="options-label">Enter Your Options Below:</div>
                    {options.map((option, index) => (
                    <div key={option.id} className="option">
                        <input 
                            type="text" value={option.value}
                            onChange={(event) => handleChange(option.id, event.target.value)}
                            placeholder={`Enter option ${index +1} here`}
                        />
                        <button className="remove-button" onClick={() => removeOption(option.id)} disabled={options.length === 1}>
                            Remove Option
                        </button>
                    </div>
                    ))}

                    <button className="add-button" onClick={addOption}>
                        Click To Add Option
                    </button>
                </div>

                <div className="publishedAt">
                    <label className="publish-label"> Publish Date: </label>
                    <input type="datetime-local" value={publishedAt} onChange={(event) => setPublishedAt(event.target.value)} /> 
                </div>
                
                <div className="validUntil">
                    <label className="valid-label"> Valid Until: </label>
                    <input type="datetime-local" value={validUntil} onChange={(event) => setValidUntil(event.target.value)} /> 
                </div>
            </div>

            <div className='submit-container'>
                <button className="submit" onClick={() => submitPoll()}>Make Poll</button>
            </div>
        </div>
    )
}