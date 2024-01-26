export default defineNuxtRouteMiddleware(async (to, from) => {

    const {$locally} = useNuxtApp()
    const userId = $locally.getItem('userId')

    //todo: 인증이 안되어 있는 경우...
    if (userId === undefined || userId === 'undefined') {
        return navigateTo('/')
    }
})
