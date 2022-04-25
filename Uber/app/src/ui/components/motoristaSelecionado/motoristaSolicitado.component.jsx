import './motoristaSelecionado.css'

export function MotoristaSelecionado({solicitacaoResponse}) {

    return (

        <div className='motoristaDiv'>
            <div className='divInfo'>

                <h1 className='nomeMotorista'>Nome do motorista: {solicitacaoResponse.nomeMotorista}</h1>
                <p className='infoMotorista'>Tempo Estimado de chegada: {solicitacaoResponse.tempoEstimadoChegada} minutos</p>
                <p className='infoMotorista'>Informaçoes do veiculo:</p>
                <p className='infoMotorista'>modelo: {solicitacaoResponse.veiculo.modelo}</p>
                <p className='infoMotorista'>placa: {solicitacaoResponse.veiculo.placa}</p>
                <p className='infoMotorista'>cor: {solicitacaoResponse.veiculo.cor}</p>

            </div> 

            <div>

                <img className='carroImg' src={solicitacaoResponse.veiculo.foto}/>

            </div>
            

        </div>
    )
}