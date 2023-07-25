const controllerUser = require('../controller/user/controller.user')
var connexion

function sendDb(clientMongo) {
    connexion = clientMongo
}

async function home(req, res) {
    try {
        await connexion.connect();
        await controllerUser.HomeUser(connexion, req, res)
    } catch (e) {
        console.log(e);
    } finally {
        await connexion.close();
    }
}

async function notification(req, res) {
    try {
        await connexion.connect();
        await controllerUser.NotificationUser(connexion, req, res)
    } catch (e) {
        console.log(e);
    } finally {
        await connexion.close();
    }
}

async function addnotif(req, res) {
    try {
        await connexion.connect();
        await controllerUser.AddNotificationUser(connexion, req, res)
    } catch (e) {
        console.log(e);
    } finally {
        await connexion.close();
    }
}

async function notificationID(req, res) {
    try {
        await connexion.connect();
        await controllerUser.NotificationUserIDWatch(connexion, req, res)
    } catch (e) {
        console.log(e);
    } finally {
        await connexion.close();
    }
}

async function login(req, res) {
    try {
        await connexion.connect();
        await controllerUser.LoginUser(connexion, res, req, false)
    } catch (e) {
        console.log(e);
    } finally {
        await connexion.close();
    }
}

async function subscribe(req, res) {
    try {
        await connexion.connect();
        await controllerUser.SubScribeUser(connexion, res, req)
    } catch (e) {
        console.log(e);
    } finally {
        await connexion.close();
    }
}

function logout(req, res) {
    controllerUser.LogoutUser(res, req)
}

exports.home = home
exports.addnotif = addnotif
exports.notification = notification
exports.notificationID = notificationID
exports.subscribe = subscribe
exports.login = login
exports.logout = logout
exports.sendDb = sendDb