import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAlugarFilmeApi } from "../../../../hooks/alugar/use-alugarFilmes.hook";
import { useFilmesApi } from "../../../../hooks/listarFilmes/use-filmes.get.hook";
import { AlugarFilme } from "../../../components/alugar.Component";
import './alugar.css'
export function AlugarScreen() {

    const filmesApi = useFilmesApi();
    const alugarFilme = useAlugarFilmeApi()
    const [filmes, setFilmes] = useState([])
    const [formulario, setFormulario] = useState({})
    const navigate = useNavigate();

    useEffect(() => {
        async function getListaDeFilmes() {
            const filmesResponse = await filmesApi.getListaDeFilmes();
            setFilmes(filmesResponse);
        }

        getListaDeFilmes();
    }, [filmesApi])

    function SalvaInformacao(Event) {

        Event.preventDefault()
        setFormulario({...formulario, [Event.target.name]:Event.target.value})
        console.log(formulario)

    }

    async function Alugar() {
        await alugarFilme.Alugar(formulario.nome, formulario.id)
    }

    function Voltar() {
        navigate("/")
    }
    return (

        <div className="mainDiv">
             <AlugarFilme
             arrayDeFilmes = {filmes}
             ReturnObject = {SalvaInformacao}
             />
             <button className="button" onClick={Alugar}> Alugar </button>
             <button className="button" onClick={Voltar}> Voltar </button>

        </div>
    )
}