import logo from './logo.svg';
import './App.css';
import { Routes, Route } from 'react-router-dom'
import { PassageirosScreen, MotoristasScreen, MenuInicial, PassageiroSelecionado, MotoristaSelecionado} from './ui/screens'

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<MenuInicial/>}/>
        <Route path="/motoristas" element={<MotoristasScreen/>}/>
        <Route path="/passageiros" element={<PassageirosScreen/>}/>
        <Route path="/passageiro_selecionado" element={<PassageiroSelecionado/>}/>
        <Route path="/motorista_selecionado" element={<MotoristaSelecionado/>}/>       
      </Routes>
    </div>
  );
}

export default App;
