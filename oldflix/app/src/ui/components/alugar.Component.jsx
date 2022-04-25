export function AlugarFilme({arrayDeFilmes,ReturnObject,Alugar}) {
    

    function handleChange(Event) {
        ReturnObject(Event)
    }

    return (
        <div className="secondDiv" onChange={handleChange}>
                <label className="labelDiv">Lista De Filmes Disponíveis</label>
                    <select className="selectButton" name="id" >
                        {arrayDeFilmes.map((filme) =>   
                                           
                        filme.disponivel && <option value={filme.id} > {filme.titulo} - {filme.categoria}</option>
                 
                        )}
                        
                    </select>
                <label className="labelDiv" >Escreva seu nome</label>

                <input className="input" name="nome" type="text" />  
        </div>
    )
}