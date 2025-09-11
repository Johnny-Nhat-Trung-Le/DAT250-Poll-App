import React from 'react'
import '../Styling/CreateUserExample.css'

import user_icon from '../../public/Images/larry.jpg'
import email_icon from '../../public/Images/cattongueoutboi.jpg'

export default function CreateUserExample() {
    return (
        <div className="container">
            <div className="header">
                <div className="text">Sign In</div>
                <div className="underline"></div>
            </div>
            <div className="inputs">
                <div className="input">
                    <img src={user_icon} alt="" />
                    <input type="text" placeholder="Name" />
                </div>
                <div className="input">
                    <img src={email_icon} alt="" />
                    <input type="email" placeholder="Email" />
                </div>
            </div>
            <div className="submit-container">
                <div className="submit">Sign Up</div>
                <div className="submit">Login</div>
            </div>
        </div>

        )
    }