# URLEttes

[![Build Status](https://travis-ci.org/mslinn/urlettes.svg?branch=master)](https://travis-ci.org/mslinn/urlettes)
[ ![Download](https://api.bintray.com/packages/micronautics/play/urlettes/images/download.svg) ](https://bintray.com/micronautics/play/urlettes/_latestVersion)

Types for Play Framework.

Included:
* Types: `RichURL`, `URLPath` and `UrlOrPath`
* Implicit form mappers: `url`, `urlPath`
* JSON type mappers: for `URL`, `URLPath` and `UrlOrPath`

[Here is why you should care about strong typing.](http://pchiusano.github.io/2016-09-15/static-vs-dynamic.html)

## Installing ##

Add two lines to `build.sbt`.

 * Add the `urlettes` dependency:
````
"com.micronautics" %% "urlettes" % "0.1.7" withSources()
````

 * Add this to the `resolvers`:
````
"micronautics/play on bintray" at "http://dl.bintray.com/micronautics/play"
````

This library has been built against Scala 2.11.11 / Play 2.5.15 and Scala 2.12.2 / Play 2.6.0.

## Scaladoc
[Here](http://mslinn.github.io/urlettes/latest/api/)

## Thanks To

This project is sponsored by [Micronautics Research Corporation](http://www.micronauticsresearch.com/),
the company that develops and delivers online Scala and Play training via [ScalaCourses.com](http://www.ScalaCourses.com).
