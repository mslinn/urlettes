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
import play.api.data.{FormError, Mapping}
import play.api.data.Forms.of
import play.api.data.format.Formatter

trait PlayTypeFormMappers {
  implicit val urlFormat = new Formatter[URL] {
    /** @param key indicates the name of the form field to convert from String to EMail
      * @param data is a Map of field name -> value */
    def bind(key: String, data: Map[String, String]): Either[Seq[FormError], URL] =
      data
        .get(key)
        .map(v => new URL(v))
        .toRight(Seq(FormError(key, "error.required", Nil)))

    def unbind(key: String, value: URL): Map[String, String] = Map(key -> value.toString)
  }

  implicit val urlPathFormat = new Formatter[URLPath] {
    /** @param key indicates the name of the form field to convert from String to EMail
      * @param data is a Map of field name -> value */
    def bind(key: String, data: Map[String, String]): Either[Seq[FormError], URLPath] =
      data
        .get(key)
        .map(v => new URLPath(v))
        .toRight(Seq(FormError(key, "error.required", Nil)))

    def unbind(key: String, value: URLPath): Map[String, String] = Map(key -> value.toString)
  }

  val url: Mapping[URL]         = of[URL]
  val urlPath: Mapping[URLPath] = of[URLPath]
}
