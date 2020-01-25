const pesanan = require('express')()
const pesananController = require('../controller/cpesanan')

// Android relations
pesanan.post('/postpesanan', (req, res) => {
  pesananController.postpesanan(req.body)
  .then(result => {
    res.json(result)
  }).catch(err => {
    res.json(err)
  })
})

pesanan.get('/getallpesanan', (req, res) => {
  pesananController.getallpesanan()
  .then(result => {
    res.json(result)
  }).catch(err => {
    res.json(err)
  })
})

pesanan.put('/updatepesanan/:id', (req, res) => {
  pesananController.updatepesanan(req.params.id, req.body)
  .then(result => {
    res.json(result)
  }).catch(err =>{
    res.json(err)
  })
})

pesanan.delete('/deletepesanan/:id', (req, res) =>{
  pesananController.deletepesanan(req.params.id)
  .then(result => res.json(result))
  .catch(error => res.json(error))
})

module.exports = pesanan