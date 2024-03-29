# Archived Entities Management Library for Hibernate

## Description
This library offers a convenient way to work with archived entities in projects based on the Spring Framework and Hibernate. It allows you to automatically apply filters to your database queries to work with either archived or non-archived entities, as well as both types of data.

Features
* Automatic application of filters to database queries based on annotations.
* Ability to configure the filtering behavior through annotations at the service or method level.
* Easy integration with existing Spring and Hibernate projects.

## How to Use
1. Add the @EnableArchivedEntity annotation to your Spring configuration class to activate the library's functionality.

```
@Configuration
@EnableArchivedEntity
public class MyApplicationConfig {
    // Your configuration
}
```

2. Use the @ArchivableCrudService annotation for services where you want to automatically manage the filtering of archived data. You can specify which entities should be returned (all, only archived, or only non-archived).

```
@ArchivableCrudService(ArchiveFilter.State.NON_ARCHIVED)
public class MyService {
    // Your service
}
```

3. For more detailed control, use the @ArchiveFilter annotation at the method level. This allows you to override the filter behavior at the level of individual methods.

```
public class MyService {

    @ArchiveFilter(ArchiveFilter.State.ARCHIVED)
    public List<MyEntity> findArchived() {
        // Implementation
    }
}
```

4. Implement in your entity ArchivableEntity interface.

```
@Entity
public class YourEntity implements ArchivableEntity {
    // Entity fields, including the archived state
}
```

## Dependencies
Make sure your project is set up with Spring Framework and Hibernate, as the library extends their functionality.
```
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

	<dependency>
	    <groupId>com.github.NikolayNN</groupId>
	    <artifactId>archived-hibernate-entity</artifactId>
	    <version>Tag</version>
	</dependency>
```
