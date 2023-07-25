const { ObjectID } = require("bson");

async function AddPost(clientConnex, req, res) {
    await clientConnex.db("WM").collection('Post').insertOne(req.body)
        .then(resultat => {
            res.send({ message: "POST ADD SUCCESSFULLY" })
        })
        .
        catch(err => res.send({ message: "POST ADD FAILED", detailled: "INVALID INFORMATION", err: err }))
}

async function GetAllPost(clientConnex, req, res) {
    await clientConnex.db("WM").collection('Post').find().toArray()
        .then(resultat => {
            if (resultat) {
                res.send(resultat)
            } else {
                res.send({ message: "DATA EMPTY" })
            }
        })
        .catch(err => {
            res.send({ message: "REQUEST ERROR" })
        })
}

async function GetPostID(clientConnex, req, res) {
        await clientConnex.db("WM").collection('Post').findOne({ _id: new ObjectID(req.params.idPost) })
            .then(resss => {
                if (resss) {
                    res.send(resss)
                } else {
                    res.send({ message: "EMPTY" })
                }
            })
            .catch(err => {
                res.send({ message: "REQUEST ERROR" })
            })
}


async function GetSearch(clientConnex, req, res) {
    if (req.body.cleSearch) {
        await clientConnex.db("WM").collection('Post').findOne({
            $or: [
                { numero: req.body.cleSearch },
                { modele: req.body.cleSearch },
                { marque: req.body.cleSearch },
                { annee: req.body.cleSearch }
            ]
        })
            .then(resss => {
                if (resss) {
                    res.send(resss)
                } else {
                    res.send({ message: "EMPTY" })
                }
            })
            .catch(err => {
                res.send({ message: "REQUEST ERROR" })
            })
    }
}

exports.AddPost = AddPost
exports.GetSearch = GetSearch
exports.GetAllPost = GetAllPost
exports.GetPostID = GetPostID
