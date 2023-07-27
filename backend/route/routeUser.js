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

async function addpreference(req, res) {
    try {
        await connexion.connect();
        await controllerUser.AddPreferenceUser(connexion, req, res)
    } catch (e) {
        console.log(e);
    } finally {
        await connexion.close();
    }
}

async function preference(req, res) {
    try {
        await connexion.connect();
        await controllerUser.PreferenceUser(connexion, req, res)
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
        await controllerUser.LoginUser(connexion, req, res, false)
    } catch (e) {
        console.log(e);
    } finally {
        await connexion.close();
    }
}

async function subscribe(req, res) {
    try {
        await connexion.connect();
        await controllerUser.SubScribeUser(connexion, req, res)
    } catch (e) {
        console.log(e);
    } finally {
        await connexion.close();
    }
}

function logout(req, res) {
    controllerUser.LogoutUser(req, res)
}

exports.home = home
exports.preference = preference
exports.addpreference = addpreference
exports.addnotif = addnotif
exports.notification = notification
exports.notificationID = notificationID
exports.subscribe = subscribe
exports.login = login
exports.logout = logout
exports.sendDb = sendDb