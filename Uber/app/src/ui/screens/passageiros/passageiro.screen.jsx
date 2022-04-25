import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useGlobalPassageiro } from "../../../context/passageiro.context";
import { useCaronaApi } from "../../../hooks/carona/use-carona-api.hook";
import { ListarPassageiros, MotoristaSelecionado, PontosCardeais} from "../../components";

import './passageiros.css'

export function PassageirosScreen() {

    const [globalPassageiro, setGlobalPassageiro] = useGlobalPassageiro()
    const navigate = useNavigate();
    const passageirosApi = useCaronaApi();
    const [passageiros, setPassageiros] = useState([]);
    const [solicitacao, setsolicitacao] = useState({});
    const [solicitacaoResponse, setsolicitacaoResponse] = useState({});


    useEffect(() => {
        async function getListaDePassageiros() {
            const passageiroResponse = await passageirosApi.ListarPassageiros();
            setPassageiros(passageiroResponse);
        }

        getListaDePassageiros();
    }, [passageirosApi])

    function RetornaInformacoesDaSolicitacao(Event) {
        Event.preventDefault()
        setGlobalPassageiro(Event.target.value)
        console.log(globalPassageiro)

    }

    function Voltar() {
        navigate("/");
    }

    function NavegarParaPassageiroSelecionado() {
        navigate("/passageiro_selecionado")
    }

    return (    
        <div>

            <div className="passageiroDiv">
                <h1 className="listarTitulo">Lista de Passageiros disponiveis</h1>
                <ListarPassageiros
                passageiros = {passageiros}
                RetornaPassageiro = {RetornaInformacoesDaSolicitacao}
                />

                <button onClick={NavegarParaPassageiroSelecionado} className="botaoSelect"> Selecionar Passageiro</button>
                <button className="botaoSelect" onClick={Voltar}> Voltar </button>

            </div>

        </div>
    )
}