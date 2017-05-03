import React, {Component} from 'react';
import './App.css';

import {
    XYPlot,
    XAxis,
    YAxis,
    LineSeries,
    HorizontalGridLines,
    RadialChart
} from 'react-vis';

import 'react-vis/dist/styles/radial-chart.scss';
import 'react-vis/dist/style.css';

class App extends Component {
    render() {
        return (
            <div>
                <RadialChart
                    innerRadius={100}
                    radius={140}
                    data={[
                        {angle: 1},
                        {angle: 10},
                        {angle: 10},
                        {angle: 10},
                        {angle: 10}
                    ]}
                    width={300}
                    height={300}
                />
                <XYPlot
                    width={300}
                    height={300}>
                    <HorizontalGridLines />
                    <LineSeries
                        color="red"
                        data={[
                            {x: 1, y: 10},
                            {x: 2, y: 5},
                            {x: 3, y: 15}
                        ]}/>
                    <XAxis title="X" />
                    <YAxis />
                </XYPlot>
            </div>
        );
    }
}

export default App;
