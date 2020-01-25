const mongoose = require('mongoose')
const Schema = mongoose.Schema

const inputSchema = new Schema({
  Nama_Obat: {
    type: String
  },
  Stok: {
    type: String
  },
  Harga: {
    type: String
  }
})

module.exports = mongoose.model('obat', inputSchema)