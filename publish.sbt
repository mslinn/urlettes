publishArtifact in Test := false

// bintray settings
bintrayOrganization := Some("micronautics")
bintrayPackageLabels := Seq("play")
bintrayRepository := "play"
bintrayVcsUrl := Some(s"git@github.com:mslinn/${ name.value }.git")

// sbt-site settings
//enablePlugins(SiteScaladocPlugin)
//siteSourceDirectory := target.value / "api"
//publishSite

// sbt-ghpages settings
//enablePlugins(GhpagesPlugin)
//git.remoteRepo := s"git@github.com:mslinn/${ name.value }.git"
