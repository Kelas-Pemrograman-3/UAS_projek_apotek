const pesananModel = require('../models/pesanan')
const ObjectId = require('mongoose').Types.ObjectId

exports.postpesanan = (data) =>
  new Promise((resolve, reject) => {
    pesananModel.create(data)
      .then(res => {
        resolve({
          error: false,
          pesan: 'Berhasil Input Data Pesanan'
        })
      })
      .catch(() => {
        reject({
          error: true,
          pesan: 'Gagal Input Data Pesanan'
        })
      })
  })

  exports.getallpesanan = () =>
  new Promise((resolve, reject) => {
    pesananModel.find()
      .then(res => {
        resolve({
          error: false,
          pesan: 'Berhasil Dapat Data',
          data: res
        })
      })
      .catch(() => {
        reject({
          error: true,
          pesan: 'Gagal Mendapatkan Data'
        })
      })
  })

  
  exports.updatepesanan = (id, data) =>
  new Promise((resolve, reject) => {
    pesananModel.update({
      _id: ObjectId(id)
    }, data).then((res) => {
      console.log(res)
      resolve({
        error: false,
        pesan: 'Berhasil Mengubah Data',
        data: res
      })
    }).catch(() => {
      reject({
        error: true,
        pesan: 'Gagal Mengubah data Data'
      })
    })
  })

  

  
exports.deletepesanan = (id) =>
new Promise((resolve, reject) => {
  pesananModel.deleteOne({
    _id: ObjectId(id)
    }).then(() => {
      resolve({
        error: false,
        pesan: 'Berhasil Menghapus Data'
      })
    }).catch(() => {
      reject({
        error: true,
        pesan: 'Data tidak terhapus'
      })
    })
  })