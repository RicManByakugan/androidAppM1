async function AddPost(clientConnex, res, req) {
    await clientConnex.db("WM").collection('Post').insertOne(req.body)
        .then(resultat => {
            res.send({ message: "POST ADD SUCCESSFULLY" })
        })
        .
        catch(err => res.send({ message: "POST ADD FAILED", detailled: "INVALID INFORMATION", err: err }))
}

async function GetAllPost(clientConnex, res, req) {
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

async function GetPostID(clientConnex, res, req) {
    await clientConnex.db("WM").collection('Post').find({ _id: req.body.postId }).toArray()
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

exports.AddPost = AddPost
exports.GetAllPost = GetAllPost
exports.GetPostID = GetPostID
