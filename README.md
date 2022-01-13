# bahamas-invoice

Application uses MongoDb as database service, need to install to run in local machine.

As the model of Invoice Express is to different from the query parameters received in the request, the application just saves the invoice at a local database and call the bahamas service.

The store-bahamas-client endpoint that could be sent in json format, as this request actually store data, a POST with a json body would be more suitable.

Endpoint of bahamas mocked in postman.

Unit tests covering some business logic at service layer.
