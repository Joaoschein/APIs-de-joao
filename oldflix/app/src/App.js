import logo from './logo.svg';
import './App.css';
import { Routes, Route, Navigate } from 'react-router-dom'
import { AlugarScreen } from './ui/screens/oldflix/alugar/Alugar.screen'
import { MenuScreen} from './ui/screens/oldflix/menu/Menu.screen'
import { DevolverScreen } from './ui/screens/oldflix/devolver/devolver.screen'
import { DetalharScreen } from './ui/screens/oldflix/detalhar/Detalhar.screen'
import { AddFilme } from './ui/screens/oldflix/addFilme/AddFilme.Screen'

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<MenuScreen/>} />
        <Route path="/alugar" element={<AlugarScreen/>} />
        <Route path="/devolver" element={<DevolverScreen/>} />
        <Route path="/detalhar" element={<DetalharScreen/>} />
        <Route path="/Adicionar" element={<AddFilme/>} />
      </Routes>
    </div>
  );
}

export default App;
