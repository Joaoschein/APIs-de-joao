import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useGlobalMotorista } from "../../../context/motorista.context";
import { useCaronaApi } from "../../../hooks/carona/use-carona-api.hook";
import { ListarMotoristas } from "../../components/listaDeMotoristas/listaMotorista.component";

export function MotoristasScreen() {

    const navigate = useNavigate();
    const [globalMotorista, setGlobalMotorista] = useGlobalMotorista()
    const motoristasApi = useCaronaApi();
    const [motoristas, setMotoristas] = useState([]);

    useEffect(() => {
        async function getListaDeMotoristas() {
            const passageiroResponse = await motoristasApi.ListarMotoristas();
            setMotoristas(passageiroResponse);
        }

        getListaDeMotoristas();
    }, [motoristasApi])

    function RetornaMotorista(Event) {
        Event.preventDefault()
        setGlobalMotorista(Event.target.value)
        console.log(globalMotorista);
        
    }

    function NavegarParaMotoristaSelecionado() {
        navigate("/motorista_selecionado")
    }

    function Voltar() {
        navigate("/")
    }

    return (
    
        <div>
            <div>
                <ListarMotoristas
                motoristas={motoristas}
                RetornaMotorista = {RetornaMotorista}
                />
                <button onClick={NavegarParaMotoristaSelecionado} className="botaoSelect"> selecionar Motorista </button>
                <button className="botaoSelect" onClick={Voltar}> Voltar </button>
            </div>

        </div>
        
    )
}