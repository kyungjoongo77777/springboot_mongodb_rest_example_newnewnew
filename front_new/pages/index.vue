<template>
  <a-config-provider
      :theme="{
      token: {
        colorPrimary: '#50440a',
      },
    }"
  >
    <div style="margin: 30px">
      <br/>
      <div style="height: 30px"></div>
      <div style="margin: 10px" v-if="!sharedService.loading">

        <div>
          User ID
          <div style="height: 3px"/>
          <a-input v-model:value="userId" placeholder=""/>
        </div>
        <br/>

        <div>
          Password
          <div style="height: 3px"/>
          <a-input v-model:value="userPwd" v-on:keyup.enter="login(userId, userPwd, router)" placeholder=""
                   type="password"/>
        </div>
        <br/>
        <a-button @click="login(userId, userPwd, router)" type="primary" style="width: 100%">
          submit
        </a-button>
        <br/>
        <br/>
        <br/>
        <div style="font-weight: bold">
          Log-in 가능한 유저 리스트
        </div>
        <br/>
        <table border="1">
          <tr>
            <th style="background-color: orange; width: 100px;padding: 10px">
              userid
            </th>
            <th style="background-color: orange; width: 100px;">
              password
            </th>
            <th style="background-color: orange; width: 100px;">
              isAdmin
            </th>
          </tr>
          <tr v-for="item in sharedService.userList" :key="item.id" style="padding: 10px;">
            <td style="padding: 10px;flex:.25">{{ item.userId }}</td>
            <td style="padding: 10px;flex:.25">{{ item.password }}</td>
            <td style="padding: 10px;flex:.25">{{ item.isAdmin }}</td>
          </tr>
        </table>
      </div>
    </div>
  </a-config-provider>

</template>

<script setup>

import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import {useSharedService} from "~/services/SharedService.js";

const sharedService = useSharedService()

const router = useRouter();
const userId = ref("test1");
const userPwd = ref("ka1114");

onMounted(() => {
  if (localStorage.getItem("userId")) {
    router.push('/board')
  }
  sharedService.value.loading = true;
  sharedService.value.getUserList()
  sharedService.value.loading = false;
})

onBeforeMount(() => {
  if (localStorage.getItem("userId")) {
    router.push('/board')
  }

})

const login = (userId, userPwd, router) => {
  sharedService.value.handleLogin(userId, userPwd, router)
}


</script>
