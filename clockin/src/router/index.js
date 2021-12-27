import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login'
import ManagerHome from '../views/ManagerHome'
import UserHome from '../views/UserHome'

import UserControl from '../../src/components/manager/UserControl'
import NoticeControl from '../../src/components/manager/NoticeControl'
import ClockControl from '../../src/components/manager/ClockControl'
import MakeUpControl from '../../src/components/manager/MakeUpControl'
import CountControl from '../../src/components/manager/CountControl'

import UserInfo from '../../src/components/usermanage/UserInfo.vue'
import BuKa from '../../src/components/usermanage/BuKa.vue'
import Notice from '../../src/components/usermanage/Notice.vue'
import QianDao from '../../src/components/usermanage/QianDao.vue'
import UpdatePassword from '../../src/components/usermanage/UpdatePassword.vue'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/manager',
    name: 'ManagerHome',
    component: ManagerHome,
    children:[
        {
          path: '/usercontrol',
          name: 'UserControl',
          component: UserControl
        },
        {
            path: '/noticecontrol',
            name: 'NoticeControl',
            component: NoticeControl
        },
        {
            path: '/clockcontrol',
            name: 'ClockControl',
            component: ClockControl
        },
        {
            path: '/makeupcontrol',
            name: 'MakeUpControl',
            component: MakeUpControl
        },
        {
            path: '/countcontrol',
            name: 'CountControl',
            component: CountControl
        }
    ]
  },
  {
    path: '/user',
    name: 'UserHome',
    component: UserHome,
    children:[
        {
          path: '/userinfo',
          name: 'userinfo',
          component: UserInfo
        },
        {
          path: '/buka',
          name: 'buka',
          component: BuKa
        },
        {
          path: '/notice',
          name: 'notice',
          component: Notice
        },
        {
          path: '/qiandao',
          name: 'qiandao',
          component: QianDao
        },
        {
          path: '/updatepassword',
          name: 'updatepassword',
          component: UpdatePassword
        },
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router
