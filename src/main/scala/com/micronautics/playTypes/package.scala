package com.micronautics

import java.net.URL

package object playTypes extends PlayTypeJsonFormats {
  implicit class RichURL(val value: URL) extends AnyVal with slick.lifted.MappedTo[URL] {
    @inline private def prefixSlash(value: String) = if (value.startsWith("/")) value else "/" + value

    def appendPath(appendedPath: String): URL =
      new URL(value.getProtocol, value.getHost, value.getPort, prefixSlash(value.getPath.trim) + appendedPath)

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
        case e: Exception => None
      }
  }
}
