const express = require('express')
const cookieParser = require('cookie-parser')
const session = require('express-session')
const { MongoClient, ServerApiVersion } = require('mongodb');

const app = express()

async function start(port, routeUser, routePost) {
    const uriBd = "mongodb://127.0.0.1:27017";
    // const uriBd = "mongodb+srv://RicMongo:tNhwIIgEIAksjl4H@cluster0.pexx4dr.mongodb.net/?retryWrites=true&w=majority";
    const client = new MongoClient(uriBd, { useNewUrlParser: true, useUnifiedTopology: true, serverApi: ServerApiVersion.v1 });
    routeUser.sendDb(client)
    routePost.sendDb(client)

    app.use(express.urlencoded({ extended: true }))
    app.use(express.json())
    app.use(session({
        secret: "wmsecretapi",
        saveUninitialized: true,
        cookie: { maxAge: 1000 * 60 * 60 * 24 },
        resave: false
    }))
    app.use(cookieParser())

    app.use(function (req, res, next) {
        res.setHeader('Access-Control-Allow-Origin', '*');
        res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,content-type');
        res.setHeader('Access-Control-Allow-Credentials', true);
        next()
    });

    // --------------------------------------------------------------------------------
    // USER
    // --------------------------------------------------------------------------------

    // GET REQUEST
    app.get("/user/", routeUser.home)
    // --------------------------------------------------------------------------------

    // POST REQUEST
    // API CLIENT FOR SUBSCRIBING
    // REQUIRED INFORMATION : name, firstname, logname, password
    app.post("/user/subscribe", routeUser.subscribe)
    // --------------------------------------------------------------------------------

    // POST REQUEST
    // USER LOGIN
    // REQUIRED INFORMATION : logName, password
    app.post("/user/login", routeUser.login)
    // --------------------------------------------------------------------------------

    // POST REQUEST
    // USER LOGOUT
    // REQUIRED INFORMATION : NOTHING ON POST REQUEST
    app.post("/user/logout", routeUser.logout)
    // --------------------------------------------------------------------------------




    // --------------------------------------------------------------------------------
    // POST
    // --------------------------------------------------------------------------------
    // POST REQUEST
    // ADD SOME POST
    app.post("/post/", routePost.addpost)
    // --------------------------------------------------------------------------------


    // 404 ERROR
    app.use((req, res) => {
        res.send({ message: "NOT FOUND" })
    })
    // --------------------------------------------------------------------------------

    app.listen(port, console.log(`SERVER RUNNING ON PORT ${port}`))
}

exports.start = start