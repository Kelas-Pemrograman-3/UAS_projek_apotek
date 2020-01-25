const userModel = require('../models/user')
const bcrypt = require('bcryptjs')

exports.postuser = (data) =>
  new Promise((resolve, reject) => {
  bcrypt.hash(data.password, 10, (err, hash) => {
    data.password = hash
    // console.log(data.password)
    userModel.find({
      username: data.username
    }).then(hasil => {
      if (hasil.length > 0) {
        reject({
          error: true,
          pesan: 'Username Sudah Digunakan'
        })
      }else{
        userModel.create(data)
        .then(res => {
          resolve({
            error: false,
            pesan: 'Berhasil Registrasi'
          })
        }).catch(() => {
          reject({
            error: true,
            pesan: 'Gagal Registrasi'
          })
        })
      }
    })
  })
})

exports.login = (data) => 
  new Promise ((resolve, reject) => {
    userModel.findOne({
      username: data.username
    }).then(res => {
      // console.log(res)
      if (res === null) {
        reject({
          error: true,
          pesan: 'Username Tidak terdaftar'
        })
      } else {
        let passwordhash = res.password
        if (bcrypt.compareSync(data.password, passwordhash)) {
          resolve({
            error: false,
            pesan: 'Berhasil Login',
            data: res
          })
        } else {
          reject({
            error: true,
            pesan: 'Password Anda Salah'
          })
        }
      }
    })
  })
