import { useNavigate } from "react-router-dom";

export function MenuScreen() {

    const navigate = useNavigate();

    function Alugar () {
        navigate("/alugar")
    }

    function Devolver () {
        navigate("/devolver")
    }

    function Adicionar () {
        navigate("/adicionar")
    }

    function Detalhes () {
        navigate("/detalhar")
    }
    

    return (

        <div className="mainDiv">

            <div>
                <button className="button" onClick={Alugar}> Alugar </button>
                <button className="button" onClick={Devolver}> Devolver </button>
            </div>
           <div>
                <button className="button" onClick={Adicionar}> Adicionar </button>
                <button className="button" onClick={Detalhes}> Ver Detalhes </button> 
            </div> 
            

        </div>
    )
}