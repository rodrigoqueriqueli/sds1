import React from 'react';
import './App.css';
import Header from './components/Header';
import Home from './pages/Home';

function App() {
  return (
    //react só consegue exportar um elemento por componente
    <div>
      <Header />
      <Home />
    </div>
  );
}

export default App;
