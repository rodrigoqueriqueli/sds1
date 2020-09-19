import React from 'react';
import './App.css';
import Header from './components/Header';

function App() {
  return (
    //react só consegue exportar um elemento por componente
    <div>
      <Header   />
    </div>
  );
}

export default App;
