import React from 'react'
import { useState, useContext } from 'react'

import '../Styling/CreateUser.css'
import AppContext from '../Contexts/AppContext'

export default function CreateUser() {
    const [username, setUserName] = useState("")
    const [email, setEmail] = useState("")

    const { setUserId } = useContext(AppContext)
    
    const BASE_API_URL = 'http://localhost:8080';

    const submitUser = async (action) => {
        if (!username || !email) {
            console.error("Username and email required")
            return;
        }
        const user = {
            username: username,
            email: email
        }  
        
        const url = action === "login" ? `${BASE_API_URL}/users/login` : `${BASE_API_URL}/users`

        try {
            const response = await fetch(url, {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify(user)
            })

            if (!response.ok) {
                throw new Error(`Response status: ${response.status}`)
            }

            const result = await response.json()
            console.log(result)

            const userId = JSON.parse(JSON.stringify(result))
            setUserId(userId.id)

        } catch (error) {
            console.error("Error creating user:", error.message)
        }
    }
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
                <button className="submit" onClick={() => submitUser("create")}>Sign Up</button>
                <button className="submit" onClick={() => submitUser("login")}>Login</button>
            </div>
        </div>
        )
    }