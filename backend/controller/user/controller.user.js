const { ObjectID } = require("bson");
const crypto = require('crypto')
const outil = require('../../modele/outil')


async function PreferenceUser(clientConnex, req, res) {
    if (this.session) {
        nextStep = false
        dataPref = {}
        await clientConnex.db("WM").collection('Preference').findOne({ userId: new ObjectID(this.session) })
            .then(resultat => {
                dataPref = resultat
                nextStep = true
            })
            .catch(err => {
                res.send([{ message: "REQUEST ERROR" }])
            })

        if (nextStep) {
            await clientConnex.db("WM").collection('User').findOne({ _id: new ObjectID(this.session) })
                .then(resultat => {
                    res.send([{ user: resultat, preference: dataPref }])
                })
                .catch(err => {
                    res.send([{ message: "REQUEST ERROR" }])
                })
        }
    } else {
        res.send([{ message: "USER NOT CONNECTED" }])
    }
}

async function AddPreferenceUser(clientConnex, req, res) {
    if (req.body.userId !== undefined) {
        await clientConnex.db("WM").collection('Preference').insertOne(req.body)
            .then(resultat => {
                res.send({ message: "PREFERENCE ADD SUCCESSFULLY" })
            })
            .catch(err => res.send({ message: "SUBSCRIBE FAILED", detailled: "INVALID INFORMATION", err: err }))
    } else {
        res.send({ message: "SUBSCRIBE FAILED", detailled: "INVALID INFORMATION" })
    }
}

async function HomeUser(clientConnex, req, res) {
    if (this.session) {
        await clientConnex.db("WM").collection('User').findOne({ _id: new ObjectID(this.session) })
            .then(resultat => {
                res.send([{ message: "USER CONNECTED", user: resultat }])
            })
            .catch(err => {
                res.send([{ message: "REQUEST ERROR" }])
            })
    } else {
        res.send([{ message: "USER NOT CONNECTED" }])
    }
}

async function NotificationUserIDWatch(clientConnex, req, res) {
    if (this.session) {
        var continueVar = false
        var updateit = false
        var resUserN = {}
        var resFinal = {}
        await clientConnex.db("WM").collection('User').findOne({ _id: new ObjectID(this.session) })
            .then(resultat => {
                continueVar = true
                resUserN = resultat
            })
            .catch(err => {
                res.send([{ message: "REQUEST ERROR" }])
            })
        
        if (continueVar) {
                await clientConnex.db("WM").collection('Notification').findOne({ _id: new ObjectID(req.params.idNotif) })
                    .then(resNotif => {
                        if (resNotif) {
                            updateit = true
                            resFinal = resNotif
                        } else {
                            res.send({ message: "EMPTY" })
                        }
                    })
                    .catch(err => {
                        res.send({ message: "REQUEST ERROR" })
                    })
        }
        if (updateit) {
            updateDoc = {
                $set: {
                    viewUser: true,
                }
            };
            options = { upsert: true };
            await clientConnex.db("WM").collection('Notification').updateOne({ _id: new ObjectID(req.params.idNotif) }, updateDoc, options)
                .then(resUpdate => {
                    if (resNotif) {
                        res.send(resFinal)
                    } else {
                        res.send({ message: "EMPTY" })
                    }
                })
                .catch(err => {
                    res.send({ message: "REQUEST ERROR" })
                })
        }
    } else {
        res.send([{ message: "USER NOT CONNECTED" }])
    }
}

async function NotificationUser(clientConnex, req, res) {
    if (this.session) {
        var continueVar = false
        var resUserN = {}
        await clientConnex.db("WM").collection('User').findOne({ _id: new ObjectID(this.session) })
            .then(resultat => {
                continueVar = true
                resUserN = resultat
            })
            .catch(err => {
                res.send([{ message: "REQUEST ERROR" }])
            })
        
        if (continueVar) {
                await clientConnex.db("WM").collection('Notification').find({ client: resUserN }).toArray()
                    .then(resNotif => {
                        if (resNotif) {
                            res.send(resNotif)
                        } else {
                            res.send({ message: "EMPTY" })
                        }
                    })
                    .catch(err => {
                        res.send({ message: "REQUEST ERROR" })
                    })
            }
    } else {
        res.send([{ message: "USER NOT CONNECTED" }])
    }
}

async function LoginUser(clientConnex, req, res) {
    await clientConnex.db("WM").collection('User').findOne({ logname: req.body.logName })
        .then(resultat => {
            if (resultat) {
                if (req.body.logName !== undefined) {
                    let hashPassword = crypto.createHash('md5').update(req.body.password).digest("hex")
                    if (resultat.logname === req.body.logName && resultat.password === hashPassword) {
                        req.session.clientId = resultat._id
                        this.session = resultat._id
                        res.send({ message: "LOGIN SUCCESSFULLY" })
                    } else {
                        res.send({ message: "LOGIN FAILED", detailled: "LOGIN NAME INVALID" })
                    }
                } else {
                    res.send({ message: "LOGIN FAILED", detailled: "LOGIN NAME INVALID" })
                }
            } else {
                res.send({ message: "LOGIN FAILED", detailled: "INFORMATION NOT FOUND" })
            }
        })
        .catch(err => {
            res.send({ message: "REQUEST ERROR" })
        })
}

async function AddNotificationUser(clientConnex, req, res) {
    if (req.body.message !== undefined && req.body.user !== undefined) {
        req.body.dateNotif = new Date()
        req.body.viewUser = false
        await clientConnex.db("WM").collection('Notification').insertOne(req.body)
            .then(resultat => {
                res.send({ message: "NOTIFICATION ADD SUCCESSFULLY" })
            })
            .catch(err => res.send({ message: "SUBSCRIBE FAILED", detailled: "INVALID INFORMATION", err: err }))
    } else {
        res.send({ message: "SUBSCRIBE FAILED", detailled: "INVALID INFORMATION" })
    }
}


async function SubScribeUser(clientConnex, req, res) {
    if (req.body.name !== undefined && req.body.firstname !== undefined && req.body.logname !== undefined && req.body.password !== undefined) {
        req.body.dateSubscribe = new Date()
        let hashPassword = crypto.createHash('md5').update(req.body.password).digest("hex")
        req.body.password = hashPassword
        continueVar = false
        await clientConnex.db("WM")
            .collection('User').findOne({ email: req.body.logname })
            .then(resUser => {
                if (resUser) {
                    res.send({ message: "SUBSCRIBE FAILED", detailled: "LOGNAME ALREADY USED" })
                    req.body.name = req.body.name.toUpperCase()
                    req.body.firstname = req.body.firstname.toUpperCase()
                    continueVar = false
                } else {
                    continueVar = true
                }
            })
            .catch(err => res.send({ message: "SUBSCRIBE FAILED", detailled: "PROCESS FAILED" }))

        if (continueVar) {
            await clientConnex.db("WM").collection('User').insertOne(req.body)
                .then(resultat => {
                    res.send({ message: "SUBSCRIBE SUCCESSFULLY" })
                })
                .catch(err => res.send({ message: "SUBSCRIBE FAILED", detailled: "INVALID INFORMATION", err: err }))
        }
    } else {
        res.send({ message: "SUBSCRIBE FAILED", detailled: "INVALID INFORMATION" })
    }
}

function LogoutUser(res, req) {
    req.session.destroy()
    this.session = null
    res.send({ message: "LOGOUT SUCCESSFULLY" })
}

exports.AddNotificationUser = AddNotificationUser
exports.NotificationUser = NotificationUser
exports.NotificationUserIDWatch = NotificationUserIDWatch
exports.SubScribeUser = SubScribeUser
exports.LoginUser = LoginUser
exports.LogoutUser = LogoutUser
exports.PreferenceUser = PreferenceUser
exports.AddPreferenceUser = AddPreferenceUser
exports.HomeUser = HomeUser