organization := "com.micronautics"

name := "urlettes"

version := "0.1.0"

scalaVersion := "2.11.8"

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

scalacOptions in (Compile, doc) <++= baseDirectory.map {
  (bd: File) => Seq[String](
     "-sourcepath", bd.getAbsolutePath,
     "-doc-source-url", "https://github.com/mslinn/urlettes/tree/master€{FILE_PATH}.scala"
  )
}

javacOptions ++= Seq(
  "-Xlint:deprecation", 
  "-Xlint:unchecked", 
  "-source", "1.8", 
  "-target", "1.8", 
  "-g:vars"
)

resolvers ++= Seq(
  "Lightbend Releases" at "http://repo.typesafe.com/typesafe/releases"
)

libraryDependencies ++= Seq(
  "org.scalatest"     %% "scalatest"   % "2.2.6" % "test" withSources(),
  "junit"             %  "junit"       % "4.12"  % "test"
)

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

sublimeTransitive := true

