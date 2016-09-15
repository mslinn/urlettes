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
