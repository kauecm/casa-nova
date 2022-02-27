
import { Presentes } from "types/casa";
import './styles.css';


type Props ={
    presente :Presentes;
}


function PresenteCard( {presente} : Props ){
    return (
        <div>
        <img className="dsmovie-movie-card-image" src={`img/${presente.mne}.jpg`} alt={presente.nome} />
        <div className="dsmovie-card-bottom-container">
            <h3>{presente.nome}</h3>

                <button className="btn btn-primary dsmovie-btn">Escolher</button>
            

        </div>
    </div>
    );
}

export default PresenteCard;