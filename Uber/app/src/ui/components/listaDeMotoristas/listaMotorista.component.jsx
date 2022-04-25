import './listaMotorista.css'

export function ListarMotoristas({motoristas, RetornaMotorista}) {

    function handleChange(Event) {
        RetornaMotorista(Event)
    }
    return (
        <div className="motoristasDiv">

            {motoristas.map((motorista) =>
                <button className="botaoPricipal" name="id" value={motorista.id} onClick={handleChange}>
                    Nome: {motorista.nome}
                </button>
            
            )}
        </div>
    )
}