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
import slick.lifted.MappedTo

object URLPath {
  import play.api.libs.json._

  implicit val urlPathFormatter: OFormat[URLPath] = Json.format[URLPath]

  val empty = URLPath("")
}

/** Path portion of a URL; valid values either start with a forward slash or are empty.
  * You could consider this as just enough of a URL to support links within the webapp. */
case class URLPath(value: String) extends AnyVal with MappedTo[String] {
  def isAbsolute: Boolean = value.startsWith("/")

  def isEmpty: Boolean = value.isEmpty

  def isRelative: Boolean = !value.startsWith("/")

  def isValid: Boolean = !value.startsWith("https:") && !value.startsWith("http:")

  def toRelative: URLPath = if (isAbsolute) URLPath(s".$value") else this

  override def toString: String = value

  def validate: URLPath = {
    assert(isValid)
    URLPath(value.trim)
  }
}

object UrlOrPath {
  def apply(url: URL): UrlOrPath = new UrlOrPath(Left(url))
  def apply(path: String): UrlOrPath = new UrlOrPath(Right(URLPath(path)))
}

/** Wraps an Either[URL, URLPath]. Use it when you need to support both local and external links. */
case class UrlOrPath(value: Either[URL, URLPath]) extends AnyVal {
  override def toString: String = value match {
    case Left(url) => url.toString
    case Right(urlPath) => urlPath.value
  }
}
