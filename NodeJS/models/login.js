const mongoose = require('mongoose')
const Schema = mongoose.Schema
loginSchema = new Schema({
  Username: {
    type: String
  },
  Password: {
    type: String
  },
  Nama: {
    type: String
  }
})

module.exports = mongoose.model('login', loginSchema)