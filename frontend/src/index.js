import ReactDOM from "react-dom";
import React from "react";

const App = () => {
    return (
        <React.StrictMode>
            <div>Application</div>
        </React.StrictMode>
    )
}

ReactDOM.render(<App/>, document.getElementById("root"));