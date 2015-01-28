package com.github.spikevlg.habraparserscala


import java.io.{File, PrintWriter}
import javax.xml.xpath.XPathFactory

import org.apache.http.client.methods.HttpGet
import org.apache.http.util.EntityUtils

import scalaj.http._
import com.ximpleware._;
import com.ximpleware.xpath._;
import org.htmlcleaner.{CleanerProperties, DomSerializer, TagNode, HtmlCleaner}
import org.apache.http.impl.client.HttpClientBuilder

object Application {


  def main (args: Array[String]): Unit = {
    /*
    val response: HttpResponse[String] = Http("http://habrahabr.ru/").asString

      val habraParser = new HtmlHabraParser
      val lastPostId = habraParser.getLastPostID(response.body)
      println(lastPostId)

    val response2: HttpResponse[String] = Http("http://habrahabr.ru/post/" + lastPostId).asString
    */
   // val response2: HttpResponse[String] = Http("http://habrahabr.ru/post/248961").asString
    val httpClient = HttpClientBuilder.create().build()
    val httpget = new HttpGet("http://habrahabr.ru/post/248961")
    val httpReponse = httpClient.execute(httpget)
    val entity = httpReponse.getEntity
    val pageBody = EntityUtils.toString(entity)


    val pw = new PrintWriter(new File("hello4.txt" ))
    pw.write(pageBody)
    //pw.write(response2.body)
    pw.close
    val habraParser = new HtmlHabraParser
    //val habraItem = habraParser.parsePost(248961, response2.body)
    val habraItem = habraParser.parsePost(248961, pageBody)
    print(habraItem)


    /*
        val httpClient = HttpClientBuilder.create().build()
        val httpget = new HttpGet("http://habrahabr.ru/")
        val httpReponse = httpClient.execute(httpget);
        val entity = httpReponse.getEntity();
        val pageBody = EntityUtils.toString(entity);
    */
/*
    val pw = new PrintWriter(new File("hello2.txt" ))
    //pw.write(pageBody)
    pw.write(response.body)
    pw.close
*/
    /*
    val htmlCleaner = new HtmlCleaner()
    val domSerializer = new DomSerializer(new CleanerProperties())
    val tagNode = htmlCleaner.clean(response.body)
    //val tagNode = htmlCleaner.clean(pageBody)
    val doc = domSerializer.createDOM(tagNode)
    val xpath = XPathFactory.newInstance().newXPath()

    println( xpath.evaluate("//h1", doc) )
    */

/*
    val vg: VTDGen = new VTDGen();
    //val autopilot = new AutoPilot()
    //autopilot.selectXPath("/CATALOG/CD/COUNTRY/text()")
    //vg.loadIndex(response.toString)
    //vg.parseFile()
    //vg.setDoc(response.body.getBytes("UTF-8"))
    //println(response.body)
    vg.setDoc(response.body.getBytes)
    vg.parse(false)
  */  /*
    val vn:VTDNav = vg.getNav
    val ap:AutoPilot = new AutoPilot(vn)
    ap.selectXPath("/greeting/text()")
    val x = ap.evalXPath()
    if(x != -1) println("eval returned " + x)
    else println("eval failed")
    */
  }
}
