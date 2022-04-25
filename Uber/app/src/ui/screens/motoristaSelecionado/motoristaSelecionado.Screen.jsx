import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useGlobalMotorista } from "../../../context/motorista.context";
import { useCaronaApi } from "../../../hooks/carona/use-carona-api.hook";
import './motoristaSelecionado.css'

const SECONDS_IN_A_MINUTE = 60
const NINE = 9

export function MotoristaSelecionado() {

    const [globalMotorista, setGlobalMotorista] = useGlobalMotorista()
    const navigate = useNavigate();
    const motoristasApi = useCaronaApi();
    const [motorista, setMotorista] = useState({})
    const [corrida, setCorrida] = useState({})
    const [iniciarResponse, setIniciarResponse] = useState(null)
    const [error, setError] = useState(null)
    const [notas, setNotas] = useState({})
    const [validar, setValidar] = useState(null)
    const [finalizar, setFinalizar] = useState()
    const [saque, SetSaque] = useState()
    const [saqueMensagem, setSaqueMensagem] = useState(null)

    useEffect(() => {
        async function getMotoristaPorId() {
            const motoristaResponse = await motoristasApi.getMotoristaPorID(globalMotorista);
            setMotorista(motoristaResponse);
            setCorrida(motoristaResponse.corrida)
            
        }

        getMotoristaPorId();
    }, [motoristasApi]) 

    

    function Teste() {
        console.log(motorista);
    }

    function formatMSS(seconds) {
        return (seconds - (seconds %= SECONDS_IN_A_MINUTE)) / SECONDS_IN_A_MINUTE + (NINE < seconds ? ':' : ':0') + seconds
    }

    async function IniciarCorrida() {
        try{
            const response = await motoristasApi.IniciarCorrida(corrida.id)
            setIniciarResponse(response)  
        } catch(error){
            setError(error.response?.data?.message)
        }
        
    }

    async function FinalizarCorrida() {
        try {
            await motoristasApi.FinalizarCorrida(corrida.id)
            setFinalizar("Corrida finalizada com sucesso")
            navigate("/motoristas")

        } catch(error) {

            setFinalizar(error.response?.data?.message)
        }
           
    }

    async function Avaliar() {
        await motoristasApi.AvaliarMotorista(notas.motorista, corrida.id)
        await motoristasApi.AvaliarPassageiro(notas.passageiro, corrida.id)
    }

    function AbrirCampoParaAvaliar() {
        setValidar("o")
    }

    function SalvarValorDaNota(Event) {
        Event.preventDefault()
        setNotas({...notas, [Event.target.name]:Event.target.value})
        console.log(notas)
    }

    function Voltar() {
        navigate("/motoristas")
    }

    function SetarValorDeSaque(Event) {
        SetSaque(Event.target.value)
    }

    async function Saque() {
        try{
            await motoristasApi.SaqueMotorista(saque, motorista.id)
            setSaqueMensagem("saque feito com sucesso")
        }catch(error){
            setSaqueMensagem(error.response?.data?.message)
        }
        
    }


    return(

        <div className="motoristaSelecionadoDiv">
            <div>

                <div className="passageiroDiv">
                    <h1 className="nomeMotorista">Nome: {motorista.nome}</h1>
                    <p className="infoMotorista">Nota: {motorista.avaliacao}</p>
                    <p className="infoMotorista">CPF: {motorista.cpf}</p>
                    <p className="infoMotorista">Situacao: {motorista.situacao}</p>
                </div>

                <div className="passageiroDiv">
                    <h1 className="nomeMotorista">Corrida</h1>
                    <p className="infoMotorista">distancia: {corrida.distancia?.toFixed(2)}KM</p>
                    <p className="infoMotorista">Passageiro: {corrida.nomePassageiro}</p>
                    <p className="infoMotorista">Situacao: {corrida.situacao}</p>
                    
                </div>
                <button className="botaoSelect" onClick={Voltar}> Voltar </button>

                <div>
                    <button className="botaoSelect" onClick={Saque}> Sacar Dinheiro</button>
                    <input type="text" name="valorDeSaque" onChange={SetarValorDeSaque} />
                    {saqueMensagem && <p className="infoMotorista">{saqueMensagem}</p>}
                </div>

            </div>

            <div>
                <button onClick={IniciarCorrida} className="botaoSelect"> Iniciar Corrida </button>

                {iniciarResponse && <div className="passageiroDiv">

                    <p className="infoMotorista">Tempo estimado para chegar ao destino: {formatMSS(iniciarResponse.tempoEstimado)}</p>
                    <p className="infoMotorista">Valor da corrida: {iniciarResponse.valorEstimado}R$ </p> 
                    <p className="infoMotorista">{error}</p> 
                </div> }
                

                <button onClick={AbrirCampoParaAvaliar} className="botaoSelect"> Finalizar Corrida </button>
                {validar && <div onChange={SalvarValorDaNota} className="passageiroDiv">

                    <label className='labelPontos' >Nota para o passageiro</label>
                    <input type="text" name="passageiro" />

                    <label className='labelPontos' >Nota para o motorista</label>
                    <input type="text" name="motorista" />

                    <button onClick={Avaliar} className="botaoPricipal"> Avaliar Corrida </button>
                    <button onClick={FinalizarCorrida} className="botaoPricipal"> Finalizar </button>
                    {finalizar && <p className="infoMotorista">{finalizar}</p>}
                    </div>}
                
            </div>
        </div>

    )
}