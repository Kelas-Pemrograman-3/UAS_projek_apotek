<template>
    <q-page>
       <div class="row justify-center q-pt-md">
           <div class="col-md-6 col-xs-26">
                <q-card class="my-card">
                <q-card-section>
                    <div class="text-h4 q-pb-md"><center> registrasi </center></div>
                    <q-form
      @submit="onSubmit"
      class="q-gutter-sm"
    >
       <q-input
        filled
        type="text"
        v-model="Username"
        label= "Masukkan Username "
        lazy-rules
        :rules="[ val => val !== null && val !== '' || 'Wajib disi']"
      />

      <q-input
        filled
        type="text"
        v-model="Nama"
        label= "Masukkan Nama "
        lazy-rules
        :rules="[ val => val !== null && val !== '' || 'Wajib disi']"
      />

      <q-input
        filled
        type="text"
        v-model="Alamat"
        label= "Masukkan Alamat Anda "
        lazy-rules
        :rules="[ val => val !== null && val !== '' || 'Wajib disi']"
      />

      <q-input
        filled
        type="text"
        v-model="Email"
        label= "Masukkan Email Anda "
        lazy-rules
        :rules="[ val => val !== null && val !== '' || 'Wajib disi']"
      />

      
      <q-input
        filled
        type="text"
        v-model="Password"
        label= "Masukkan Password "
        lazy-rules
        :rules="[ val => val !== null && val !== '' || 'Wajib disi']"
      />

      <div>
        <q-btn label="SIMPAN" type="submit" color="primary"/>
      </div>
    </q-form>
                </q-card-section>
                </q-card>
           </div>
       </div>
    </q-page>
</template>

<script>
export default {
  data () {
    return {
      Username: '',
      Nama: '',
      Alamat: '',
      Email: '',
      Password: '',
    }
  },
  methods: {
    onSubmit () {
      this.$axios.post('registrasi/insert', {
        Username: this.Username,
        Nama: this.Nama,
        Alamat: this.Alamat,
        Email: this.Email,
        Password: this.Password
      }).then(res => {
        if (res.data.error) {
          this.showNotif(res.data.pesan, 'negative')
        } else {
          this.$router.push('/login')
        }
        this.Username = ''
        this.Nama = ''
        this.Alamat = ''
        this.Email = ''
        this.Password = ''
      
      })
    },
    showNotif (msg, color) {
      this.$q.notify({
        message: msg,
        color: 'color'
      })
    }
  }
}
</script>
