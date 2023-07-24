const controllerClient = require('../controller/user/controller.user')
var connexion

function sendDb(clientMongo) {
    connexion = clientMongo
}

async function home(req, res) {
    try {
        await connexion.connect();
        await controllerClient.HomeUser(connexion, req, res)
    } catch (e) {
        console.log(e);
    } finally {
        await connexion.close();
    }
}

exports.home = home
exports.sendDb = sendDb