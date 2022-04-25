import { useMemo } from "react";
import { BASE_OLDFLIX_API_URL } from "../../constant";
import { useHttp } from "../base/use-http.hook";

export function useFilmesApi() {

    const httpInstance = useHttp(BASE_OLDFLIX_API_URL)

    async function getListaDeFilmes() {
        const response = await httpInstance.get("/filmes");
    
        return response;
      }

      return useMemo(
          () => ({
              getListaDeFilmes,
              // eslint-disable-next-line react-hooks/exhaustive-deps
          }),
          []
      )
}