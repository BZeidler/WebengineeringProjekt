import React from "react";
import {Link} from "react-router-dom";
import User from "../util/User";

class Navigation extends React.Component {
   updateAuthentication() {
      // If we would store the authentication state in the component's state and reset the state,
      // we would not have to do this.
      this.forceUpdate();
   }
   render() {
      return (

         <div className="bd-example" data-example-id="">
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
               <a className="navbar-brand" href="#">Navbar</a>

                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul className="navbar-nav mr-auto">
                     <li className="nav-item"><a className="nav-link" href="/">Matches</a></li>
                     <li className="nav-item">
                        <Link className="nav-link" to="/matching/find">Find People</Link>
                     </li>
                  </ul>
                  {
                     User.isNotAuthenticated() &&
                     <ul className="nav navbar-nav navbar-right">
                         <li><Link to="/user/login">Login</Link></li> 
                         <li><Link to="/user/SignUp">SignUp</Link></li>
                     </ul>
                  }
                  {
                     User.isAuthenticated() &&
                     <ul className="nav navbar-nav navbar-right">
                         <li><Link to="/user/login">{User.email}</Link></li>
                     </ul>
                  }
               </div>
            </nav>
         </div>
      );
   }
}
  export default Navigation;
