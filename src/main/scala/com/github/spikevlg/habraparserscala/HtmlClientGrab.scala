package com.github.spikevlg.habraparserscala

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils


class HtmlClientGrab {
  val httpClient = HttpClientBuilder.create().build()

  def go(url : String) : String = {
    val httpGet = new HttpGet(url)
    val httpResponse = httpClient.execute(httpGet)
    val entity = httpResponse.getEntity
    EntityUtils.toString(entity)
  }
}
