const Express = require('express');
const app = new Express();
const port = process.env.NOTEWEB_PORT || 80;
const gatewayServicePort = process.env.GATEWAYSERVICE_PORT || 9000;

console.log(`gatewayServicePort: ${gatewayServicePort}`);

app.use(Express.json());
app.get("/gatewayServicePort", (req, res) => {

    console.log(`enter /gatewayServicePort, return: ${gatewayServicePort}`);

    res.json({

        gatewayServicePort: gatewayServicePort
    });
});
app.use(Express.static(require('path').join(__dirname, 'public')));
app.listen(port, () => {

    console.log(`listening on: ${port}...`);
});
