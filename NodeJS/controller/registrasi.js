const registrasiModel = require('../models/registrasi')
const ObjectId = require ('mongoose').Types.ObjectId

exports.registrasidataobat = (data) =>
  new Promise((resolve, reject) => {
    registrasiModel.create(data)
      .then(res => {
        resolve({
          error: false,
          pesan: 'Berhasil Input Data',
          dat:res
        })
      })
      .catch(() => {
        reject({
          error: true,
          pesan: 'Gagal Input Data'
        })
      })
  })

  exports.getAll = () =>
  new Promise((resolve, reject) => {
    registrasiModel.find()
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

    exports.getByID = (id) =>
    new Promise((resolve, reject) => {
    
        registrasiModel.findOne({
          _id: ObjectId(id)
        })
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
  
    exports.edit = (id, data) =>                                 
      new Promise ((resolve,reject) => {
        registrasiModel.update({
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
  
  
  
  
      exports.delete = (id) => 
      new Promise ((resolve,reject) => {
        registrasiModelModel.deleteOne({
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