const express = require('express')
const cookieParser = require('cookie-parser')
const session = require('express-session')
const { MongoClient, ServerApiVersion } = require('mongodb');

const app = express()

async function start(port, routeUser) {
    const uriBd = "mongodb://127.0.0.1:27017";
    // const uriBd = "mongodb+srv://RicMongo:tNhwIIgEIAksjl4H@cluster0.pexx4dr.mongodb.net/?retryWrites=true&w=majority";
    const client = new MongoClient(uriBd, { useNewUrlParser: true, useUnifiedTopology: true, serverApi: ServerApiVersion.v1 });
    routeUser.sendDb(client)

    app.use(express.urlencoded({ extended: true }))
    app.use(express.json())
    // app.use(session({
    //     secret: "garagesecreti",
    //     saveUninitialized: true,
    //     cookie: { maxAge: 1000 * 60 * 60 * 24 },
    //     resave: false
    // }))
    app.use(cookieParser())

    // --------------------------------------------------------------------------------
    // USER
    // --------------------------------------------------------------------------------

    // GET REQUEST
    app.get("/user/", routeUser.home)
    // --------------------------------------------------------------------------------


    // 404 ERROR
    app.use((req, res) => {
        res.send({ message: "NOT FOUND" })
    })
    // --------------------------------------------------------------------------------

    app.listen(port, console.log(`Server running on port ${port}`))
}

exports.start = start