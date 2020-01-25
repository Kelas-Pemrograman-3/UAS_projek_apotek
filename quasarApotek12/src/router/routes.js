
const routes = [
  {
    path: '/',
    component: () => import('layouts/guest.vue'),
    children: [
      { path: '', component: () => import('pages/login.vue') }
    ]
  },
  {
    path: '/home',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/Index.vue') }
    ]
  },
  {
    path: '/lihatdatatransaksi',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/Lihatdatatransaksi.vue') }
    ]
  },
  {
    path: '/input',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/inputdata.vue') }
    ]
  },
  {
    path: '/dataobat',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/dataobat.vue') }
    ]
  }
]
// Always leave this as last one

if (process.env.MODE !== 'ssr') {
  routes.push({
    path: '/inputdata',
    component: () => import('pages/Index.vue')
  })
}
export default routes
