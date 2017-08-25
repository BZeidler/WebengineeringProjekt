import axios from "axios";
import React from "react";
import { translate, Trans } from 'react-i18next';
import User from "../util/User";

class FindMatch extends React.Component {
   constructor(props) {
      super();

      this.state = {
         suggestion: undefined
      }
      this.handleLike = this.handleLike.bind(this);
      this.handleDislike = this.handleDislike.bind(this);
   }

   // This function is called before render() to initialize its state.
   componentWillMount() {
      axios.get('/api/matching/find')
      .then(({data}) => {
         this.setState({suggestion: data})
      });
   }
   handleLike(event){
      const suggestion=this.state.suggestion;
      axios.post('/api/matching/like',
         {
            id: suggestion.id
         }).then((data) => {
            this.props.history.push("/");
         }
      );
   }
   handleDislike(event){
      const suggestion=this.state.suggestion;
      axios.post('/api/matching/dislike',
         {
            id: suggestion.id
         }).then((data) => {
            this.props.history.push("/");
         }
      );
   }
      
   render() {
      const { t, i18n } = this.props;
      
      const changeLanguage = (lng) => {
          i18n.changeLanguage(lng);
      }
      const suggestion = this.state.suggestion
      if(!suggestion)
         return <div/>;
      return (
         <div className="component">
            <table className="table table-hover">
               <thead>
                  <tr>
                     <th className="col-sm-6">{t('Message')}</th>
                  </tr>
               </thead>
               <tbody>
                  <tr><td>{suggestion.message}</td></tr>
               </tbody>
            </table>
            <div>
               <form onSubmit={this.handleLike}>
                  <input type="submit" className="btn" value={t('Like')}/>
               </form>
               <form onSubmit={this.handleDislike}>
                  <input type="submit" className="btn" value={t('Disike')}/>
               </form>
            </div>
         </div>
      );
   }
}



export default translate('common')(FindMatch);
