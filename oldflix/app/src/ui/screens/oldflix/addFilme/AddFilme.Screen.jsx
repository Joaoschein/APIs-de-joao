import { useState } from "react"
import { useNavigate } from "react-router-dom";
import { useAdicionarFilmeApi } from "../../../../hooks/adicionar/use-adicionarFilme.hook"

export function AddFilme() {

    const [formulario, setFormulario] = useState({})
    const adicionarFilme = useAdicionarFilmeApi()
    const navigate = useNavigate();

    function SalvaInformacao(Event) {

        Event.preventDefault()
        setFormulario({...formulario, [Event.target.name]:Event.target.value})
        console.log(formulario)

    }

    async function Adicionar() {
        await adicionarFilme.Adicionar(formulario.titulo, formulario.descricao, formulario.categoria)
    }

    function Voltar() {
        navigate("/")
    }

    return (

        <div className="mainDiv" onChange={SalvaInformacao}>
            <label className="labelDiv" > Titulo do filme </label>
            <input className="input" name="titulo" type="text" />

            <label className="labelDiv"> Descricao </label>
            <input className="input" name="descricao" type="text" />

            <label className="labelDiv"> Categoria</label>
            <input className="input" name="categoria" type="text" />
            
            <button className="button" onClick={Adicionar}> Adicionar </button>
            <button className="button" onClick={Voltar}> Voltar </button>
        </div>
    )
}