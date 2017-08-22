import React from "react";
import {Link} from "react-router-dom";
import User from "../util/User";

class Navigation extends React.Component {
  render() {
    return (

      <div className="bd-example" data-example-id="">
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
          <a className="navbar-brand" href="#">Navbar</a>

          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav mr-auto">
              <li className="nav-item active">
                <a className="nav-link" href="#">Home <span className="sr-only">(current)</span></a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#">Link</a>
              </li>
              <li className="nav-item">
                <a className="nav-link disabled" href="#">Disabled</a>
              </li>
            </ul>
              <ul className="nav navbar-nav navbar-right">
                  {
                    User.isNotAuthenticated() &&
                    <li><Link to="/user/login">Login</Link></li>
                  }
                  {
                      User.isAuthenticated() &&
                      <li><Link to="/user/login">{User.email}</Link></li>
                  }
              </ul>
          </div>

        </nav>
      </div>
      );
    }
  }
  export default Navigation;
