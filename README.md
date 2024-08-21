### Create local database:

```
    cd local_database
    docker compose up -d 
```

Spring VM:

``-Dspring.profiles.active=local``


### Deploy to docker hub

```
    sudo ./gradlew dockerTagTAGVersion
    sudo ./gradlew dockerPushTAGVersion
```
