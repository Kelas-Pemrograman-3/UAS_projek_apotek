const inputModel = require('../models/obat')
const ObjectId = require('mongoose').Types.ObjectId

exports.postobat = (data) =>
  new Promise((resolve, reject) => {
    inputModel.create(data)
      .then(res => {
        resolve({
          error: false,
          pesan: 'Berhasil Input Data'
        })
      })
      .catch(() => {
        reject({
          error: true,
          pesan: 'Gagal Input Data'
        })
      })
  })

exports.getallobat = () =>
  new Promise((resolve, reject) => {
    inputModel.find()
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
  
  exports.updateobat = (id, data) =>
  new Promise((resolve, reject) => {
    inputModel.update({
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

  

  
exports.deleteobat = (id) =>
new Promise((resolve, reject) => {
  inputModel.deleteOne({
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
    
    // exports.getByID = (id) =>
    //   new Promise((resolve, reject) => {
    
    //     transaksiModel.findOne({
    //         _id: ObjectId(id)
    //       })
    //       .then(res => {
    //         resolve({
    //           error: false,
    //           pesan: 'Berhasil Dapat Data',
    //           data: res
    //         })
    //       })
    //       .catch(() => {
    //         reject({
    //           error: true,
    //           pesan: 'Gagal Mendapatkan Data'
    //         })
    //       })
    //   })