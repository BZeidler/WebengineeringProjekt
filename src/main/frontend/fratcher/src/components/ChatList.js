import axios from "axios";
import React from "react";
import User from "../util/User";

class ChatList extends React.Component {
   constructor(props) {
      super();
      this.state = {
         messages: []
      }

      this.handleClick = this.handleClick.bind(this);
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


   handleClick(id) {
      //TODO: open chat
      //this.props.history.push(`/post/${id}`);
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
      return (
         <div className="component">
            <table className="table table-hover">
               <thead>
                  <tr>
                     <th className="col-sm-2">Date Name</th>
                     <th className="col-sm-6">Message</th>
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


export default ChatList;
