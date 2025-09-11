import React from 'react'
import { useState } from 'react'

import '../Styling/CreatePoll.css'

export default function CreatePoll(props) {
    const [question, setQuestion] = useState("")
    const [options, setOptions] = useState([{id: 0, value: ""}])
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
    return (
        <div className="container">
            <div className="header">
                <div className="text">Create a Poll</div>
                <div className="underline"></div>
            </div>

            <div className="inputs">
                <div className="input">
                    <label className="input-label"> Enter question: 
                    <input type="text" value={question} 
                           onChange={(event) => setQuestion(event.target.value)} className="Question" 
                           placeholder='Enter question here'       
                    />
                    </label>
                </div>
                <div className="options">
                    {options.map((option, index) => (
                    <div key={option.id} className="option">
                        <input 
                            type="text" value={option.value}
                            onChange={(event) => handleChange(option.id, event.target.value)}
                            placeholder={`Enter option ${index +1} here`}
                        />
                        <button className="button" onClick={() => removeOption(option.id)} disabled={options.length === 1}>
                            Remove option
                        </button>
                    </div>
                    ))}

                    <button className="button" onClick={addOption}>
                        Click to add option
                    </button>
                </div>
            </div>
        </div>
    )
}