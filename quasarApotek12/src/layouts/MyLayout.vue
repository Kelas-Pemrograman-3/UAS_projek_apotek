<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar class="bg-green text-black" >
        <q-btn
          flat
          dense
          round
          @click="leftDrawerOpen = !leftDrawerOpen"
          icon="menu"
          class="q-mr-sm"
          aria-label="Menu"
        />

        <q-toolbar-title>
        Apotek Mesra
        </q-toolbar-title>

        <div>Quasar v{{ $q.version }}</div>
      </q-toolbar>
    </q-header>

    <q-drawer
      v-model="leftDrawerOpen"
      show-if-above
      bordered
      content-class="bg-green "
    >
      <q-list>
        <q-item-label header class= "text-white" >MENU</q-item-label>
        <q-item clickable to="/lihatdatatransaksi">
          <q-item-section>
            <q-item-label>Data Transaksi</q-item-label>
          </q-item-section>
        </q-item>
        <q-item clickable to="/input">
          <q-item-section>
            <q-item-label>Input data obat</q-item-label>
          </q-item-section>
        </q-item>
          <q-item clickable to="/dataobat">
          <q-item-section>
            <q-item-label>Data Obat</q-item-label>
             </q-item-section>
        </q-item>
          <q-item clickable tag="a"  href="http://localhost:8080/">
          <q-item-section>
            <q-item-label>Keluar</q-item-label>
          </q-item-section>
        </q-item>

      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
export default {
  name: 'MyLayout',

  data () {
    return {
      leftDrawerOpen: false
    }
  }
}
</script>
