package com.micronautics.playTypes

import java.net.URL
import org.scalatest.{MustMatchers, WordSpec}

class TestyMcTest extends WordSpec with MustMatchers {
  "URLs" must {
    "behave" in {
      val url = new URL("http", "localhost", 80, "")
      url.setPath("blah") === "http:/localhost:80/blah"
      url.setQuery("name=value") === "http:/localhost:80?name=value"
      url.setPath("blah").setQuery("name=value") === "http:/localhost:80/blah?name=value"
      url.setQuery("name=value").setPath("blah") === "http:/localhost:80/blah?name=value"
      ()
    }
  }
}
