# Build Instruction
- unzip the file.
- cd into the folder where pom.xml resides.
- mvn clean install
- docker-compose build
- docker-compose up -d


### Curl command

#####POST : 
    curl --location --request POST 'http://localhost:8080/articles' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "text": "Hello!!!!111",
        "title": "Bello"
    }'

######GET All
    curl --location --request GET 'http://localhost:8080/articles'

######Get an item
     curl --location --request GET 'http://localhost:8080/articles/id'

#### update

    curl --location --request PUT 'http://localhost:8080/articles/1' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "title": "Hello",
        "text": "Bello,,,,"
    }'