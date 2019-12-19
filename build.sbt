/* Copyright 2012-2019 Micronautics Research Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License. */

cancelable := true

crossScalaVersions := Seq("2.11.12", "2.12.10", "2.13.1")

// define the statements initially evaluated when entering 'console', 'console-quick', but not 'console-project'
initialCommands in console := """
                                |""".stripMargin

javacOptions ++= Seq(
  "-Xlint:deprecation",
  "-Xlint:unchecked",
  "-source", "1.8",
  "-target", "1.8",
  "-g:vars"
)

libraryDependencies ++= Seq(
  "junit" %  "junit" % "4.12"   % Test
)

libraryDependencies ++= scalaVersion {
  case sv if sv.startsWith("2.13") => // Builds with Scala 2.13.x and Play 2.8.x
    val playVer = "2.8.0"
    Seq(
      "com.typesafe.play"      %% "play"               % playVer % Provided,
      //
      "com.typesafe.play"      %% "play"               % playVer    % Test withSources(),
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0"    % Test withSources()
    )

  case sv if sv.startsWith("2.12") => // Builds with Scala 2.12.x and Play 2.8.x
    val playVer = "2.8.0"
    Seq(
      "com.typesafe.play"      %% "play"               % playVer % Provided,
      //
      "com.typesafe.play"      %% "play"               % playVer % Test withSources(),
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test withSources()
    )

  case sv if sv.startsWith("2.11") => // Builds with Scala 2.11.x and Play 2.5.x
    val playVer = "2.7.4"
    Seq(
      "com.typesafe.play"      %% "play"               % playVer % Provided,
      //
      "com.typesafe.play"      %% "play"               % playVer % Test withSources(),
      "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test withSources()
    )
}.value

licenses += ("MIT", url("https://opensource.org/licenses/Apache-2.0"))

logLevel := Level.Warn

// Only show warnings and errors on the screen for compilations.
// This applies to both test:compile and compile and is Info by default
logLevel in compile := Level.Warn

// Level.INFO is needed to see detailed output when running tests
logLevel in test := Level.Info

name := "urlettes"

organization := "com.micronautics"

resolvers ++= Seq(
  "Lightbend Releases" at "https://repo.typesafe.com/typesafe/releases",
  "micronautics/play on bintray" at "https://dl.bintray.com/micronautics/play"
)

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-target:jvm-1.8",
  "-unchecked"
)

scalacOptions in (Compile, doc) ++= baseDirectory.map {
  (bd: File) => Seq[String](
     "-sourcepath", bd.getAbsolutePath,
     "-doc-source-url", "https://github.com/mslinn/urlettes/tree/master€{FILE_PATH}.scala"
  )
}.value

scalaVersion := "2.13.1"

//triggeredMessage in ThisBuild := Watch.clearScreenOnTrigger

ThisBuild / turbo := true

version := "0.1.8"
