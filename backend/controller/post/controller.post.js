const { ObjectID } = require("bson");

async function AddPost(clientConnex, req, res) {
    await clientConnex.db("WM").collection('Post').insertOne(req.body)
        .then(resultat => {
            res.send({ message: "POST ADD SUCCESSFULLY" })
        })
        .
        catch(err => res.send({ message: "POST ADD FAILED", detailled: "INVALID INFORMATION", err: err }))
}

async function GetAllPostVideo(clientConnex, req, res) {
    await clientConnex.db("WM").collection('PostVideo').find().toArray()
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

async function GetPostIDVideo(clientConnex, req, res) {
        if (typeof req.params.id === "string"){
            await clientConnex.db("WM").collection('PostVideo').findOne({ _id: new ObjectID(req.params.id) })
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
        }else{
            res.send({ message: "REQUEST TYPE ERROR" })
        }
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
        if (typeof req.params.id === "string"){
            var updateTrue = false
            var lastFind = false
            var resssF = {}
            await clientConnex.db("WM").collection('Post').findOne({ _id: new ObjectID(req.params.id) })
                .then(resss => {
                    if (resss) {
                        if (!resss.visite == null) {
                            updateDoc = {
                                $set: {
                                    visite: parseInt(resss.visite) + 1,
                                }
                            };
                        }else if(resss.visite <= 1 || resss.visite <= 10000000){
                            updateDoc = {
                                $set: { 
                                    visite: parseInt(resss.visite) + 1,
                                }
                            };
                        }else{
                            updateDoc = {
                                $set: {
                                    visite: 1,
                                }
                            };
                        }
                        options = { upsert: true };
                        updateTrue = true
                        resssF = resss
                    } else {
                        res.send({ message: "EMPTY" })
                    }
                })
                .catch(err => {
                    res.send({ message: "REQUEST ERROR" })
                })
            if (updateTrue) {
                await clientConnex.db("WM").collection('Post').updateOne({ _id: new ObjectID(req.params.id) }, updateDoc, options)
                    .then(resssU => {
                        lastFind = true
                    })
                    .catch(err => {
                        res.send({ message: "REQUEST ERROR" })
                    })
            }

            if (lastFind) {
                await clientConnex.db("WM").collection('Post').findOne({ _id: new ObjectID(req.params.id) })
                .then(resF => {
                    res.send(resF)
                })
                .catch(err => {
                    res.send({ message: "REQUEST ERROR" })
                })
            }

        }else{
            res.send({ message: "REQUEST TYPE ERROR" })
        }
}

async function GetSearch(clientConnex, req, res) {
    if (req.body.cleSearch) {
        const query = {$or: [{title: { $regex: req.body.cleSearch.toString(), $options: 'i' }}, {Lieu: { $regex: req.body.cleSearch.toString(), $options: 'i' }}]};
        await clientConnex.db("WM").collection('Post').find(query).toArray()
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
exports.GetAllPostVideo = GetAllPostVideo
exports.GetPostIDVideo = GetPostIDVideo
