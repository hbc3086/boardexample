import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    redirect: 'signin',
  },
  {
    path: '/signin',
    name: 'SignIn',

    component: () =>
      import(/* webpackChunkName: "about" */ '../views/SignIn.vue'),
  },
  {
    path: '/signup',
    name: 'SignUp',

    component: () =>
      import(/* webpackChunkName: "about" */ '../views/SignUp.vue'),
  },
  {
    path: '/main',
    name: 'Main',

    component: () =>
      import(/* webpackChunkName: "about" */ '../views/Main.vue'),
  },
  {
    path: '/detail/:id',
    name: 'Detail',

    component: () =>
      import(/* webpackChunkName: "about" */ '../views/Detail.vue'),
  },
  {
    path: '/form',
    name: 'Form',

    component: () =>
      import(/* webpackChunkName: "about" */ '../views/Form.vue'),
  },
  {
    path: '/edit/:id',
    name: 'Edit',

    component: () =>
      import(/* webpackChunkName: "about" */ '../views/Edit.vue'),
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
