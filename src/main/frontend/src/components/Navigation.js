import React from "react";
import {Link} from "react-router-dom";
import { translate, Trans } from 'react-i18next';
import User from "../util/User";

class Navigation extends React.Component {
   updateAuthentication() {
      // If we would store the authentication state in the component's state and reset the state,
      // we would not have to do this.
      this.forceUpdate();
   }
   render() {
      const { t, i18n } = this.props;
      
      const changeLanguage = (lng) => {
          i18n.changeLanguage(lng);
      }
      return (

         <div className="bd-example" data-example-id="">
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
               <a className="navbar-brand" href="#">{t('Navbar')}</a>

                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul className="navbar-nav mr-auto">
                     <li className="nav-item"><a className="nav-link" href="/">{t('Matches')}</a></li>
                     <li className="nav-item">
                        <Link className="nav-link" to="/matching/find">{t('Find People')}</Link>
                     </li>
                  </ul>
                  {
                     User.isNotAuthenticated() &&
                     <ul className="nav navbar-nav navbar-right">
                         <li><Link to="/user/login">{t('Login')}</Link></li> 
                         <li><Link to="/user/SignUp">{t('SignUp')}</Link></li>
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
export default translate('common')(Navigation);
