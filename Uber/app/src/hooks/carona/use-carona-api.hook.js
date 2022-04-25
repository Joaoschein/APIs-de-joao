import { useHttp } from "../base/use-http.hook"
import { BASE_ME_LEVA_AI_API_URL } from "../../constant"
import { useMemo } from "react";


export function useCaronaApi() {
   
    const httpInstance = useHttp(BASE_ME_LEVA_AI_API_URL);

    async function SolicitarCorrida(id, pontoInicialX, pontoInicialY, pontoFinalX, pontoFinalY) {
        const response = await httpInstance.post("corrida", {
            id,
            pontoInicialX,
            pontoInicialY,
            pontoFinalX,
            pontoFinalY 
        });
        return response;
    }

    async function IniciarCorrida(id) {
        const response = await httpInstance.put(`corrida/${id}/iniciar`);

        return response;
    }

    async function FinalizarCorrida(id) {
        try{
            await httpInstance.put(`corrida/${id}/finalizar`);
        } catch(error){
            throw error
        }

        }

    async function ListarPassageiros() {
        const response = await httpInstance.get("passageiro");

        return response;
    }

    async function AvaliarPassageiro(nota, id) {
        const response = await httpInstance.put(`corrida/${id}/avaliar/passageiro`, {
            nota
        });

        return response;
    }

    async function AvaliarMotorista(nota, id) {
        const response = await httpInstance.put(`corrida/${id}/avaliar/motorista`, {
            nota
        });

        return response;
    }

    async function SaqueMotorista(valor, id) {
        const response = await httpInstance.put(`motorista/${id}/sacar`, {
            valor
        });

        return response;
    }

    async function DepositoPassageiro(valor, id) {
        const response = await httpInstance.put(`passageiro/${id}/depositar`, {
            valor
        });

        return response;
    }

    async function ListarMotoristas() {
        const response = await httpInstance.get("motorista");

        return response;
    }

    async function InativarPassageiro(id) {
        const response = await httpInstance.put(`passageiro/${id}/inativar`);

        return response
    }

    async function getPassageiroPorID(id) {
        const response = await httpInstance.get(`passageiro/${id}`);

        return response
    }

    async function getMotoristaPorID(id) {
        const response = await httpInstance.get(`motorista/${id}`);

        return response
    }


    return useMemo(
        () => ({
            SolicitarCorrida,
            IniciarCorrida,
            FinalizarCorrida,
            ListarPassageiros,
            AvaliarPassageiro,
            AvaliarMotorista,
            SaqueMotorista,
            DepositoPassageiro,
            ListarMotoristas,
            InativarPassageiro,
            getPassageiroPorID,
            getMotoristaPorID,
            // eslint-disable-next-line react-hooks/exhaustive-deps
        }),
        []
    )
}