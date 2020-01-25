const input = require('express')()
const inputController = require('../controller/cobat')

// Android relations
input.post('/postobat', (req, res) => {
  inputController.postobat(req.body)
  .then(result => {
    res.json(result)
  }).catch(err => {
    res.json(err)
  })
})

input.get('/getallobat', (req, res) => {
  inputController.getallobat()
  .then(result => {
    res.json(result)
  }).catch(err => {
    res.json(err)
  })
})
// Android relations
input.put('/updateobat/:id', (req, res) => {
  inputController.updateobat(req.params.id, req.body)
  .then(result => {
    res.json(result)
  }).catch(err =>{
    res.json(err)
  })
})

input.delete('/deleteobat/:id', (req, res) =>{
  inputController.deleteobat(req.params.id)
  .then(result => res.json(result))
  .catch(error => res.json(error))
})

// input.get('/get/:id', (req, res) => {
//   inputController.getByID(req.params.id)
//   .then(result => {
//     res.json(result)
//   }).catch(err => {
//     res.json(err)
//   })
// })

module.exports = input