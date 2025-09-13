import CreatePoll from './CreatePoll'
import '../Styling/CreatePollModal.css'

export default function CreatePollModal({show, onClose, onSuccess}) {
    if (!show) return null

    return (
        <div className="modal-overlay" onClick={onClose}>
            <div className="modal-content" onClick={(event) => event.stopPropagation()}>
                <button className="modal-close" onClick={onClose}>X</button>
                <CreatePoll onSuccess={onSuccess}/>
            </div>
        </div>
    )
}