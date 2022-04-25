import { useState } from "react"
import { useNavigate } from "react-router-dom";
import { useDevolverFilmeApi } from "../../../../hooks/devolver/use-devolverFilmes.hook"

export function DevolverScreen() {

    const devolverFilme = useDevolverFilmeApi()
    const [formulario, setFormulario] = useState({})
    const navigate = useNavigate();

    function SalvaInformacao(Event) {

        Event.preventDefault()
        setFormulario({...formulario, [Event.target.name]:Event.target.value})
        console.log(formulario)

    }

    async function Devolver() {
        await devolverFilme.Devolver(formulario.nome, formulario.id)
    }

    function Voltar() {
        navigate("/")
    }

    return (
        <div className="mainDiv" onChange={SalvaInformacao}>

            <label className="labelDiv" htmlFor=""> Digite seu nome </label>
            <input className="input" name="nome" type="text" />

            <label className="labelDiv" htmlFor="">Digite o id do filme que voce deseja devolver</label>
            <input className="input" name="id" type="text" />
            
            <button className="button" onClick={Devolver}>Devolver</button>
            <button className="button" onClick={Voltar}> Voltar </button>
        </div>
    )

}