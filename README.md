### Goals

Build a multi-module server app that consists of several slices that can be developed _and tested_ independently, but deployed as a single application.

### Building

`./gradlew clean build` builds the whole thing
 
`./gradlew :users:clean build` builds a single module where `:users` is the module name

`./gradlew :users:test` runs tests within a single module where `:users` is the module name

`./gradlew test` runs all tests across all modules

The tests can also be run from within the IDE (per module).

### Running

`./gradlew bootRun` starts the container with code from all modules.

Feel free to take it for a spin:

```
$ curl http://localhost:8080/potato
$ curl -v -XPOST http://localhost:8080/coffee
$ curl http://localhost:8080/customer/5 | json_pp
$ curl http://localhost:8080/user/6 | json_pp
```

The app can also be run from within the IDE by running the [`Application`](https://github.com/vrto/gradle-kotlin-spring-multi-modules/blob/master/src/main/kotlin/sk/vrto/Application.kt) class.

### Modules

**Main module (the app root)**

- contains the main class and the main [`build.gradle`](https://github.com/vrto/gradle-kotlin-spring-multi-modules/blob/master/build.gradle) script that specifies how is the app built
- the main build script also specifies compulsory dependencies and settings for every sub-module
- contains 'other stuff' that belongs nowhere else (in this case [`OtherStuffController`](https://github.com/vrto/gradle-kotlin-spring-multi-modules/blob/master/src/main/kotlin/sk/vrto/OtherStuffController.kt))
- ideally this module would gradually shrink to a bare minimum over the time

**Shared**

- contains components that other modules depend on
- modules that depend on `:shared` will also end up pulling its dependencies transitively (unless they're explicitly excluded)
- this module also exports _its tests_ (using a JAR) so other modules can re-use generic base test class and other test support. This is done via [`testRuntime`](https://github.com/vrto/gradle-kotlin-spring-multi-modules/blob/master/shared/build.gradle#L9) configuration.

**Customers**

- business logic module that represents 'customers' slice of the (virtual) app
- written in Kotlin + Spring Boot + Spring Data
- the database configuration is grabbed from the `:shared` module
- the database itself contains tables for all modules, but this module only cares about customer-related tables

**Users**

- similar to customers, but deals with 'users' business slice
- written in Java + Lombok + Spring Boot + Spring Data to demonstrate that Lombok+Kotlin can be mixed seamlessly

### Functional tests

It wouldn't make sense to run all functional tests from within the main module, as that would be slow and we wouldn't be able to 'fully' test the individual modules (that may contain controllers etc.). In order to test the controllers in individual modules, we need to have one `Application`-like class per module with `main` function, see [`UsersModuleApp`](https://github.com/vrto/gradle-kotlin-spring-multi-modules/blob/master/users/src/main/java/sk/vrto/user/UsersModuleApp.java) and [`CustomersModuleApp`](https://github.com/vrto/gradle-kotlin-spring-multi-modules/blob/master/customers/src/main/kotlin/sk/vrto/customer/CustomersModuleApp.kt#L12). These apps wouldn't be used in production, it's enough to invoke `main` function using the [`Application`](https://github.com/vrto/gradle-kotlin-spring-multi-modules/blob/master/src/main/kotlin/sk/vrto/Application.kt#L9-L8) class in the main module.

That being said, the endpoints and components in different modules can still be normally accessed from the main module as well.

### Gradle specifics

- [`settings.gradle`](https://github.com/vrto/gradle-kotlin-spring-multi-modules/blob/master/settings.gradle) needs to include each individual module. IntelliJ automatically recognises that and marks source/test directories appropriately
- [`subprojects`](https://github.com/vrto/gradle-kotlin-spring-multi-modules/blob/master/build.gradle#L49) section in the main build file specifies the common defaults for every sub-module
- individual modules may omit `build.gradle` file if they don't need any additional configuration. In case when they do, the `build.gradle` file within the submodule should only contain things _not inherited_ from the main build file 
- spring boot plugin main only be applied to one module (the main one), other sub-modules [need to import](https://github.com/vrto/gradle-kotlin-spring-multi-modules/blob/master/build.gradle#L75) the relevant BOM
- while `:shared` is a standard sub-module, it [must not depend on self](https://github.com/vrto/gradle-kotlin-spring-multi-modules/blob/master/build.gradle#L80) to avoid circular dependencies 