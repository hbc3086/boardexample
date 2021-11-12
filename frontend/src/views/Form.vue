<template>
  <div>
    <div class="container mt-5">
      <div class="card">
        <div class="card-header d-flex">
          <input
            v-model="title"
            type="text"
            name="id"
            placeholder="제목을 입력해주세요"
          />
          <select v-model="boardType" name="boardType" data-title="선택">
            <option value="0">-</option>
            <option value="COMMUNITY">커뮤니티</option>
            <option value="NOTICE">공지사항</option>
            <option value="FAQ">자주하는질문</option>
            <option value="INQUIRY">1:1문의</option>
          </select>
        </div>
        <div class="card-body d-flex">
          <blockquote class="blockquote mb-0">
            <textarea
              v-model="contents"
              rows="10"
              cols="50"
              name="contents"
              placeholder="내용을 입력해주세요"
            ></textarea>
          </blockquote>
        </div>
      </div>
      <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-2">
        <router-link to="/main"
          ><button type="button" class="btn btn-primary">
            목록으로
          </button></router-link
        >
        <button type="button" class="btn btn-primary" @click="form">
          등록하기
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import VueCookies from 'vue-cookies';

export default {
  name: '',
  components: {},
  data() {
    return {
      title: '',
      contents: '',
      boardType: '',
    };
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    async form() {
      try {
        const data = {
          title: this.title,
          contents: this.contents,
          boardType: this.boardType,
        };
        const response = await axios.put('http://localhost:3000/board', data, {
          headers: {
            Authorization: `Bearer ${VueCookies.get('accessToken')}`,
          },
        });
        console.log(response);
        alert('등록성공!');
        this.$router.push({
          path: '/main',
        });
      } catch (error) {
        alert(error.response.data.error);
      }
    },
  },
};
</script>
