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

package com.micronautics.playTypes

import java.net.URL
import org.scalatest.{MustMatchers, WordSpec}

class TestIt extends WordSpec with MustMatchers {
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

  "UrlOrPath" must {
    val urlStr = new URL("http://asdf.com/blah")
    val path = "/asfd/qwer"

    "work" in {
      val actual = UrlOrPath(urlStr)
      actual === Left("http://asdf.com/blah")
      ()
    }
    "append properly" in {
      val actual = UrlOrPath(urlStr.appendPath(path))
      actual === Left("http://asdf.com/blah/asfd/qwer")
      ()
    }
  }
}
