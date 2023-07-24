async function AddPost(clientConnex, res, req) {
    if (req.body.imagePost !== undefined && req.body.descirption !== undefined) {
        await clientConnex.db("WM").collection('Post').insertOne(req.body)
        .then(resultat => {
            res.send({ message: "POST ADD SUCCESSFULLY" })
        })
        .
        catch(err => res.send({ message: "POST ADD FAILED", detailled: "INVALID INFORMATION", err: err }))
    } else {
        res.send({ message: "POST ADD FAILED", detailled: "INVALID INFORMATION" })
    }
    }

exports.AddPost = AddPost
