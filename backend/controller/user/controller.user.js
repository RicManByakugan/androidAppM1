const { ObjectID } = require("bson");
const crypto = require('crypto')
const outil = require('../../modele/outil')


async function HomeUser(clientConnex, req, res) {
    // console.log(clientConnex)
    res.send([{ message: "CONTROLLER" }])
}


exports.HomeUser = HomeUser