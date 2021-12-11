# HappyThoughts

[![Java CI with Maven](https://github.com/aaronmbdev/happythoughts-backend/actions/workflows/maven.yml/badge.svg)](https://github.com/aaronmbdev/happythoughts-backend/actions/workflows/maven.yml)

Este es el backend del proyecto HappyThoughts. Desarrollado para la hackathon bitsxlaMarató.

## Requisitos

Te recomendamos tener instalado:
- Java 11
- Maven
- Docker

Para poder ejecutar satisfactoriamente este programa es necesario contar con:
- Servidor ElasticSearch
- Proyecto en Google Cloud con la API de natural language habilitada.

### Elastic Search
Para poder ejecutar Elastic Search en local, se recomienda ejecutar la siguiente imagen de docker:

`docker run -p 9200:9200 \
-e "discovery.type=single-node" \
docker.elastic.co/elasticsearch/elasticsearch:7.10.0`

### Google Cloud - Credenciales
Para poder ejecutar el proyecto, tienes que configurar la variable de entorno GOOGLE_APLICATION_CREDENTIALS con el path absoluto al fichero json descargado de Google Cloud.

`export GOOGLE_APPLICATION_CREDENTIALS="/home/user/path/toJsonFile.json"`
