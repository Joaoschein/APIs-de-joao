import './pontosCardeais.css'

export function PontosCardeais({RetornaPontos}) {

    function handleChange(Event) {
        RetornaPontos(Event)
    }
    return (

        <div className='mainPontosDiv' onChange={handleChange}>

            <div className='pontoDiv'>
                <div className='ponto'>
                    <label className='labelPontos' >Ponto Inicial X</label>
                    <input type="text" name="pontoInicialX" />
                </div>

                <div className='ponto'>
                    <label className='labelPontos' >Ponto Inicial Y</label>
                    <input type="text" name="pontoInicialY" />
                </div>
                
            </div>

            <div className='pontoDiv'>
                <div className='ponto'>
                    <label className='labelPontos' >Ponto Final X</label>
                    <input type="text" name="pontoFinalX" />  
                </div>
                
                <div className='ponto'> 
                    <label className='labelPontos'>Ponto Final Y</label>
                    <input type="text" name="pontoFinalY" />
                </div>

            </div>



        </div>
    )
}