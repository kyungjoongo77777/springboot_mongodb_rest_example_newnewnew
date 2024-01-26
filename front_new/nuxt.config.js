// https://nuxt.com/docs/api/configuration/nuxt-config
import {useSharedService} from "./services/SharedService.js";

export default defineNuxtConfig({
    devtools: {enabled: false},
    modules: [
        '@ant-design-vue/nuxt',
    ],
    antd: {
        // Options
    },
    router: {
        middleware: 'auth'
    },
    serverMiddleware: [
        (req, res, next) => {
            let sharedService = useSharedService()
            req.sharedService = sharedService;
            next();
        }
    ]
})


