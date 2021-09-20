var path = require("path");
const environment = process.env.NODE_ENV || "development";

module.exports = {
    mode: `${environment}`,
    entry: "./src/index.js",
    output: {
        path: path.resolve(__dirname, "public"),
        filename: "bundle.js"
    },
    resolve: {
        alias: {
            AppConfig: path.join(__dirname, `src/config/${environment}.js`)
        },
        extensions: ['.js', '.jsx'],
    },
    devServer:{
      port: 5000,
        hot: true,
        historyApiFallback: true,
    },
    devtool: 'inline-source-map',
    performance:{
      hints: false
    },
    module: {
        rules: [
            {
                test: /\.js$|jsx/,
                exclude: /node_modules/,
                loader: "babel-loader"
            }
        ]
    }
}