import CreateUser from "./CreateUser"
import '../Styling/CreateUserModal.css'

export default function CreateUserModal({show, onClose}) {
    if (!show) return null

    return (
        <div className="modal-overlay" onClick={onClose}>
            <div className="modal-content" onClick={(event) => event.stopPropagation()}>
                <button className="modal-close" onClick={onClose}>X</button>
                <CreateUser onSuccess={onClose}/>
            </div>
        </div>
    )
}