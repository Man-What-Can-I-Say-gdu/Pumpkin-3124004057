import React from 'react';
import ReactDOM from 'react-dom';
import App from '../Login.html';
import App from '../Register.html';
import * as serviceWorker from './serviceWorker';

ReactDOM.render(
    <React.StrictMode>
        <App />
    </React.StrictMode>,
    document.getElementById('root')
);


serviceWorker.register();
