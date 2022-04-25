import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import { useDelharFilmeApi } from "../../../../hooks/detalhar/use-detalharFilme.hook"
import { DetalhesComponent } from "../../../components/detalhes.Component"

export function DetalharScreen() {

    const [formulario, setFormulario] = useState({})
    const [filme, setFilme] = useState({})
    const detalharFilme = useDelharFilmeApi()
    const navigate = useNavigate();

        useEffect(() => {
        async function Detalhar() {
            const filmeResponse = await detalharFilme.getDetalhesFilme(formulario.id);
            setFilme(filmeResponse);
        }

        Detalhar();
    }, [detalharFilme])

    function SalvaInformacao(Event) {

        Event.preventDefault()
        setFormulario({...formulario, [Event.target.name]:Event.target.value})
        console.log(formulario)

    }
    async function teste() {
        const filmeResponse = await detalharFilme.getDetalhesFilme(formulario.id);
        setFilme(filmeResponse);
        console.log(filme);
    }

    function Voltar() {
        navigate("/")
    }
    return (
        
        <div className="mainDiv">
            <label className="labelDiv"> Qual o id do filme que voce deseja persquisar? </label>

            <div onChange={SalvaInformacao}>
                <input className="input" name="id" type="text" />
            </div>
                <button className="button" onClick={teste}>detalhes</button>
            <div>
                <DetalhesComponent filme = {filme}/>
            </div>
            <button className="button" onClick={Voltar}> Voltar </button>
        </div>
    )
}           
           