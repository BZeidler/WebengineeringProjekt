import React from 'react';
import ReactDOM from 'react-dom';
import {CookiesProvider} from "react-cookie";
import {I18nextProvider} from "react-i18next";
import {HashRouter as Router, Route, Switch} from "react-router-dom";

import i18n from "./I18n";

import App from './App';
import registerServiceWorker from './registerServiceWorker';

ReactDOM.render(<App />, document.getElementById('root'));
registerServiceWorker();
