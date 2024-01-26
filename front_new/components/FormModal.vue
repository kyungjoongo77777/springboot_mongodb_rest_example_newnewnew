<template>
  <div style="margin: 30px">
    <a-modal v-model:visible="sharedService.modalVisible" title="게시글 작성" @ok="handleSubmit" width="1200"
             bodyStyle="height: 500px">
      <br/>
      <div style="display: flex; flex-direction: row">
        <div style="flex:.1">
          title:
        </div>
        <div style="flex:.9">
          <a-input
              v-model:value="sharedService.currentTitle"
              placeholder="title"
          />
        </div>
      </div>
      <br/>
      <div style="display: flex; flex-direction: row">
        <div style="flex:.1">
          contents:
        </div>
        <div style="flex:.9">
          <a-textarea
              style="height: 310px;"
              size="large"
              v-model:value="sharedService.currentContents"
              placeholder="contents"/>
        </div>
      </div>
      <br/>
<!--      <div style="display: flex; flex-direction: row">-->
<!--        <div style="flex:.1">-->
<!--          author:-->
<!--        </div>-->
<!--        <div style="flex:.9">-->
<!--          {{}}-->
<!--        </div>-->
<!--      </div>-->
    </a-modal>
  </div>

</template>

<script setup>
import {useRouter} from "vue-router";
import {useSharedService} from "~/services/SharedService.js";

const router = useRouter();
const sharedService = useSharedService();


const handleSubmit = async () => {
  //alert(sharedService.value.currentTitle)
  if (sharedService.value.isWriteMode) {
    await sharedService.value.postBoardOne()
    sharedService.value.modalVisible = false;
  } else {//todo: 수정 모드 일떄.
    await sharedService.value.putBoardOne();
  }

}


</script>
