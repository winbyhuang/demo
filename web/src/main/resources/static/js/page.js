// 'use strict';
class HelloMessage extends React.Component {
    render() {
        return (
            <div>
                {this.props.name1}:<input type="text" value={this.props.name}/>
                {this.props.name2}:<input type="text" value={this.props.name}/>
                {this.props.name3}:<input type="text" value={this.props.name}/>
            </div>
        );
    }
};
//import {HelloMessage} from '../static/js/page.js'
ReactDOM.render(<HelloMessage name1="111" name2="222" name3="333"/>,document.getElementById('ccc'));