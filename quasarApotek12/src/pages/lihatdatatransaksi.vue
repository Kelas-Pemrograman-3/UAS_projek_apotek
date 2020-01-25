<template>
  <q-page padding>
   <q-card>
      <q-table
      title="DATA TRANSAKSI"
      :data="data"
      :columns="columns"
      row-key="id"
      :pagination.sync="pagination"
      :loading="loading"
      :filter="filter"
      @request="onRequest"
      binary-state-sort
    >

      <template v-slot:top-right>
        <q-input v-model="filter" placeholder="Search">
          <template v-slot:append>
            <q-icon name="search" />
          </template>
        </q-input>
      </template>
      <template v-slot:body="props">
          <q-tr :props="props">
            <q-td key="namaobat" :props="props">{{ props.row.namaobat }}</q-td>
            <q-td key="jumlahobat" :props="props">{{ props.row.jumlahobat }}</q-td>
            <q-td key="hargaobat" :props="props">{{ props.row.hargaobat }}</q-td>
            <q-td key="totalobat" :props="props">{{ props.row.totalobat }}</q-td>
            <q-td key="Aksi" :props="props">
              <q-card-actions align="around" class="row q-col-gutter-md no-wrap">
                <div class="col q-gutter-md">
                  <q-btn round @click="opendialog(props.row)" color="secondary" icon="edit"></q-btn>
                   <q-btn round @click="confirm(props.row._id)" color="negative" icon="delete"></q-btn>
                </div>
              </q-card-actions>
            </q-td>
          </q-tr>
        </template>

    </q-table>
   </q-card>
   <q-dialog v-model="openedit" v-if="openedit">
      <q-card style="width: 700px; max-width:80vw;">
        <q-card-section>
          <div class="text-h6">Edit Data</div>
        </q-card-section>

        <q-separator/>

          <q-card-selection style="max-height: 50vh" class="scroll">
          <q-input label ="namaobat" v-model ="activedata.namaobat"></q-input>
          <q-input label ="jumlahobat" v-model ="activedata.jumlahobat"></q-input>
          <q-input label ="hargaobat" v-model ="activedata.hargaobat"></q-input>
          <q-input label ="totalobat" v-model ="activedata.totalobat"></q-input>
          <q-input label ="Aksi" v-model ="activedata.Aksi"></q-input>
          </q-card-selection>

        <q-separator />

        <q-card-actions align="right">
          <q-btn flat label="Cancel" color="primary" v-close-popup />
          <q-btn flat label="Edit" color="primary" @click="edit()" />
        </q-card-actions>
      </q-card>
    </q-dialog>
  </q-page>
</template>

<script>
export default {
  name: 'PageIndex',
  data () {
    return {
      filter: '',
      loading: false,
      pagination: {
        sortBy: 'name',
        descending: false,
        page: 1,
        rowsPerPage: 5,
        rowsNumber: 10
      },
      columns: [
        {

        },
        { name: 'namaobat', align: 'left', label: 'Nama Obat', field: 'namaobat', sortable: true },
        { name: 'jumlahobat', label: 'Jumlah Obat', field: 'jumlahobat', sortable: true },
        { name: 'hargaobat', label: 'Harga Obat', field: 'hargaobat', sortable: true },
        { name: 'totalobat', label: 'Uang Anda', field: 'totalobat', sortable: true },
        { name: 'Aksi', label: 'Aksi', field: 'Aksi' }
      ],
      data: [],
      original: [],
      openedit: false,
      activedata: null
    }
  },
  created () {
    this.getData()
  },
  mounted () {
    this.onRequest({
      pagination: this.pagination,
      filter: undefined
    })
  },
  methods: {
    getData () {
      this.$axios.get('pesanan/getallpesanan')
        .then(res => {
          this.original = res.data.data
        })
    },
    onRequest (props) {
      let { page, rowsPerPage, rowsNumber, sortBy, descending } = props.pagination
      let filter = props.filter

      this.loading = true

      // emulate server
      setTimeout(() => {
        // update rowsCount with appropriate value
        this.pagination.rowsNumber = this.getRowsNumberCount(filter)

        // get all rows if "All" (0) is selected
        let fetchCount = rowsPerPage === 0 ? rowsNumber : rowsPerPage

        // calculate starting row of data
        let startRow = (page - 1) * rowsPerPage

        // fetch data from "server"
        let returnedData = this.fetchFromServer(startRow, fetchCount, filter, sortBy, descending)

        // clear out existing data and add new
        this.data.splice(0, this.data.length, ...returnedData)

        // don't forget to update local pagination object
        this.pagination.page = page
        this.pagination.rowsPerPage = rowsPerPage
        this.pagination.sortBy = sortBy
        this.pagination.descending = descending

        // ...and turn of loading indicator
        this.loading = false
      }, 1500)
    },
    fetchFromServer (startRow, count, filter, sortBy, descending) {
      let data = []

      if (!filter) {
        data = this.original.slice(startRow, startRow + count)
      } else {
        let found = 0
        for (let index = startRow, items = 0; index < this.original.length && items < count; ++index) {
          let row = this.original[index]
          // match filter?
          if (!row['name'].includes(filter)) {
            // get a different row, until one is found
            continue
          }
          ++found
          if (found >= startRow) {
            data.push(row)
            ++items
          }
        }
      }

      // handle sortBy
      if (sortBy) {
        data.sort((a, b) => {
          let x = descending ? b : a
          let y = descending ? a : b
          if (sortBy === 'desc') {
            // string sort
            return x[sortBy] > y[sortBy] ? 1 : x[sortBy] < y[sortBy] ? -1 : 0
          } else {
            // numeric sort
            return parseFloat(x[sortBy]) - parseFloat(y[sortBy])
          }
        })
      }

      return data
    },
    getRowsNumberCount (filter) {
      if (!filter) {
        return this.original.length
      }
      let count = 0
      this.original.forEach((treat) => {
        if (treat['name'].includes(filter)) {
          ++count
        }
      })
      return count
    },
    opendialog (data) {
      this.openedit = true
      this.activedata = data
    },
    edit () {
      this.$axios.put('pesanan/updatepesanan/' + this.activedata._id, this.activedata)
        .then(res => {
          if (res.data.error) {
            this.showNotif(res.data.pesan, 'negative')
          } else {
            this.showNotif(res.data.pesan, 'positive')
            this.openedit = false
            this.getData()
          }
        })
    },
    showNotif (msg, color) {
      this.$q.notify({
        message: msg,
        color: color
      })
    },
    confirm (id) {
      this.$q.dialog({
        title: 'Confirm',
        message: 'apakah anda yakin',
        cancel: true,
        persistent: true
      }).onOk(() => {
        this.$axios.delete('pesanan/deletepesanan/' + id)
          .then(res => {
            if (res.data.error) {
              this.showNotif(res.data.pesan, 'negative')
            } else {
              this.showNotif(res.data.pesan, 'positive')
              this.getData()
            }
          })
      })
    }
  }
}
</script>
