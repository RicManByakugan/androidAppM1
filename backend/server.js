const app = require("./app")
const routeUser = require("./route/routeUser")

app.start(5000, routeUser)