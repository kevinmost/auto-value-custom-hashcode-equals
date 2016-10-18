# AutoValue: Custom hashCode/equals Extension

An extension for Google's [AutoValue](https://github.com/google/auto) that
allows for the exclusion of specific properties from hashCode and equals

## Usage

Annotate any properties in your `@AutoValue`-annotated class with
`@IgnoreForHashCodeEquals`

```java
@AutoValue public abstract class Foo {

  public abstract String bar();

  @IgnoreForHashCodeEquals public abstract UUID id(); // will not be used in generating hashCode and equals
}
```

## Download

Add JitPack at the end of your list of repositories if you haven't already:

```groovy
repositories {
  ... // jcenter(), mavenCentral(), etc
  maven { url "https://jitpack.io" }
}
```

Now add the dependencies:

```groovy
dependencies {
  compileOnly "com.github.kevinmost.auto-value-custom-hashcode-equals:adapter:[version]"
  apt "com.github.kevinmost.auto-value-custom-hashcode-equals:processor:[version]"
}
```
