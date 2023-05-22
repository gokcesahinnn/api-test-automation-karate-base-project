# Karate API Functional and Performance Testing Automation Base Project


# Tool stack

* **Karate Framework** - Development Framework
* **Gatling Framework** - Performance Test Framework
* **Java/Javascript/Scala** - Development Language (For situations where it is necessary)
* **IntelliJ IDE** - Development IDE
* **Maven** - Package Management

# Running Tests

1. It can be run based on scenario or feature by pressing the green RUN button on the IDE.


2. Scenarios determined on the JUnit runner can be run again by pressing the green RUN button.


3. `Running it via CLI by giving 'Runner class' and 'tagname'.

   `mvn clean test -Dtest=RunnerName "-Dkarate.options=--tags @tagName"`


4. Running API test as performance test

   ``mvn clean test-compile gatling:test -Dgatling.simulationClass=performanceRunners.createBookingWithCsvSimulation -DuserCount=5 -Dduration=10 -DresponseTime=50 -DsuccessfulRequests=95``

# Naming Convention

* `camelCase` is used for naming. New developments need to adhere to this convention. Only Java and Scala classes are named as `UpperCamelCase`.

Example: `myVar`, `dataPackage`, `contentCategory`, `Utils.Java`, `MyRunner.scala` etc.

# Tagging

* When tagging scenarios `snake_case` should be used.

```
@wip = A scenario/feature whose development is not complete
@smoke = A scenario/feature which is expected to run in the smoke scope
@excluded  = A scenario/feature that no longer exists on the application, but whose scenario has already been implemented
@bug_fix = A scenario/feature waiting for bug fix due to an existing bug on the application 
           It should be used with the bug_id tag like the example below.
           Example Usage: @bug_fix @bug_id_JIRA_ID 
@prod  = Scenarios to be run on prod environment
@regression  = Cases to be run during regression
@feature_tag = Tag name to be given to each feature file. Example: @feature_login
@scenario_tag = The unique tag to be given to each scenario. Example: @success_login
```

# Custom Method Development

* Karate Framework ile geliştirilemeyen custom ihtiyaçlar olduğunda, Java ve/veya JS ile' custom metotlar
  geliştirilebilir.
* Custom metotlar `helpers` package'nın altında konumlandırılmalıdır.

<b>Utils class</b>

- Bu class, senaryoların içerisinde ortak olarak kullanılan fonksiyonların tutulduğu classdır.
- Class'ın içerisindeki 'waitFor' fonksiyonu ile feature akışı bekletilmektedir.
- CommonUtils.java sınıfı feature file içerisinden şu şekilde çağırılmalıdır.

```
   * Java.type('helpers.Utils').waitFor(60)
```

# Feature yazımı

* Features folderında ilgili servise ait package oluşturularak her bir servis özelinde feature file'lar
  oluşturulacaktır.
* Her bir mikroservis için ayrı bir feature file oluşturulacak. Bir mikroservisin birden fazla feature'ı varsa o
  featurelar içinde ayrı birer feature file oluşturulacak.
* Proje genelinde tekrarlı olarak çağırılacak servisler `callers` paketi altına eklenecektir.
* Graphql mutution ve query modelleri `qraphql` folderı altında feature package modelinde tutulacaktır.
* Load test `runner`'ları performance runner'ının altında yer almaktadır.
* Senaryolar için gerekli yardımcı fonksiyonel kodlar helpers folderı altında `Java` ile geliştirilecektir.
* `model` folderında servisler için gerekli request ve response modeller tutulacaktır. Genel error
  mesajlar `errorMessage.json` içinde tutulacaktır.
* Proje genelinde sabit data dosyaları `data` package'ı altında tutulacaktır.


# Project tree

```
.
├── README.md
├── pom.xml                       #Projenin kullanılacak kütüphanelerin yönetimi
└── src
    ├── main
    │   ├── java
    │   └── resources
    └── test
        ├── java
        │   ├── CucumberRunner.java  
        │   ├── callers                        #Proje genelinde çağırılan servislerin yazıldığı klasör.
        │   │   └── getBooking
        │   │       └── getBookingCallers.feature
        │   ├── data                        #Proje genelinde kullanılacak olan datalatın yer aldığı klasör.
        │   │   └── user
        │   │       └── userName.csv
        │   ├── features                       #Gherkin Synxtaxı'ndaki senaryoların bulunduğu dizin
        │   │   └── publicGraphQl
        │   │       ├── publicGraphqlApi.feature
        │   ├── helpers                            #Utils class ve metodların yer aldığı dizin
        │   │   └── DataGenerator.java        
        │   │   └── Utils.java
        │   │   └── csvConvertJson.js
        │   │   └── DateTimeHelper
        │   │   └── RegexHelper.js
        │   ├── model                              #Servis request ve response modeller
        │   │   ├── createBooking
        │   │   │   └── createBookingData.json
        │   │   ├── graphql                                #Graphql servis modellerinin bulunduğu klasör
        │   │   │   └── publicGraphqlApi
        │   │   │      └── publicGraphqlApi.graphql
        │   │   ├── errorMessage.json
        │   ├── performanceRunners                 # Performans test koşum scriptlerinin bulunduğu sınıf
        │   │   └── performanceTestingSimulation.scala # for performance test
        │   │   └── performanceTestingSimulationWithAssertion.scala # for performance test
        │   ├── CucumberRunner       
        │   ├── karate-config.js                   #Projeye ait configürasyonlar
        │   ├── logback-test.xml                   #Console log model
        └── resources
            └── gatling-akka.conf

```