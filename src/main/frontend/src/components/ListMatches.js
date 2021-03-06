import axios from "axios";
import React from "react";
import { translate, Trans } from 'react-i18next';
import User from "../util/User";

class MatchList extends React.Component {
   constructor(props) {
      super();
      this.state = {
         matches: []
      }

      this.handleClick = this.handleClick.bind(this);
   }

   // This function is called before render() to initialize its state.
   componentWillMount() {
      axios.get('/api/matching/list')
      .then(({data}) => {
         this.setState({
            matches: data
         })
      });
   }
   
   handleClick(id) {
      //TODO: open chat
      window.matchId=id;
      this.props.history.push(`chat/load/${id}`);
   }

   renderPosts() {
      return this.state.matches.map((match => {
         let isAuthor = false;

         if(!match.lastMessage){
            return (
            <tr key={match.name} onClick={() => this.handleClick(match.id)} className={isAuthor ? 'success' : ''}>
               <td>{match.name}</td>
               <td/>
               <td>No messages</td>
               <td/>
            </tr>
         );
         }
         
         if (User.isAuthenticated && User.id == match.lastMessage.author_Id) {
            isAuthor = true;
         }

         let date = new Date(match.lastMessage.created_At).toDateString();
         let author;
         if(match.lastMessage.author_Id === User.id)
         {
            author = User.name;
         }
         else
         {
            author = match.name;
         }

         return (
            <tr key={match.lastMessage.message} onClick={() => this.handleClick(match.id)} className={isAuthor ? 'success' : ''}>
               <td>{match.name}</td>
               <td>{date}</td>
               <td>{match.lastMessage.message}</td>
               <td>{author}</td>
            </tr>
         );
      }));
   }


   render() {
      const { t, i18n } = this.props;
      
      const changeLanguage = (lng) => {
          i18n.changeLanguage(lng);
      }
      return (
         <div className="component">
            <table className="table table-hover">
               <thead>
                  <tr>
                     <th className="col-sm-2">{t('Match Name')}</th>
                     <th className="col-sm-2">{t('Date')}</th>
                     <th className="col-sm-6">{t('Last Message')}</th>
                     <th className="col-sm-2">{t('Author')}</th>
                  </tr>
               </thead>
               <tbody>
                  {this.renderPosts()}
               </tbody>
            </table>
         </div>
      );
   }
}


export default translate('common')(MatchList);
