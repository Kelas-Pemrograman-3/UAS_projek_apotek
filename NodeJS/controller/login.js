
const loginModel = require('../models/login')
const bcrypt = require('bcryptjs')

exports.insertuser = (data) =>
  new Promise((resolve, reject) => {
    bcrypt.hash(data.Password, 10,(err, hash)=> {
      data.Password = hash
      loginModel.find({
        Username: data.Username
      }).then(hasil=> {
        if (hasil.length>0) {
        reject({
          error:true,
          pesan: 'Username sudah di gunakan'
        })
      } else {
        loginModel.create(data)
        .then(res => {
          resolve({
            error: false,
            pesan: 'Berhasil Input Data'
          })
        })
        .catch(() => {
          reject({
            error: true,
            pesan: 'Username sudah di gunakan'
          })
        })
      }
    })
    })
 
  })

  exports.login = (data) => 
  new Promise((resolve,reject)=> {
    loginModel.findOne({
      Username: data.Username
    }).then(res => {
     if (res === null) {
       reject({
         error: true,
         pesan: 'UserName Tidak Terdaftar'
       })
     } else {
       let hashPassword = res.Password
      if ( bcrypt.compareSync(data.Password, hashPassword)) {
        resolve({
          error: false,
          pesan: 'Berhasil login',
          data: res
        })
      } else {
        reject({
          error: true,
          pesan: 'Password Anda salah'
        })
      }
     }
    })
  })

  // exports.getAllMahasiswa = () =>
  // new Promise((resolve, reject) => {
  //   MahasiswaModel.find()
  //     .then(res => {
  //       resolve({
  //         error: false,
  //         pesan: 'Berhasil Mengambil Data',
  //         data: res
  //       })
  //     })
  //     .catch(() => {
  //       reject({
  //         error: true,
  //         pesan: 'Gagal Mengambil Data'
  //       })
  //     })
  // })