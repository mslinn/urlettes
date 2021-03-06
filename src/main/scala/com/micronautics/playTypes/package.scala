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

package com.micronautics

import java.net.URL

package object playTypes extends PlayTypeJsonFormats {
  implicit class RichURL(val value: URL) extends AnyVal {
    @inline private def prefixSlash(value: String) = if (value.startsWith("/")) value else "/" + value

    def appendPath(appendedPath: String): URL =
      new URL(value.getProtocol, value.getHost, value.getPort, prefixSlash(value.getPath.trim) + appendedPath)

    def exists: Boolean = try {
      scala.io.Source.fromURL(value)
      true
    } catch {
      case _: Exception =>
        false
    }

    def isEmpty: Boolean = value.toString.trim.isEmpty

    def nonEmpty: Boolean = value.toString.trim.nonEmpty

    def setHost(newHost: String): URL =
      new URL(value.getProtocol.trim, newHost, value.getPort, prefixSlash(value.getFile.trim))

    def setPath(newPath: String): URL =
      new URL(value.getProtocol.trim, value.getHost.trim, value.getPort, prefixSlash(newPath.trim))

    def setProtocol(newProtocol: String): URL =
      new URL(newProtocol.trim, value.getHost.trim, value.getPort, prefixSlash(value.getFile.trim))

    /** @param newQuery will be URL encoded */
    def setQuery(newQuery: String): URL = {
      import java.net.URLEncoder.encode
      import java.nio.charset.StandardCharsets.UTF_8

      val newFile = (if (value.getPath.isEmpty) "" else prefixSlash(value.getPath)) +
        (if (newQuery.trim.isEmpty) "" else "?" + encode(newQuery.trim, UTF_8.toString))
      new URL(value.getProtocol, value.getHost, value.getPort, newFile)
    }
  }

  object RichURL {
    def empty = new URL("http://empty")

    def unapply(string: String): Option[RichURL] =
      try {
        Some(RichURL(new URL(string)))
      } catch {
        case _: Exception => None
      }
  }
}
