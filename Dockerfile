# Estágio de Build
FROM ubuntu:latest AS build

# Instalação das dependências necessárias
RUN apt-get update && \
    apt-get install -y openjdk-21-jdk maven

# Configuração das variáveis de ambiente do Java
ENV JAVA_HOME /usr/lib/jvm/java-21-openjdk-amd64
ENV PATH $JAVA_HOME/bin:$PATH

# Diretório de trabalho
WORKDIR /app

# Copia o código-fonte para o contêiner
COPY . .

# Compila o projeto e ignora os testes
RUN mvn clean install -DskipTests

# Estágio de Execução (Runtime)
FROM openjdk:21-jdk-slim

# Expondo a porta 8080 para a aplicação
EXPOSE 8080

# Diretório de trabalho
WORKDIR /app

# Copia o arquivo JAR do estágio de build
COPY --from=build /app/target/Onebuild-api.jar app.jar

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
