# URLEttes

[![Build Status](https://travis-ci.org/mslinn/urlettes.svg?branch=master)](https://travis-ci.org/mslinn/urlettes)

Types for Play Framework.

Included:
* Types: `RichURL`, `URLPath` and `UrlOrPath`
* Implicit form mappers: `url`, `urlPath`
* JSON type mappers: for `URL`, `URLPath` and `UrlOrPath`
* Slick type mappers for `URL` and `URLPath`

[Here is why you should care about strong typing.](http://pchiusano.github.io/2016-09-15/static-vs-dynamic.html)

## Installing ##

Add two lines to `build.sbt`.

 * Add the `urlettes` dependency:
````
"com.micronautics" %% "urlettes" % "0.1.5" withSources()
````

 * Add this to the `resolvers`:
````
"micronautics/play on bintray" at "http://dl.bintray.com/micronautics/play"
````

This library has been built against Scala 2.11.8 / Play 2.5.12 and Scala 2.12.1 / Play 2.6.0-M1.

## Thanks To

This project is sponsored by [Micronautics Research Corporation](http://www.micronauticsresearch.com/),
the company that develops and delivers online Scala and Play training via [ScalaCourses.com](http://www.ScalaCourses.com).
