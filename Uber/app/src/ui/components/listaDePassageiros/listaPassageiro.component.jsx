import './listaPassageiro.css'
export function ListarPassageiros({passageiros, RetornaPassageiro}) {

    function handleChange(Event) {
        RetornaPassageiro(Event)
    }

    return (
        <div className='listaPassageiroDiv'>
            {passageiros.map((passageiro) =>
            <button className="botaoPricipal" name="id" value={passageiro.id} onClick={handleChange}>
                Nome: {passageiro.nome}
            </button>
            
            )}
        </div>
    )
}