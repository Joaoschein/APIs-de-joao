package br.com.cwi.crescer.oldflix;

public enum Categoria {
    BRONZE(5),
    PRATA(3),
    OURO(2);

    private int descricao;

    Categoria(int descricao) {
        this.descricao=descricao;
    }

    public int getDescricao() {return descricao; }
}

