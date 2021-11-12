<template>
  <body>
    <article class="container">
      <div class="page-header">
        <div class="col-md-6 col-md-offset-3">
          <h3>SignIn</h3>
        </div>
      </div>
      <div class="col-sm-6 col-md-offset-3">
        <form role="form">
          <div class="form-group">
            <label for="id">ID</label>
            <input
              v-model="id"
              type="text"
              class="form-control"
              name="id"
              placeholder="아이디를 입력해 주세요"
            />
          </div>
          <div class="form-group mb-2">
            <label for="password">PASSWORD</label>
            <input
              v-model="password"
              type="password"
              class="form-control"
              id="password"
              placeholder="비밀번호를 입력해주세요"
            />
          </div>

          <div class="form-group text-center">
            <router-link to="/signup">
              <button
                type="button"
                id="join-submit"
                class="btn btn-primary me-2"
              >
                회원가입<i class="fa fa-check spaceLeft"></i>
              </button>
            </router-link>
            <button type="button" class="btn btn-warning" @click="login">
              로그인<i class="fa fa-times spaceLeft"></i>
            </button>
          </div>
        </form>
      </div>
    </article>
  </body>
</template>

<script>
import axios from 'axios';
import VueCookies from 'vue-cookies';

export default {
  name: '',
  components: {},
  data() {
    return {
      id: '',
      password: '',
    };
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    async login() {
      try {
        const data = {
          username: this.id,
          password: this.password,
        };
        const response = await axios.post(
          'http://localhost:3000/api/auth/signin',
          data,
        );
        console.log(response);
        alert('로그인성공!');
        VueCookies.set('username', response.data.username, '1d');
        VueCookies.set('accessToken', response.data.accessToken, '1d');
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
