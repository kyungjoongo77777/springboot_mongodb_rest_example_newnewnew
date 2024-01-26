import {createGlobalObservable, useLocalObservable} from "mobx-vue-lite";
import axios from "axios";
import {toJS} from "mobx";
import {useRouter} from "vue-router";
import _ from "lodash";
import {ENDPOINT_PREFIX} from "../constants/FrontConstansts.js";

export const useSharedService = createGlobalObservable(() => {
    return useLocalObservable(() => ({
        count: 0,
        boardList: [],
        loading: false,
        user: {},
        userId: '',
        userPwd: '',
        modalVisible: false,
        currentBoardId: '',
        currentTitle: '',
        currentContents: '',
        currentBoard: {
            title: '',
            contents: '',
            author: "kyungjoon",
            createdDt: "2024-01-15",
            isAdmin: false
        },
        isAdmin: false,
        userList:[],
        isWriteMode: true,
        async handleLogin(userId, userPwd, router) {
            localStorage.setItem("userId", userId);
            localStorage.setItem("userPwd", userPwd);

            let _userId = localStorage.getItem("userId");
            let _userPwd = localStorage.getItem("userPwd");
            console.log("_userId===>", _userId);
            console.log("_userPwd===>", _userPwd);
            this.user = _userId;
            let result = await axios.post(`${ENDPOINT_PREFIX}/user/login`, {
                userId: _userId,
                password: _userPwd
            })
            console.log("resultresultresultresult===>", result.data)
            if (_.isEmpty(result.data)) {
                alert('유저 정보 no!!(id,pwd가 일치 하지 않습니다)')
            } else {
                //alert('해당 아이디가 존재하므로 다음 페이지로 진행')
                const {$locally} = useNuxtApp()
                $locally.setItem('userId', userId)
                localStorage.setItem('isAdmin', result.data.isAdmin)
                router.push("/board");
            }
        },
        async getBoardList() {
            this.loading = true;
            let results = await axios.get(`${ENDPOINT_PREFIX}/board`,)
            this.boardList = results.data;
            console.log("boardList===boardList>", toJS(this.boardList));
            this.loading = false;

        },
        /**
         *
         * @returns {Promise<void>}
         */
        async getUserList() {
            this.loading = true;
            let results = await axios.get(`${ENDPOINT_PREFIX}/user`,)
            console.log("userList===>", results.data)
            this.userList = results.data;
            this.loading = false;

        },
        async postBoardOne() {
            let _body = {
                title: this.currentTitle,
                contents: this.currentContents,
                author: localStorage.getItem('userId'),
                createdDt: new Date().toLocaleString(),
                //isAdmin: false
            }
            let results = await axios.post(`${ENDPOINT_PREFIX}/board`, _body)
            await this.getBoardList();
        },
        async putBoardOne() {
            let _body = {
                title: this.currentTitle,
                contents: this.currentContents,
                author: localStorage.getItem('userId'),
                createdDt: new Date().toLocaleString(),
            }
            let results = await axios.put(`${ENDPOINT_PREFIX}/board/${this.currentBoardId}`, _body)
            this.modalVisible = false;
            await this.getBoardList();

        },
        async getBoardOne(id) {
            let results = await axios.get(`${ENDPOINT_PREFIX}/board/${id}`)

            console.log("results====>", results);

        },
        async SignUp(userInfo) {
            const sharedService = useSharedService()
            const router = useRouter()
            this.loading = true;
            let results = await axios.post(`${ENDPOINT_PREFIX}/users`, userInfo);
            if (results.data.statusCode === 200) {
                // sharedService.value.showToast('가입 완료, 잠시후 로그인 페이지로 이동.!')
                return true;
            } else {
                sharedService.value.showToast('가입 failed')
                return false;
            }
        },
        showToast(msg) {
            alert(msg)
        }
    }));
});