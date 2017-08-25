import axios from "axios";
import React from "react";
import {withCookies} from "react-cookie";

import User from "../util/User";

class SignUp extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: '',
            name: '',
            message: '',
            error: undefined
        };

        this.handleEmailChange = this.handleEmailChange.bind(this);
        this.handlePasswordChange = this.handlePasswordChange.bind(this);
        this.handleNameChange = this.handleNameChange.bind(this);
        this.handleMessageChange = this.handleMessageChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.cookies = this.props.cookies;
    }

    handleEmailChange(event) {
        this.setState({email: event.target.value});
    }

    handleNameChange(event) {
        this.setState({name: event.target.value});
    }


    handleMessageChange(event) {
        this.setState({message: event.target.value});
    }

    handlePasswordChange(event) {
        this.setState({password: event.target.value});
    }

    handleSubmit(event) {
        event.preventDefault();
        axios.post('/user/signup', this.state, {
            // We allow a status code of 401 (unauthorized). Otherwise it is interpreted as an error and we can't
            // check the HTTP status code.
            validateStatus: (status) => {
                return (status >= 200 && status < 300) || status == 401
            }
        })
            .then(({data, status}) => {
                switch (status) {
                    case 201:
                        User.setCookieCredentials(data);
                        this.setState({error: undefined});

                        // Store authentication values even after refresh.
                        this.cookies.set('auth', {
                            token: data.token,
                            user: User
                        }, {path: '/'});

                        // Send event of updated login state.
                        this.props.updateAuthentication();

                        // Redirect to front page.
                        this.props.history.push("/");
                        break;

                    case 409:
                        this.setState({error: true});
                        break;
                }
            });
    }

    render() {
        let signupComponent =
            <div>
                <form onSubmit={this.handleSubmit} className="form-horizontal">
                    <div className="form-group">
                        <label className="col-sm-2">Email</label>
                        <div className="col-sm-4">
                            <input type="text" className="form-control" autoFocus={true}
                                   value={this.state.email}
                                   onChange={this.handleEmailChange}/>
                        </div>
                    </div>
                    <div className="form-group">
                        <label className="col-sm-2">Name</label>
                        <div className="col-sm-4">
                            <input type="text" className="form-control" autoFocus={true}
                                   value={this.state.name}
                                   onChange={this.handleNameChange}/>
                        </div>
                    </div>
                    <div className="form-group">
                        <label className="col-sm-2">Message</label>
                        <div className="col-sm-4">
                            <input type="text" className="form-control" autoFocus={true}
                                   value={this.state.message}
                                   onChange={this.handleMessageChange}/>
                        </div>
                    </div>
                    <div className="form-group">
                        <label className="col-sm-2">Password</label>
                        <div className="col-sm-4">
                            <input type="text" name="text" className="form-control"
                                   value={this.state.password}
                                   onChange={this.handlePasswordChange}/>
                        </div>
                    </div>
                    <input type="submit" className="btn btn-success" value="Submit"/>
                </form>
              </div>

        return (
            <div className="component">
                {signupComponent}
                <p/>
                { this.state.error &&
                <div className="alert alert-danger">
                    Email is already in use.
                </div>
                }
            </div>
        );
    }
}


export default withCookies(SignUp);
