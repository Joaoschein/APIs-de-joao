import { useMemo } from "react";
import { BASE_OLDFLIX_API_URL } from "../../constant";
import { useHttp } from "../base/use-http.hook";

export function useAlugarFilmeApi () {

    const httpInstance = useHttp(BASE_OLDFLIX_API_URL);

    async function Alugar(responsavel, id) {
        const response = await httpInstance.put(`/filmes/${id}/alugar`, {responsavel})

        return response
    }

    return useMemo (() => ({
        Alugar,
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }), [])
}