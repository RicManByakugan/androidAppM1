const controllerPost = require('../controller/post/controller.post')
var connexion

function sendDb(clientMongo) {
    connexion = clientMongo
}

async function addpost(req, res) {
    try {
        await connexion.connect();
        await controllerPost.AddPost(connexion, req, res)
    } catch (e) {
        console.log(e);
    } finally {
        await connexion.close();
    }
}



exports.addpost = addpost
exports.sendDb = sendDb
