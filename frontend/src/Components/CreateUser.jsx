import React from 'react'
import { useState } from 'react'

import '../Styling/CreateUser.css'

export default function CreateUser(props) {
    const [username, setUserName] = useState("")
    const [email, setEmail] = useState("")

    return (
        <div className="container">
            <div className="header">
                <div className="text"> Sign In </div>
                <div className="underline"></div>
            </div>
            <div className="inputs">
                <div className="input">
                    <label className='input-label'> Username: </label>
                    <input type="text" value={username} onChange={(event) => setUserName(event.target.value)}
                           className="Name" placeholder='Enter your username here' required/>
                </div>
                <div className="input">
                    <label className='input-label'> Email: </label>
                    <input type="email" value={email} onChange={(event) => setEmail(event.target.value)}
                           className='Email' placeholder='Enter your email here' required/>
                </div>
            </div>
            <div className='submit-container'>
                <button className="submit">Sign Up</button>3
                <button className="submit">Login</button>
            </div>
        </div>
        )
    }