<template>
  <div class="container">
    <div class="page-header">
      <div class="col-md-6 col-md-offset-3">
        <h3>SignUp</h3>
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
            placeholder="아이디를 입력해 주세요 (4자리 이상)"
            value
          />
        </div>
        <div class="form-group">
          <label for="password">PASSWORD</label>
          <input
            v-model="password"
            type="password"
            class="form-control"
            name="password"
            placeholder="비밀번호를 입력해주세요 (6자리 이상)"
            value=""
          />
        </div>
        <div class="form-group mb-2">
          <label for="email">EMAIL</label>
          <input
            v-model="email"
            type="text"
            class="form-control"
            name="email"
            placeholder="이메일을 입력해주세요"
            value
          />
        </div>

        <div class="form-group text-center">
          <button
            type="button"
            id="join-submit"
            class="btn btn-primary me-2"
            @click="register"
          >
            등록하기<i class="fa fa-check spaceLeft"></i>
          </button>
          <router-link to="/signin">
            <button type="submit" class="btn btn-warning">
              뒤로가기<i class="fa fa-times spaceLeft"></i>
            </button>
          </router-link>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: '',
  components: {},
  data() {
    return {
      id: '',
      password: '',
      email: '',
    };
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    async register() {
      try {
        const data = {
          username: this.id,
          password: this.password,
          email: this.email,
          role: ['user'],
        };
        const response = await axios.post(
          'http://localhost:3000/api/auth/signup',
          data,
        );
        console.log(response);
        alert('등록성공!');
        this.$router.push({
          path: '/signin',
        });
      } catch (error) {
        alert(error.response.data.error);
      }
    },
  },
};
</script>
