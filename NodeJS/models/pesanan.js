const mongoose = require('mongoose')
const Schema = mongoose.Schema

const pesananSchema = new Schema({
  namaobat: {
    type: String
  },
  jumlahobat: {
    type: String
  },
  hargaobat: {
    type: String
  },
  totalobat: {
    type: String
  }
})

module.exports = mongoose.model('pesanan', pesananSchema)