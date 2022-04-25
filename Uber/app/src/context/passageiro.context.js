import createGlobalState from "react-create-global-state";

const PASSAGEIRO_KEY = "passageiro"

const passageiroInLocalStorage = localStorage.getItem(PASSAGEIRO_KEY);
const passageiro = passageiroInLocalStorage ? JSON.parse(passageiroInLocalStorage) : {}

const [_useGlobalPassageiro, GlobalPassageiroProvider] = createGlobalState(passageiro)

function useGlobalPassageiro() {
    const [globalPassageiro, _setGlobalPassageiro] = _useGlobalPassageiro()
  
    function setState(valor) {
      localStorage.setItem(PASSAGEIRO_KEY, JSON.stringify(valor))
      _setGlobalPassageiro(valor)
    }
  
    return [globalPassageiro, setState]
  }

  export { useGlobalPassageiro, GlobalPassageiroProvider}