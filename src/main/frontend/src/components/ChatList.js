import axios from "axios";
import React from "react";
import { translate, Trans } from 'react-i18next';
import User from "../util/User";

class ChatList extends React.Component {
   constructor(props) {
      super();
      this.state = {
         matchId: window.matchId,
         messages: []
      }
      this.handleMessageChange = this.handleMessageChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
   }

   // This function is called before render() to initialize its state.
   componentWillMount() {
      axios.get(`/api/chat/load/${this.props.match.params.id}`)
      .then(({data}) => {
         this.setState({
            messages: data
         })
      });
   }
    handleMessageChange(event) {
        this.setState({message: event.target.value});
    }

    handleSubmit(event) {
        event.preventDefault();

        axios.post('/api/chat/send',
            {
                matchId: this.state.matchId,
                message: this.state.message
            })
            .then((data) => {
                // Redirect to front page.
                this.props.history.push("/");
            });
    }

   renderPosts() {
      return this.state.messages.map((message => 
      {
         let isAuthor = false;
         if (User.isAuthenticated && User.id == message.author_Id) {
            isAuthor = true;
         }

         let date = new Date(message.created_At).toDateString();

         return (
            <tr>
               <td>{date}</td>
               <td>{message.message}</td>
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
                     <th className="col-sm-2">{t('Date')}</th>
                     <th className="col-sm-6">{t('Message')}</th>
                     </tr>
                 </thead>
                 <tbody>
                    {this.renderPosts()}
                </tbody>
            </table>
            <form onSubmit={this.handleSubmit}>
               <div className="form-group">
                  <label>
                     {t('New Message')}
                  </label>
                  <textarea className="form-control"
                     onChange={this.handleMessageChange}/>
               </div>
               <input type="submit" className="btn btn-success" value={t('Submit')}/>
            </form>
         </div>
      );
   }
}


export default translate('common')(ChatList);
