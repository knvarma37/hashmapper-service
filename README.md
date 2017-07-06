# hashmapper-service

This is a CF service broker to provide key-value based storage. Installation process as follows.

1) mvn clean package  # to build the service broker
2) cf push --random-route # reads manifest file from the project directory and pushes the app into CF in selected space / org. 
3) curl username:password@url/v2/catalog  # Once pushed to verify succesful running of service. Username and password are pre-configured in resources/applicatoin.properties.
4) cf create-service-broker hashmapper-service username password https://url --space-scoped # enable a service in taget scope
5) cf marketplace # to see your service visible in the list.
6) cf create-service hashmapper-service-id basic hashmapper-db
   cf create-service-key hashmapper-db cred-keys
   cf service-key hashmapper-db cred-keys
7) With above command the client can get instance of the service enables the access for it to use services.
   
