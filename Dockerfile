FROM ubuntu:latest AS build

# Instalação das dependências
RUN apt-get update && \
    apt-get install -y openjdk-21-jdk maven

# Configuração das variáveis de ambiente para Java
ENV JAVA_HOME /usr/lib/jvm/java-21-openjdk-amd64
ENV PATH $JAVA_HOME/bin:$PATH

# Diretório de trabalho
WORKDIR /app

# Copia o código-fonte para o contêiner
COPY . .

# Compila o projeto e ignora os testes
RUN mvn clean install -DskipTests

FROM openjdk:21-jdk

# Expondo a porta 8080 para a aplicação
EXPOSE 8080

# Diretório de trabalho
WORKDIR /app

# Copia o arquivo JAR do estágio de build
COPY --from=build /app/target/Onebuild-api.jar app.jar

# Comando de inicialização da aplicação
ENTRYPOINT [ "java", "-jar", "app.jar" ]