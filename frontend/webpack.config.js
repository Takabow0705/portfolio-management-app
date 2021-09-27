const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const FixStyleOnlyEntriesPlugin = require('webpack-fix-style-only-entries');
const path = require("path");
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
    devServer: {
        port: 5000,
        hot: true,
        historyApiFallback: true,
    },
    devtool: 'inline-source-map',
    performance: {
        hints: false
    },
    devtool: "source-map",
    watchOptions: {
        ignored: /node_modules/
    },
    module: {
        rules: [
            {
                test: /\.js$|jsx/,
                exclude: /node_modules/,
                loader: "babel-loader"
            },
            {
                test: /\.scss$/,
                use: [
                    "style-loader",
                    {
                        loader: "css-loader",
                        options: {
                            url: false,
                            sourceMap: true,
                        },
                    },
                    {
                        loader: "sass-loader",
                        options: {
                            sourceMap: true,
                        },
                    },
                ],
            },
        ]
    },
}