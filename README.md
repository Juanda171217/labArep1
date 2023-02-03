# Titulo

TALLER 1: APLICACIONES DISTRIBUIDAS (HTTP, SOCKETS, HTML, JS,MAVEN, GIT)

### Juan David Martinez


## Descripción

En este repositorio se encuentra un programa para consultar la información de películas de cine, la aplicación recibirá una frase de búsqueda del título y muestra los datos de la película correspondiente, para esto se utiliza el API gratuito de https://www.omdbapi.com


### Prerrequisitos

Para correr este se debe tener instalado:

- Maven
- Java

### Guía de uso

Primero debera desacargar el respositorio con el comando

```
git clone https://github.com/Juanda171217/labArep1
```

Luego para ejecutarlo, debe utilizar el comando

```
mvn clean package exec:java -D "exec.mainClass"="edu.escuelaing.arem.HttpServer"
```

Al terminar esta intruccion podra ir a cualquier navegador y acceder con la ruta http://localhost:36000


## Estructura de Archivos

  pom.xml
│   README.md
│   
├───.vscode
│       settings.json 
├───src
│   ├───main
│   │   └───java
│   │       └───edu
│   │           └───escuelaing
│   │               └───arem
│   │                       HttpClient.java
│   │                       HttpServer.java
│   └───test
│       └───java
│           └───edu
│               └───escuelaing
│                   └───arem
│                       └───ASE
│                           └───app
│                                   AppTest.java
└───target
    │   mi-primera-app-1.0-SNAPSHOT.jar
    ├───classes
    │   └───edu
    │       └───escuelaing
    │           └───arem
    │                   HttpClient.class
    │                   HttpServer.class
    ├───generated-sources
    │           └───default-testCompile
    │                   createdFiles.lst
    │                   inputFiles.lst
    ├───surefire-reports
    │       edu.escuelaing.arem.ASE.app.AppTest.txt
    │       TEST-edu.escuelaing.arem.ASE.app.AppTest.xml
    └───test-classes
        └───edu
            └───escuelaing
                └───arem
                    └───ASE
                        └───app
                                AppTest.class

