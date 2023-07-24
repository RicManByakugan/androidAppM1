const { ObjectID } = require("bson");
const crypto = require('crypto')
const outil = require('../../modele/outil')


async function HomeUser(clientConnex, req, res) {
    if (this.session) {
        await clientConnex.db("WM").collection('User').findOne({ _id: new ObjectID(this.session) })
            .then(resultat => {
                res.send([{ message: "USER CONNECTED", user: resultat, session: this.session }])
            })
            .catch(err => {
                res.send([{ message: "REQUEST ERROR" }])
            })
    } else {
        res.send([{ message: "USER NOT CONNECTED" }])
    }
}


async function LoginUser(clientConnex, res, req) {
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

async function SubScribeUser(clientConnex, res, req) {
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

exports.SubScribeUser = SubScribeUser
exports.LoginUser = LoginUser
exports.LogoutUser = LogoutUser
exports.HomeUser = HomeUser