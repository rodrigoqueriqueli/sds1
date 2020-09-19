import React from 'react';
import './styles.css';

type Props = {
    //? significa q é opcional
    totalPages?: number;
    goToPage: Function;
    activePage: number;
}

const Pagination = ({ totalPages = 0, goToPage, activePage }: Props) => {
    //array de items
    const paginationItems = Array.from(Array(totalPages).keys());


    return (
        <div className="pagination-container">
            {paginationItems.map(item => (
                <button
                    key={item}
                    className={`pagination-item ${ activePage === item ? 'active' : 'inactive'}`}
                    //quando user apertar no botao de paginacao, vai invocar a prop/funcao (o metodo) goToPage
                    onClick={() => goToPage(item)}
                >
                    {item + 1}
                </button>
            ))}
        </div>
    );
}

export default Pagination;