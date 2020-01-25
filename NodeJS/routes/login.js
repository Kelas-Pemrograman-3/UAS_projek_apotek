const login = require('express')()
const loginController = require('../controller/login')


login.post('/register', (req, res) => {
  loginController.insertuser(req.body)
  .then(result => {
    res.json(result)
  }).catch(err => {
    res.json(err)
  })
})

login.post('/login', (req, res)=> {
  loginController.login(req.body)
  .then(result => {
  res.json(result)
  }).catch(err => {
    res.json(err)
  })
})




// Mahasiswa.get('/getAllMhs', (req, res) => {
//   mhsController.getAllMahasiswa()
//   .then(result => {
//     res.json(result)
//   }).catch(err => {
//     res.json(err)
//   })
// })

module.exports = login