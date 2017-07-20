/* Copyright 2012-2016 Micronautics Research Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License. */

import sbt.Keys._

licenses += ("MIT", url("https://opensource.org/licenses/Apache-2.0"))

name := "urlettes"

organization := "com.micronautics"

version := "0.1.7"

scalaVersion := "2.11.11"
crossScalaVersions := Seq(scalaVersion.value, "2.12.2")

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-target:jvm-1.8",
  "-unchecked",
  "-Ywarn-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused",
  "-Ywarn-value-discard",
  "-Xfuture",
  "-Xlint"
)

scalacOptions in (Compile, doc) ++= baseDirectory.map {
  (bd: File) => Seq[String](
     "-sourcepath", bd.getAbsolutePath,
     "-doc-source-url", "https://github.com/mslinn/{name.value}/tree/master€{FILE_PATH}.scala"
  )
}.value

javacOptions ++= Seq(
  "-Xlint:deprecation",
  "-Xlint:unchecked",
  "-source", "1.8",
  "-target", "1.8",
  "-g:vars"
)

resolvers ++= Seq(
  "Lightbend Releases" at "http://repo.typesafe.com/typesafe/releases",
  "micronautics/play on bintray" at "http://dl.bintray.com/micronautics/play"
)

libraryDependencies ++= Seq(
  "com.github.nscala-time" %% "nscala-time" % "2.16.0" withSources(),
  //
  "junit"                  %  "junit"       % "4.12"   % Test
)

libraryDependencies ++= scalaVersion {
  case sv if sv.startsWith("2.12") => // Builds with Scala 2.12.x and Play 2.6.x
    val playVer = "2.6.2"
    Seq(
      "com.typesafe.play"      %% "play"               % playVer % Provided,
      "com.typesafe.play"      %% "play-json"          % playVer % Provided,
      "org.clapper"            %% "grizzled-scala"     % "4.2.0" withSources(),
      "com.typesafe.slick"     %% "slick"              % "3.2.0" % Provided,
      //
      "com.typesafe.play"      %% "play"               % playVer    % Test withSources(),
      "com.typesafe.play"      %% "play-json"          % playVer    % Test withSources(),
      "com.typesafe.play"      %% "play-ws"            % playVer    % Test withSources(),
      "com.typesafe.slick"     %% "slick"              % "3.2.0"    % Test withSources(),
      "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.0-M2" % Test withSources()
    )

  case sv if sv.startsWith("2.11") => // Builds with Scala 2.11.x and Play 2.5.x
    val playVer = "2.5.16"
    Seq(
      "com.typesafe.play"      %% "play"               % playVer % Provided,
      "com.typesafe.play"      %% "play-json"          % playVer % Provided,
      "com.typesafe.play"      %% "play-iteratees"     % playVer withSources() force(),
      "com.typesafe.play"      %% "play-datacommons"   % playVer withSources() force(),
      "org.clapper"            %% "grizzled-scala"     % "1.3"   withSources(),
      //
      "com.typesafe.play"      %% "play"               % playVer % Test withSources(),
      "com.typesafe.play"      %% "play-json"          % playVer % Test withSources(),
      "com.typesafe.play"      %% "play-ws"            % playVer % Test withSources(),
      "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test withSources()
    )
}.value

logLevel := Level.Warn

// Only show warnings and errors on the screen for compilations.
// This applies to both test:compile and compile and is Info by default
logLevel in compile := Level.Warn

// Level.INFO is needed to see detailed output when running tests
logLevel in test := Level.Info

// define the statements initially evaluated when entering 'console', 'console-quick', but not 'console-project'
initialCommands in console := """
                                |""".stripMargin

cancelable := true
