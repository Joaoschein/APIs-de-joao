import { useNavigate } from "react-router-dom";

export function MenuInicial () {

    const navigate = useNavigate();

    function NavegarPassageiro() {
        navigate("/passageiros")
    }

    function NavegarMotorista() {
        navigate("/motoristas")
    }

    return (
        <div>
            <h1 className="nomeMotorista">Voce é um passageiro ou um motorista?</h1>
            <div className="passageiroDiv">
            
                <button className="botaoPricipal" onClick={NavegarPassageiro}> Passageiro</button>
                <button className="botaoPricipal" onClick={NavegarMotorista}> Motorista</button>

            </div>
        </div>
    )
}