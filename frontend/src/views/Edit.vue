<template>
  <div>
    <div class="container mt-5">
      <div class="card">
        <div class="card-header d-flex">
          제목: <input type="text" v-model="board.title" />
        </div>
        <div class="card-body d-flex">
          <blockquote class="blockquote mb-0">
            <textarea v-model="board.contents" rows="10" cols="50"></textarea>
          </blockquote>
        </div>
        <div class="card-footer d-flex">
          게시일: {{ time(board.regDate / 1000) }}
        </div>
      </div>
      <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-2">
        <router-link to="/main"
          ><button type="button" class="btn btn-primary">
            목록으로
          </button></router-link
        >
        <button type="button" class="btn btn-warning" @click="form">
          수정완료
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
      board: {},
      sampleData: '',
    };
  },
  setup() {},
  created() {
    this.getboard();
  },
  mounted() {},
  unmounted() {},
  methods: {
    edit(boardSeq) {
      this.$router.push({
        path: `/edit/${boardSeq}`,
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
    async form() {
      try {
        const data = {
          boardSeq: this.board.boardSeq,
          title: this.board.title,
          contents: this.board.contents,
          boardType: this.board.boardType.code,
        };
        const response = await axios.put('http://localhost:3000/board', data, {
          headers: {
            Authorization: `Bearer ${VueCookies.get('accessToken')}`,
          },
        });
        console.log(response);
        alert('수정성공!');
        this.$router.push({
          path: '/main',
        });
      } catch (error) {
        alert(error.response.data.error);
      }
    },
    async getboard() {
      try {
        const response = await axios.get(
          `http://localhost:3000/board/${this.$route.params.id}`,
          {
            headers: {
              Authorization: `Bearer ${VueCookies.get('accessToken')}`,
            },
          },
        );
        console.log(response);
        this.board = response.data.data;
      } catch (error) {
        alert('다시 로그인해주세요');
        this.$router.push({
          path: '/signin',
        });
        console.log(error);
      }
    },
    async deleteboard() {
      try {
        const response = await axios.delete(
          `http://localhost:3000/board/${this.$route.params.id}`,
          {
            headers: {
              Authorization: `Bearer ${VueCookies.get('accessToken')}`,
            },
          },
        );
        console.log(response);
        alert('삭제되었습니다.');
        this.$router.push({
          path: '/main',
        });
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
