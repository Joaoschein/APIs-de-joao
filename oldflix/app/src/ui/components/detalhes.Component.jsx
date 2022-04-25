export function DetalhesComponent({filme}) {

    return (
        <div>
                {filme && <p>titulo: {filme.titulo}</p>}
                {filme && <p>descricao: {filme.descricao}</p>}
                {filme && <p>disponivel: {filme.disponivel}</p>}
                {filme && <p>categoria: {filme.categoria}</p>}
                {filme && <p>responsavel: {filme.responsavel}</p>}
                {filme && <p>Data De Retirada: {filme.dataDeRetirada}</p>}
                {filme && <p>Situacao: {filme.situcao}</p>}
                {filme && <p>Data de Entrega: {filme.dataDeEntrega}</p>}
        </div>
    )

}