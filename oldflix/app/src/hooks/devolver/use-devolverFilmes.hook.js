import { useMemo } from "react";
import { BASE_OLDFLIX_API_URL } from "../../constant";
import { useHttp } from "../base/use-http.hook";

export function useDevolverFilmeApi () {

    const httpInstance = useHttp(BASE_OLDFLIX_API_URL);

    async function Devolver(responsavel, id) {
        const response = await httpInstance.put(`/filmes/${id}/devolver`, {responsavel})

        return response
    }

    return useMemo (() => ({
        Devolver,
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }), [])
}