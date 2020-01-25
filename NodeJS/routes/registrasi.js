

      const registrasi = require('express')()
const registrasiController = require('../controller/registrasi')

registrasi.post('/insert', (req, res) => {
  registrasiController.registrasidataobat(req.body)
  .then(result => {
    res.json(result)
  }).catch(err => {
    res.json(err)
  })
})

registrasi.get('/getAll', (req, res) => {
  registrasiController.getAll()
  .then(result => {
    res.json(result)
  }).catch(err => {
    res.json(err)
  })
})

registrasi.get('/get/:id', (req, res) => {
  registrasiController.getByID(req.params.id)
  .then(result => {
    res.json(result)
  }).catch(err => {
    res.json(err)
  })
})

registrasi.put('/edit/:id', (req, res) => {
  registrasiController.edit(req.params.id, req.body)
  .then(result => {
    res.json(result)
  }).catch(err =>{
    res.json(err)
  })
})

registrasi.delete('/delete/:id', (req, res) =>{
  registrasiController.delete(req.params.id)
  .then(result => res.json(result))
  .catch(error => res.json(error))
})

module.exports = registrasi