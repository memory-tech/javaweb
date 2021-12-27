module.exports = {
    devServer: {
        proxy:{
            '/api':{
                target:'http://localhost:7777',
                secure:false,
                changeOrigin:true,
                pathRewrite:{
                    '^/api':'/'
                }
            }
        }
    }
}