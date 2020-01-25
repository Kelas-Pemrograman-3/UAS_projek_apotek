const mongoose = require('mongoose')
const Schema = mongoose.Schema
registrasiSchema = new Schema({
  Username: {
    type: String
  },
  Nama: {
    type: String
  },
  Email: {
    type: String
  },
Alamat: {
  type: String
},
Password: {
  type: String
}
})

module.exports = mongoose.model('registrasi', registrasiSchema)