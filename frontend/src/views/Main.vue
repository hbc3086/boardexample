<template>
  <div class="container">
    <div class="float-start">
      <select
        @change="change"
        v-model="boardType"
        name="boardType"
        data-title="선택"
      >
        <option value="">전체</option>
        <option value="COMMUNITY">커뮤니티</option>
        <option value="NOTICE">공지사항</option>
        <option value="FAQ">자주하는질문</option>
        <option value="INQUIRY">1:1문의</option>
      </select>
    </div>
    <div class="float start">
      <input
        v-model="keyword"
        type="text"
        class="search"
        placeholder="제목을 입력해주세요"
      />
      <button type="button" class="btn btn-primary" @click="search">
        검색하기
      </button>
    </div>
    <div class="float-end">
      {{ username }}님 어서오세요
      <button type="button" class="btn btn-primary" @click="logout">
        로그아웃
      </button>
    </div>
    <table class="table table-dark table-striped">
      <thead>
        <tr>
          <th scope="col">번호</th>
          <th scope="col">종류</th>
          <th scope="col">제목</th>
          <th scope="col">게시일</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(board, i) in boardlist" :key="i">
          <th scope="row">{{ board.boardSeq }}</th>
          <td>{{ board.boardType.label }}</td>
          <td>
            <a @click="detail(board.boardSeq)" style="cursor: pointer">{{
              board.title
            }}</a>
          </td>
          <td>{{ time(board.regDate / 1000) }}</td>
        </tr>
      </tbody>
    </table>
    <div class="float-end">
      <router-link to="/form">
        <button type="button" class="btn btn-primary">등록하기</button>
      </router-link>
    </div>
  </div>
</template>

<script>
import VueCookies from 'vue-cookies';
import axios from 'axios';

export default {
  name: '',
  components: {},
  data() {
    return {
      keyword: '',
      boardType: '',
      boardlist: [],
      username: VueCookies.get('username'),
    };
  },
  setup() {},
  created() {
    this.getboard();
  },
  mounted() {},
  unmounted() {},
  methods: {
    async search() {
      try {
        const response = await axios.get('http://localhost:3000/board', {
          params: { boardTypes: this.boardType, keyword: this.keyword },
          headers: {
            Authorization: `Bearer ${VueCookies.get('accessToken')}`,
          },
        });
        console.log(response);
        this.boardlist = response.data.data;
      } catch (error) {
        alert('다시 로그인해주세요');
        this.$router.push({
          path: '/signin',
        });
        console.log(error);
      }
    },
    async change() {
      try {
        const response = await axios.get('http://localhost:3000/board', {
          params: { boardTypes: this.boardType },
          headers: {
            Authorization: `Bearer ${VueCookies.get('accessToken')}`,
          },
        });
        console.log(response);
        this.boardlist = response.data.data;
      } catch (error) {
        alert('다시 로그인해주세요');
        this.$router.push({
          path: '/signin',
        });
        console.log(error);
      }
    },
    detail(boardSeq) {
      this.$router.push({
        path: `/detail/${boardSeq}`,
      });
    },
    time(t) {
      var myDate = new Date(t * 1000);
      var date =
        myDate.getFullYear() +
        '-' +
        (myDate.getMonth() + 1) +
        '-' +
        myDate.getDate() +
        ' ' +
        myDate.getHours() +
        '시' +
        myDate.getMinutes() +
        '분';
      return date;
    },
    logout() {
      VueCookies.remove('username');
      VueCookies.remove('accessToken');
      alert('로그아웃 되었습니다.');
      this.$router.push({
        path: '/signin',
      });
    },
    async getboard() {
      try {
        const response = await axios.get('http://localhost:3000/board', {
          headers: {
            Authorization: `Bearer ${VueCookies.get('accessToken')}`,
          },
        });
        console.log(response);
        this.boardlist = response.data.data;
      } catch (error) {
        alert('다시 로그인해주세요');
        this.$router.push({
          path: '/signin',
        });
        console.log(error);
      }
    },
  },
};
</script>
