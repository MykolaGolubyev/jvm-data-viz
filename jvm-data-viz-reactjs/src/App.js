import React, {Component} from 'react';
import './App.css';

import VisualizationServer from './server/VisualizationServer';

import Symbol from 'es6-symbol';
global.Symbol = Symbol;

// import {
//     XYPlot,
//     XAxis,
//     YAxis,
//     LineSeries,
//     HorizontalGridLines,
//     VerticalGridLines,
//     VerticalBarSeries,
//     RadialChart
// } from 'react-vis';
//
import 'react-vis/dist/styles/radial-chart.scss';
import 'react-vis/dist/style.css';


class App extends Component {
    constructor(props) {
        super(props);

        this.state = {
            plotData: [
                {x: 1, y: 10},
                {x: 2, y: 5},
                {x: 3, y: 15},
                {x: 6, y: 18}
            ]
        };

        // this.server = new VisualizationServer({onData: this.onData.bind(this)});
        // this.server.connect();
    }

    render() {
        const barData = [
            {x: 'A', y: 10},
            {x: 'B', y: 5},
            {x: 'C', y: 15}
        ];

        return (
            <div>
                <div>Hello</div>
                {/*<XYPlot*/}
                    {/*width={300}*/}
                    {/*height={300}>*/}
                    {/*<VerticalGridLines />*/}
                    {/*<HorizontalGridLines />*/}
                    {/*<LineSeries*/}
                        {/*color="red"*/}
                        {/*data={this.state.plotData}*/}
                    {/*/>*/}

                    {/*<XAxis title="X"/>*/}
                    {/*<YAxis title="Y"/>*/}

                    {/*/!*<VerticalBarSeries data={[*!/*/}
                    {/*/!*{x: 'A', y: 10},*!/*/}
                    {/*/!*{x: 'B', y: 5},*!/*/}
                    {/*/!*{x: 'C', y: 15}*!/*/}
                    {/*/!*]}/>*!/*/}
                {/*</XYPlot>*/}


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

export default App;
