const mongoose = require('mongoose')
const schema = mongoose.Schema
const userSchema = new schema({
  username: {
    type: String,
    index: {
      unique: true
    }
  },
  nama: {
    type: String
  },
  password: {
    type: String
  }
})

module.exports = mongoose.model('user', userSchema)