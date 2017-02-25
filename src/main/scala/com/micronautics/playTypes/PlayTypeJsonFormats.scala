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
import play.api.libs.json.{Format, JsError, JsResult, JsString, JsSuccess, JsValue, Reads, Writes}

/** Play JSON type mappers */
trait PlayTypeJsonFormats {
  val readsUrl: Reads[URL] = new Reads[URL] {
    override def reads(json: JsValue): JsResult[URL] = try {
      JsSuccess(new URL(json.as[String]))
    } catch {
      case e: Exception =>
        JsError(e.getMessage)
    }
  }

  val writesUrl: Writes[URL] = new Writes[URL] {
    override def writes(url: URL): JsValue = try {
      JsString(url.toString)
    } catch { case e: Exception =>
      JsString(e.getMessage)
    }
  }

  implicit val formatUrl = Format(readsUrl, writesUrl)

  val readsUrlPath: Reads[URLPath] = new Reads[URLPath] {
    override def reads(json: JsValue): JsResult[URLPath] = try {
      JsSuccess(new URLPath(json.as[String]))
    } catch {
      case e: Exception =>
        JsError(e.getMessage)
    }
  }

  val writesUrlPath: Writes[URLPath] = new Writes[URLPath] {
    override def writes(urlPath: URLPath): JsValue = try {
      JsString(urlPath.toString)
    } catch { case e: Exception =>
      JsString(e.getMessage)
    }
  }

  implicit val formatUrlPath = Format(readsUrlPath, writesUrlPath)
}

object PlayTypeJsonFormats extends PlayTypeJsonFormats
