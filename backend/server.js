const app = require("./app")
const routeUser = require("./route/routeUser")
const routePost = require("./route/routePost")

app.start(3000, routeUser, routePost)