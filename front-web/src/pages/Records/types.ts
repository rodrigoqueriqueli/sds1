export type RecordsResponse = {
    content: RecordItem[];
    totalPages: number;
}

export type RecordItem = {
    // tipando o payload que ira ser consumido do backend
    id: number;
    moment: string;
    name: string;
    age: number;
    gameTitle: string;
    gamePlatform: Platform; //ou (,) confirmar depois
    genreName: string;
}

export type Platform = 'XBOX' | 'PC'  | 'PLAYSTATION';