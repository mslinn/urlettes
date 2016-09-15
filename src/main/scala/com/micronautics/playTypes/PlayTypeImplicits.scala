package com.micronautics.playTypes

import java.net.URL
import play.api.libs.json.{Format, JsError, JsResult, JsString, JsSuccess, JsValue, Reads, Writes}

trait PlayTypeImplicits {
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

object PlayTypeImplicits extends PlayTypeImplicits
