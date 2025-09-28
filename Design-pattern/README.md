# Design Patterns Exercises - Java
    This project demonstrates six use cases for design patterns:
- Behavioral: Observer, Strategy
- Creational: Factory Method, Builder
- Structural: Proxy, Adapter
    Project layout:
```
design-patterns-exercises/
 ├─ src/com/example/patterns/
 │   ├─ Main.java
 │   ├─ util/LoggerFactory.java
 │   ├─ behavioral/observer/Subject.java
 │   ├─ behavioral/observer/ConcreteSubject.java
 │   ├─ behavioral/observer/Observer.java
 │   ├─ behavioral/strategy/CompressionStrategy.java
 │   ├─ behavioral/strategy/ZipCompressionStrategy.java
 │   ├─ behavioral/strategy/GzipCompressionStrategy.java
 │   ├─ creational/factory/Product.java
 │   ├─ creational/factory/ProductFactory.java
 │   ├─ creational/builder/Meal.java
 │   ├─ creational/builder/MealBuilder.java
 │   ├─ structural/proxy/SecureService.java
 │   ├─ structural/proxy/SecureServiceProxy.java
 │   ├─ structural/adapter/LegacyTemperatureSensor.java
 │   ├─ structural/adapter/ITemperatureSensor.java
 │   ├─ structural/adapter/TemperatureAdapter.java
 │   └─ README.md
```
    ## Run
Compile and run:
```
javac -d out $(find src -name "*.java")
java -cp out com.example.patterns.Main
```
