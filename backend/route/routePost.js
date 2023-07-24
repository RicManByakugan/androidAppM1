const controllerUser = require('../controller/post/controller.post')
var connexion

function sendDb(clientMongo) {
    connexion = clientMongo
}


exports.sendDb = sendDb
