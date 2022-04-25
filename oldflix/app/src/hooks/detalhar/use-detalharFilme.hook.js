import { useMemo } from "react";
import { BASE_OLDFLIX_API_URL } from "../../constant";
import { useHttp } from "../base/use-http.hook";

export function useDelharFilmeApi() {

    const httpInstance = useHttp(BASE_OLDFLIX_API_URL)

    async function getDetalhesFilme(id) {
        const response = await httpInstance.get(`/filmes/${id}`);
    
        return response;
      }

      return useMemo(
          () => ({
            getDetalhesFilme,
              // eslint-disable-next-line react-hooks/exhaustive-deps
          }),
          []
      )
}