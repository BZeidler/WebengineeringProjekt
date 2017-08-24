import axios from "axios";
import React from "react";
import User from "../util/User";

class FindMatch extends React.Component {
   constructor(props) {
      super();

      this.state = {
         suggestion: undefined
      }

      this.handleClick = this.handleClick.bind(this);
   }

   // This function is called before render() to initialize its state.
   componentWillMount() {
      axios.get('/api/matching/find')
      .then(({data}) => {
         this.setState({suggestion: data})
      });
   }


   handleClick(id) {
      //TODO: open chat
      //this.props.history.push(`/post/${id}`);
   }

   render() {
      const suggestion = this.state.suggestion
      if(!suggestion)
         return <div/>;
      return (
         <div className="component">
            <table className="table table-hover">
               <thead>
                  <tr>
                     <th className="col-sm-6">Message</th>
                  </tr>
               </thead>
               <tbody>
                  <tr><td>{suggestion.message}</td></tr>
               </tbody>
            </table>
         </div>
      );
   }
}


export default FindMatch;
