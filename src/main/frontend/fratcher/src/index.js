import React from 'react';
import ReactDOM from 'react-dom';
import {CookiesProvider} from "react-cookie";
import {I18nextProvider} from "react-i18next";
import {HashRouter as Router, Route, Switch} from "react-router-dom";

import i18n from "./I18n";

import App from './App';
import User from './util/User'
import Navigation from './components/Navigation'
import Authentication from './components/Authentication'
import registerServiceWorker from './registerServiceWorker';

class Root extends React.Component {
    constructor(props) {
        super(props);
        // Force initialization of the object.
        User.isAuthenticated();
        this.updateAuthentication = this.updateAuthentication.bind(this);
    }

    // This is called whenever the authentication state of a user is changed by a component. Additionally,
    // this is an example of intersibling communication with a common parent.
    updateAuthentication() {
        this.nav.updateAuthentication();
    }

    render() {
        return (
            <div>
                <Navigation ref={(component) => {
                    this.nav = component;
                }}/>
                <Switch>
                    {/*Authentication*/}
                    // See https://github.com/ReactTraining/react-router/issues/4627
                    <Route path="/user/login"
                           render={(props) => (
                               <Authentication {...props} updateAuthentication={this.updateAuthentication}/> )}/>
                    <Route path="api/matching" component={MatchList}/>
                </Switch>
            </div>
        );
    }
}
ReactDOM.render(
    <CookiesProvider>
        <I18nextProvider i18n={i18n}>
            <Router>
                <Root />
            </Router>
        </I18nextProvider>
    </CookiesProvider>
    , document.getElementById('root'));

// ReactDOM.render(<App />, document.getElementById('root'));
// registerServiceWorker();
