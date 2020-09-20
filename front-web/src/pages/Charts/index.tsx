import React, { useState, useEffect } from 'react';
import Filters from '../../components/Filters';
import './styles.css';
import { barOptions, pieOptions } from './chart-options';
import Chart from 'react-apexcharts';
import axios from 'axios';
import {buildBarSeries, getPlatformChartData, getGenderChartData } from './helpers';

type PieChartData={
    labels: string[];
    series: number[];
}

type BarChartData ={
    x: string;
    y: number;
}

const initialPieData = {
    labels: [],
    series: []
}

const BASE_URL = 'https://sds1-rodrigo-queriqueli.herokuapp.com';

const Charts = () => {
    //padrao do react hooks
    //no useState primeiro eh a variavel e a segunda a funcao pra atualizar a variavel
    const [barChartData, setBarChartData] = useState<BarChartData[]>([]);
    const [platformData, setPlatformData] = useState<PieChartData>(initialPieData);
    const [genderData, setGenderData] = useState<PieChartData>(initialPieData);

    useEffect(() => {
        async function getData() {
            //funcao assincrona que vai executar um trecho de codigo assincrono
            const recordsResponse = await axios.get(`${BASE_URL}/records`);
            const gamesResponse = await axios.get(`${BASE_URL}/games`);

            const barData = buildBarSeries(gamesResponse.data, recordsResponse.data.content);
            setBarChartData(barData);

            const platformChartData = getPlatformChartData(recordsResponse.data.content);
            setPlatformData(platformChartData);

            const genderChartData = getGenderChartData(recordsResponse.data.content);
            setGenderData(genderChartData);
        }
        getData();
        //o que ta dentro do [ ] vai ser executado assim que o componente charts carregar
    }, [])



    return (
        <div className="page-container">
            <Filters link="/records" linkText="SEE TABEL" />
            <div className="chart-container">
                <div className="top-related">
                    <h1 className="top-related-title">
                        Top rated games
                    </h1>
                    <div className="games-container">
                        <Chart
                            //props abaixo:
                            options={barOptions}
                            type="bar"
                            width="900"
                            height="650"
                            series={[{ data: barChartData }]}
                        />
                    </div>
                </div>
                <div className="charts">
                    <div className="platform-chart">
                        <h2 className="chart-title">Platforms</h2>
                        <Chart
                            //preparando o componente pra receber uma prop {{}}
                            options={{ ...pieOptions, labels: platformData?.labels }}
                            type="donut"
                            series={platformData?.series}
                            width="350"
                        />
                    </div>
                    <div className="gender-chart">
                        <h2 className="chart-title">Genres</h2>
                        <Chart
                            //preparando o componente pra receber uma prop {{}}
                            options={{ ...pieOptions, labels: genderData?.labels }}
                            type="donut"
                            series={genderData?.series}
                            width="350"
                        />
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Charts;