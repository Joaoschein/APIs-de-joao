import { BASE_OLDFLIX_API_URL } from "../../constant";
import { useHttp } from "../base/use-http.hook";

import { useMemo } from "react";

export function useAdicionarFilmeApi() {

  const httpInstance = useHttp(BASE_OLDFLIX_API_URL);

  async function Adicionar(titulo, descricao, categoria) {
    const response = await httpInstance.post("/filmes", {
      titulo,
      descricao,
      categoria,
    });

    return response;
  }

  return useMemo(
    () => ({
      Adicionar,
      // eslint-disable-next-line react-hooks/exhaustive-deps
    }),
    []
  );
}