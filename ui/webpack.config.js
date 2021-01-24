"use strict";

const {VueLoaderPlugin} = require("vue-loader");
const HtmlWebpackPlugin = require("html-webpack-plugin");

const path = require("path");

module.exports = {
    mode: "development",
    entry: "./src/main/main.js",
    output: {
        filename: "main.js",
        path: path.resolve(__dirname, "build"),
        publicPath: "/"
    },
    module: {
        rules: [
            { test: /\.vue$/, use: "vue-loader" },
            {
                test: /\.css$/, use: [
                    "vue-style-loader",
                    "css-loader"
                ]
            }
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            title: "Das Cookbook, Yo!",
            hash: true,
            template: path.resolve(__dirname, "public/index.html"),
            templateParameters: {
                "BASE_URL": "/"
            }
        }),
        new VueLoaderPlugin()
    ],
    resolve: {
        alias: {
            "@app": path.resolve(__dirname, "src/main/"),
            "@test": path.resolve(__dirname, "src/test/"),
        },
        modules: [
            path.resolve(__dirname, "node_modules")
        ]
    },
    devServer: {
        contentBase: path.resolve(__dirname, "build"),
        port: "3000",
        proxy: {
            "/api": {
                target: "http://localhost:8080"
            }
        },
        historyApiFallback: true
    }
};
