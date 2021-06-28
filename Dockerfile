FROM openjdk:16
ADD target/Hotel.jar hotel.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "hotel.jar"]