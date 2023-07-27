const express = require('express')
const cookieParser = require('cookie-parser')
const session = require('express-session')
const { MongoClient, ServerApiVersion } = require('mongodb');

const app = express()

async function start(port, routeUser, routePost) {
    // const uriBd = "mongodb://127.0.0.1:27017";
    const uriBd = "mongodb+srv://welcomemadagascar:MRkdGrGUsnSX34tQ@cluster0.6shj7xp.mongodb.net/";
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

    // GET REQUEST
    app.get("/user/notification", routeUser.notification)
    // --------------------------------------------------------------------------------

    // GET REQUEST
    app.get("/user/notification", routeUser.notification)
    // --------------------------------------------------------------------------------

    // GET REQUEST
    app.get("/user/preference", routeUser.preference)
    // --------------------------------------------------------------------------------

    // POST REQUEST
    // API ADD NOTIFICATION FOR USER
    // REQUIRED INFORMATION : userId, preference
    app.post("/user/preference/add", routeUser.addpreference)
    // --------------------------------------------------------------------------------

    // POST REQUEST
    // API ADD NOTIFICATION FOR USER
    // REQUIRED INFORMATION : message, user
    app.post("/user/notification/add", routeUser.addnotif)
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

    // GET ONE POST
    app.get("/post/:idPost", routePost.getpost)
    // --------------------------------------------------------------------------------

    // GET ALL POST
    app.get("/post/", routePost.getallpost)
    // --------------------------------------------------------------------------------

    // --------------------------------------------------------------------------------
    // POST
    // --------------------------------------------------------------------------------
    // POST REQUEST
    // ADD SOME POST : param : cleSearch
    app.post("/post/search", routePost.getsearch)
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