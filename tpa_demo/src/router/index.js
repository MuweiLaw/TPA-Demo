import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Hello from '@/view/Hello'
import Music from '@/view/Music'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/hello',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/music',
      name: 'Music',
      component: Music
    }
  ]
})
