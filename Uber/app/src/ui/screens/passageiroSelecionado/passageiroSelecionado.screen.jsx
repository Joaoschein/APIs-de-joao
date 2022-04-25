import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useGlobalPassageiro } from "../../../context/passageiro.context";
import { useCaronaApi } from "../../../hooks/carona/use-carona-api.hook";
import { PontosCardeais } from "../../components";
import './passageiroSelecionado.css'

export function PassageiroSelecionado() {

    const [globalPassageiro, setGlobalPassageiro] = useGlobalPassageiro()
    const navigate = useNavigate();
    const passageirosApi = useCaronaApi();
    const [passageiro, setPassageiro] = useState({})
    const [solicitacao, setsolicitacao] = useState({});
    const [solicitacaoResponse, setsolicitacaoResponse] = useState({});
    const [mensagem, setMensagem] = useState(null);
    const [valorDeposito, setValorDeposito] = useState();

    useEffect(() => {
        async function getPassageiroPorId() {
            const passageiroResponse = await passageirosApi.getPassageiroPorID(globalPassageiro);
            setPassageiro(passageiroResponse);
        }

        getPassageiroPorId();
    }, [passageirosApi]) 


    function RetornaInformacoesDaSolicitacao(Event) {
        Event.preventDefault()
        setsolicitacao({...solicitacao, [Event.target.name]:Event.target.value})
        console.log(solicitacao)

    }

    async function Solicitar() {
        try {
        const solicitacaoResponse = await passageirosApi.SolicitarCorrida(
            solicitacao.id,
            solicitacao.pontoInicialX,
            solicitacao.pontoInicialY,
            solicitacao.pontoFinalX,
            solicitacao.pontoFinalY )
            setsolicitacaoResponse(solicitacaoResponse)
            setMensagem("solicitacao feita com Sucesso")
        } catch(error) {
            setMensagem(error.response?.data?.message)
        }
    }

    function SolicitarCorrida() {
        setsolicitacao({...solicitacao, ["id"]:globalPassageiro})
    }

    function AbrirCampoParaSetarDeposito() {
        setValorDeposito("salve")
    }

    function setDeposito(Event) {
        
        setValorDeposito(Event.target.value)
        console.log(valorDeposito);
    }

    async function Depositar() {
        try {
            await passageirosApi.DepositoPassageiro(valorDeposito, globalPassageiro);
            setMensagem("deposito feito com sucesso")
        }catch(error) {
            setMensagem(error.response?.data?.message)
        }
    }

    async function InativarConta() {
            await passageirosApi.InativarPassageiro(globalPassageiro);
            setMensagem("passageiro inativado")
            console.log(passageiro)

    }

    function Voltar() {
        navigate("/passageiros")
    }


    return (

        <div>
            <h1 className="nomeMotorista" >Informações do Passageiro</h1>
            <div className="passageiroSelecionadoDiv">
                <div>
                    <div className="passageiroDiv">
                        <h1 className="nomeMotorista">Nome: {passageiro.nome}</h1>
                        <p className="infoMotorista">Credito: {passageiro.creditos} </p>
                        <p className="infoMotorista">CPF: {passageiro.cpf} </p>
                        <p className="infoMotorista">Nota: {passageiro.avaliacao} </p>
                        <p className="infoMotorista">Data de Nascimento: {passageiro.dataNascimento}</p>
                        
                    </div>


                    
                    <div>
                        <button className="botaoSelect" onClick={InativarConta}> Inativar Conta </button>
                        <button className="botaoSelect" onClick={Voltar}> Voltar </button>
                    </div>

                </div>

                

                <div>
                    <button className="botaoSelect" onClick={SolicitarCorrida}> Solicitar Corrida </button>

                    {solicitacao.id && <PontosCardeais
                        RetornaPontos={RetornaInformacoesDaSolicitacao}
                    />}
                    {solicitacao.id && <button className="botaoSelect" onClick={Solicitar}> Confirmar Solicitação</button>}
                    {mensagem && <p className="infoMotorista">{mensagem}</p>}

                </div>

                <div>
                    <button onClick={AbrirCampoParaSetarDeposito} className="botaoSelect"> Depositar Dinheiro </button>
                    {valorDeposito && <div className="passageiroDiv">
                        <label className='labelPontos' > Digite um valor para depositar</label>
                        <input onChange={setDeposito} type="text" /> 
                        <button onClick={Depositar} className="botaoPricipal"> Confirmar Deposito </button>
                    </div>} 
                </div>
                
            </div>
        </div>
    )
}