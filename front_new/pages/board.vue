<template>

  <div style="margin: 30px">
    <a-flex gap="middle">
      <div
          v-if="sharedService.loading===false"
          style="display: flex; background: #dde5e0; height: 30px;align-items: center; padding-left: 20px;justify-content: flex-end;padding-right: 30px;width: 1275px;">
        userId : {{ sharedService.userId }}
      </div>
      <div
          style="display: flex; background: #dde5e0; height: 30px;align-items: center; padding-left: 20px;justify-content: flex-end;padding-right: 30px;width: 220px;">
        관리자 : {{ sharedService.isAdmin }}
      </div>
      <a-flex vertical style="align-self: center">
        <a-button v-on:click="handleLogout" type="primary">
          logout
        </a-button>
      </a-flex>

    </a-flex>
    <div style="position: absolute; top:25%; left: 45%" v-if="sharedService.loading">
      <a-spin/>
    </div>
    <FormModal/>
    <a-flex gap="middle">
      <table border="1">
        <tr>
          <!--        <th style="background-color: orange">id</th>-->
          <th style="background-color: orange;width:300px;height: 40px;">제목</th>
          <th style="background-color: orange;width:500px;">내용</th>
          <th style="background-color: orange;width:100px;">글쓴이</th>
          <th style="background-color: orange;width:250px;">작성일자</th>
          <th style="background-color: orange;width:100px;">
            삭제
          </th>
          <th style="background-color: orange;width:100px;">수정</th>
        </tr>
        <tr v-for="item in sharedService.boardList" :key="item.id" style="padding: 10px;">
          <!--        <td style="padding: 10px">{{ item.id }}</td>-->
          <td style="padding: 10px;flex:.25">{{ item.title }}</td>
          <td style="padding: 10px;flex:.25">
          <pre style="text-align: left;white-space: pre-line;">
            {{ item.contents }}
          </pre>
          </td>
          <td style="padding: 10px;flex:.25">{{ item.author }}</td>
          <td style="padding: 10px;flex:.25">{{ item.createdDt }}</td>
          <td style="padding: 10px;flex:.25; ">
            &nbsp;&nbsp;
            <button v-on:click="sharedService.handleDeleteOne(item)">
              삭제
            </button>
          </td>
          <td style="padding: 10px;flex:.25">
            &nbsp;&nbsp;
            <button v-on:click="sharedService.handleModify(item)">
              수정
            </button>
          </td>
        </tr>
      </table>
    </a-flex>
    <br/>
    <a-button v-on:click="showWriteModal" type="primary">
      글쓰기
    </a-button>
  </div>

</template>

<script setup>
definePageMeta({
  middleware: ["auth"]
})

import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import axios from "axios";
import {useSharedService} from "~/services/SharedService.js";
import FormModal from "~/components/FormModal.vue";
import {ENDPOINT_PREFIX} from "~/constants/FrontConstansts.js";
import {notification} from 'ant-design-vue';

const [api, contextHolder] = notification.useNotification();

const router = useRouter();
const userId = ref("");
const userPwd = ref("");
const sharedService = useSharedService()
const {$locally} = useNuxtApp()
const handleLogout = () => {
  //todo: 로그아웃시 userId localStore 제거
  localStorage.clear();
  $locally.setItem('userId', undefined)
  sharedService.value.userId = '';
  router.go(-1)
}

onMounted(() => {
  sharedService.value.userId = $locally.getItem('userId')
  sharedService.value.isAdmin = $locally.getItem('isAdmin')
  sharedService.value.getBoardList();

})
const showWriteModal = () => {
  sharedService.value.isWriteMode = true;
  sharedService.value.currentTitle = ''
  sharedService.value.currentContents = ''
  sharedService.value.modalVisible = true;
}


</script>
