import { useState, useContext, useEffect } from 'react'

import '../Styling/CreateUser.css'
import AppContext from '../Contexts/AppContext'

export function CreateUser() {
    const [username, setUserName] = useState("")
    const [email, setEmail] = useState("")

    const { setUserId } = useContext(AppContext)

    const BASE_API_URL = 'http://localhost:8080'

    const trimUsername = () => setUserName(username.trim())
    const trimEmail = () => setEmail(email.trim())

    const submitUser = async (action) => {
        if (!username || !email) {
            alert("Username and email required!")
            return
        }
        const user = {
            username: username,
            email: email
        }

        const url = action === "login" ? `${BASE_API_URL}/users/login` : `${BASE_API_URL}/users`

        try {
            const response = await fetch(url, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(user)
            })

            if (!response.ok) throw new Error(`Response status: ${response.status}`)

            const result = await response.json()
            console.log(result)

            const userId = JSON.parse(JSON.stringify(result))
            setUserId(userId.id)

        } catch (error) {
            console.error("Error creating user:", error.message)
        }
    }
    return (
        <div className="container-CreateUser">
            <div className="header-CreateUser">
                <div className="text-CreateUser"> Sign In </div>
                <div className="underline-CreateUser"></div>
            </div>
            <div className="inputs-CreateUser">
                <div className="input-CreateUser">
                    <label className="input-label-CreateUser"> Username: </label>
                    <input type="text" value={username} onChange={(event) => setUserName(event.target.value)}
                        className="Name-CreateUser" onBlur={trimUsername} placeholder='Enter your username here' />
                </div>
                <div className="input-CreateUser">
                    <label className="input-label-CreateUser"> Email: </label>
                    <input type="email" value={email} onChange={(event) => setEmail(event.target.value)}
                        className='Email-CreateUser' onBlur={trimEmail} placeholder='Enter your email here' />
                </div>
            </div>
            <div className="submit-container-CreateUser">
                <button className="submit-CreateUser" onClick={() => submitUser("create")}>Sign Up</button>
                <button className="submit-CreateUser" onClick={() => submitUser("login")}>Login</button>
            </div>
        </div>
    )
}

export default function showUser() {
    const [showComponent, setShowComponent] = useState(false)

    const handleClick = () => {
        setShowComponent(!showComponent)
    };

    useEffect(() => {
    }, [showComponent])

    return (
        <div className="show-login">
            <button className="show-login-button" onClick={handleClick}>Login</button>
            {!showComponent || <CreateUser />}
        </div>
    );
}