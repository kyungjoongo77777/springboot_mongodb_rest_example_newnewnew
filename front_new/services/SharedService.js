import {createGlobalObservable, useLocalObservable} from "mobx-vue-lite";
import axios from "axios";
//import {toJS} from "mobx";
import {useRouter} from "vue-router";
import _ from "lodash";
import {ENDPOINT_PREFIX_DEV, ENDPOINT_PREFIX_PROD} from "../constants/FrontConstansts.js";
import {notification} from "ant-design-vue";

const __END_POINT_PREFIX__ = process.env.NODE_ENV === 'development' ? ENDPOINT_PREFIX_DEV : ENDPOINT_PREFIX_PROD

console.log("__END_POINT_PREFIX__====>",__END_POINT_PREFIX__);

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
        userList: [],
        isWriteMode: true,
        async handleLogin(userId, userPwd, router) {
            localStorage.setItem("userId", userId);
            localStorage.setItem("userPwd", userPwd);
            let _userId = localStorage.getItem("userId");
            let _userPwd = localStorage.getItem("userPwd");
            this.user = _userId;
            let result = await axios.post(`${__END_POINT_PREFIX__}/user/login`, {
                userId: _userId,
                password: _userPwd
            })
            if (_.isEmpty(result.data)) {
                alert('id/pwd 가 일치하지 않습니다. 하단의  User List를 보고 잘 넣으세요')
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
            let results = await axios.get(`${__END_POINT_PREFIX__}/board`,)
            this.boardList = results.data;
            this.loading = false;

        },
        async handleDeleteOne(item) {
            if (item.author === localStorage.getItem('userId') || localStorage.getItem('isAdmin').toString() === 'true') {
                let result = await axios.delete(`${__END_POINT_PREFIX__}/board/${item.id}`,)
                await this.getBoardList()
                this.value.currentBoardId = item.id;
            } else {
                //  alert('자기글만 삭제 가능 또는 관리자인 경우!')
                notification.open({
                    message: `자기글만 삭제 가능 또는 관리자인 경우!`,
                    duration: 1.5,
                });
            }
        },
        handleModify(item) {
            if (item.author === localStorage.getItem('userId') || localStorage.getItem('isAdmin').toString() === 'true') {
                this.isWriteMode = false;
                this.currentTitle = item.title;
                this.currentBoardId = item.id;
                this.currentContents = item.contents;
                this.modalVisible = true;
            } else {
                notification.open({
                    message: `자기글만 수정 가능 합니다 또는 관리자만 수정 가능!`,
                    duration: 1.5,
                });
            }
        },
        async getUserList() {
            this.loading = true;
            let results = await axios.get(`${__END_POINT_PREFIX__}/user`,)
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
            let results = await axios.post(`${__END_POINT_PREFIX__}/board`, _body)
            await this.getBoardList();
        },
        async putBoardOne() {
            let _body = {
                title: this.currentTitle,
                contents: this.currentContents,
                author: localStorage.getItem('userId'),
                createdDt: new Date().toLocaleString(),
            }
            let results = await axios.put(`${__END_POINT_PREFIX__}/board/${this.currentBoardId}`, _body)
            this.modalVisible = false;
            await this.getBoardList();

        },
        async getBoardOne(id) {
            let results = await axios.get(`${__END_POINT_PREFIX__}/board/${id}`)

        },
        async SignUp(userInfo) {
            const sharedService = useSharedService()
            const router = useRouter()
            this.loading = true;
            let results = await axios.post(`${__END_POINT_PREFIX__}/users`, userInfo);
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