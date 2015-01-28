name := "HabraParserScala"

version := "1.0"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
    "org.scalaj" % "scalaj-http_2.10" % "1.1.0" withSources() withJavadoc()
    , "com.ximpleware" % "vtd-xml" % "2.11" withSources() withJavadoc()
    , "net.sourceforge.htmlcleaner" % "htmlcleaner" % "2.10" withSources() withJavadoc()
    , "org.apache.httpcomponents" % "httpclient" % "4.3.6" withSources() withJavadoc()
    , "org.apache.httpcomponents" % "httpcore" % "4.3.3" withSources() withJavadoc()
)

mainClass in (Compile,run) := Some("com.github.spikevlg.habraparserscala.Application")