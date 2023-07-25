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

async function getallpost(req, res) {
    try {
        await connexion.connect();
        await controllerPost.GetAllPost(connexion, req, res)
    } catch (e) {
        console.log(e);
    } finally {
        await connexion.close();
    }
}

async function getpost(req, res) {
    try {
        await connexion.connect();
        await controllerPost.GetPostID(connexion, req, res)
    } catch (e) {
        console.log(e);
    } finally {
        await connexion.close();
    }
}


async function getsearch(req, res) {
    try {
        await connexion.connect();
        await controllerPost.GetSearch(connexion, req, res)
    } catch (e) {
        console.log(e);
    } finally {
        await connexion.close();
    }
}



exports.getallpost = getallpost
exports.getpost = getpost
exports.getsearch = getsearch
exports.addpost = addpost
exports.sendDb = sendDb
