import createGlobalState from "react-create-global-state";

const MOTORISTA_KEY = "motorista"

const motoristaInLocalStorage = localStorage.getItem(MOTORISTA_KEY);
const motorista = motoristaInLocalStorage ? JSON.parse(motoristaInLocalStorage) : {}

const [_useGlobalMotorista, GlobalMotoristaProvider] = createGlobalState(motorista)

function useGlobalMotorista() {
    const [globalMotorista, _setGlobalMotorista] = _useGlobalMotorista()
  
    function setState(valor) {
      localStorage.setItem(MOTORISTA_KEY, JSON.stringify(valor))
      _setGlobalMotorista(valor)
    }
  
    return [globalMotorista, setState]
  }

  export { useGlobalMotorista, GlobalMotoristaProvider}