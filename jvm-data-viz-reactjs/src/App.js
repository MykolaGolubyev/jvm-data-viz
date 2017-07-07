import React, {Component} from 'react';
import './App.css';

import VisualizationServer from './server/VisualizationServer';

import {
    VictoryChart,
    VictoryBar,
    VictoryLine,
    VictoryScatter
} from 'victory'

class DataListeners {
    constructor() {
        this.listeners = [];
    }

    addListener(l) {
        this.listeners.push(l)
    }

    registerNewData(data) {
        this.listeners.forEach(l => l(data))
    }
}

const dataListeners = new DataListeners();

class App extends Component {
    constructor(props) {
        super(props);

        const data = [[40, 20], [30, 15], [50, 22]];
        this.state = {data};

        dataListeners.addListener(this.dataListener)

        // this.server = new VisualizationServer({onData: this.onData.bind(this)});
        // this.server.connect();
    }

    dataListener = (data) => {
        const rand = () => Math.random() * 30 - 15;
        this.setState({data: [[rand(), rand()], [rand(), rand()], [rand(), rand()]]})
    };

    render() {
        const {data} = this.state;

        return (
            <div style={{width: 700, height: 400}}>
                <div>Hello</div>
                <VictoryChart>
                    <VictoryLine data={data} x={0} y={1}/>
                    <VictoryScatter data={data} x={0} y={1}/>
                </VictoryChart>
            </div>
        );
    }

    onData(data) {
        if (!data) {
            return;
        }

        console.log("got data", data.data);
        const plotData = data.data.map((d, idx) => {
            return {x: idx, y: d}
        });

        this.setState({plotData});
    }
}

global.testDirection = () => {
    dataListeners.registerNewData({})
};

export default App;
