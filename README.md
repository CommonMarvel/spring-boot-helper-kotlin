# spring-boot-helper-kotlin

### install

> build.gradle.kts
```
repositories {
    ...
    maven("https://commonmarvel.bintray.com/spring-boot-helper-kotlin")
}

dependencies {
    ...
    api("common.marvel:spring-boot-helper-kotlin:0.1.1")
}
```

### how to publish
```
BINTRAY_USER=<USERNAME> BINTRAY_KEY=<API_KEY> ./publish.sh
```
