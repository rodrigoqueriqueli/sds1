import React, { useEffect, useState } from 'react';
import './styles.css';
import axios from 'axios';
import { RecordsResponse } from './types';
import { formatDate } from './helpers';
import Pagination from './Pagination';
import { Link } from 'react-router-dom';


const BASE_URL = 'http://localhost:8080'

const Records = () => {
    const [recordsResponse, setRecordsResponse] = useState<RecordsResponse>();
    //pagina inicial que vai buscar do backend eh o 0
    const [activePage, setActivePage] = useState(0);

    //toda vez que tiver alteracao de estado ele vai executar o bloco abaixo
    useEffect(() => {
        //o axios faz uma conexao http...faz requisicao para o endpoint 
        axios.get(`${BASE_URL}/records?linesPerPage=12&page=${activePage}`)
            .then(response => setRecordsResponse(response.data));
            //colocando o activePage como dependencia do useEffect
    }, [activePage]);

    const handlePageChange = (index: number) => {
        //mudando a pagina automaticamente
        setActivePage(index)
    }

    return (
        <div className="page-container">
            <div className="filters-container records-actions">
                <Link to="/charts">
                    <button className="action-filters">
                        SEE GRAPHS
                    </button>
                </Link>
            </div>
            <table className="records-table" cellPadding="0" cellSpacing="0">
                <thead>
                    <tr>
                        <th>INSTANT</th>
                        <th>NAME</th>
                        <th>AGE</th>
                        <th>PLATFORM</th>
                        <th>GENRE</th>
                        <th>GAME TITLE</th>
                    </tr>
                </thead>
                <tbody>
                    {recordsResponse?.content.map(record => (
                        <tr key={record.id}>
                            <td>{formatDate(record.moment)}</td>
                            <td>{record.name}</td>
                            <td>{record.age}</td>
                            <td className="text-secondary">{record.gamePlatform}</td>
                            <td>{record.genreName}</td>
                            <td className="text-primary">{record.gameTitle}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <Pagination
                activePage={activePage}
                //?. pq de inicio ele vem undefined
                //quando funcao for invocada vou chamar o metodo la de cima
                goToPage={handlePageChange}
                totalPages={recordsResponse?.totalPages}
            />
        </div>
    );
}

export default Records;

