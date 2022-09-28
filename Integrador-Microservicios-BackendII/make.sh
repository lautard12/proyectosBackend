cd eureka-server/
mvn clean && mvn package -DskipTests
docker build . -t eureka-service
cd ..

cd server-config/
mvn clean && mvn package -DskipTests
docker build . -t config-service
cd ..

#Agregamos el build de serie-service
#cd serie-service/
#mvn clean && mvn package -DskipTests
#docker build . -t serie-service
#cd ..

#cd movie-service/
#mvn clean && mvn package -DskipTests
#docker build . -t movie-service
#cd ..

#cd catalog-service/
#mvn clean && mvn package -DskipTests
#docker build . -t catalog-service
#cd ..

cd api-gateway/
mvn clean && mvn package -DskipTests
docker build . -t api-gateway
cd ..

docker compose up