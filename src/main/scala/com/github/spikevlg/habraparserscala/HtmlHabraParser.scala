package com.github.spikevlg.habraparserscala

import javax.xml.xpath.{XPathConstants, XPathFactory}

import org.htmlcleaner.{CleanerProperties, DomSerializer, HtmlCleaner}

import org.w3c.dom.{NodeList, Document}

import scala.collection.mutable


class HtmlHabraParser {
  val htmlCleaner = new HtmlCleaner()
  val domSerializer = new DomSerializer(new CleanerProperties())
  val xpath = XPathFactory.newInstance().newXPath()

  def parsePost(postId : Integer, postPage : String) : HabraItem = {
    def parseDouble(xPathStr : String, doc : Document) : Double = xpath.evaluate(xPathStr, doc
        , XPathConstants.NUMBER).asInstanceOf[Double]
    def parseInteger(xPathStr : String, doc : Document) : Int = parseDouble(xPathStr, doc).toInt
    def parseBoolean(xPathStr : String, doc : Document) : Boolean = xpath.evaluate(xPathStr, doc
        , XPathConstants.BOOLEAN).asInstanceOf[Boolean]
    def strToDouble(s: String) = try { Some(s.toDouble) } catch { case e : NumberFormatException => None }

    val tagNode = htmlCleaner.clean(postPage)
    val doc = domSerializer.createDOM(tagNode)

    val scoreStr = xpath.evaluate("//span[@class='score']/text()", doc)
    print("=======" + xpath.evaluate("//h1//span[@class='post_title']/text()", doc))
    HabraItem(
        id = postId
        , title = xpath.evaluate("//h1//span[@class='post_title']/text()", doc)
        , countStars = parseInteger("//div[@class='favs_count']/text()", doc)
        , author = xpath.evaluate("//div[@class='author']//a/text()", doc)
        , pageView = parseInteger("//div[@class='pageviews']/text()", doc)
        , isTranslate = parseBoolean("count(//h1//span[contains(@class, 'flag_translation')]) > 0", doc)
        , score = strToDouble(scoreStr.replace("+", ""))
        , hubs = parseListString("//div[@class='hubs']/a/text()", doc)
        , tags = parseListString("//ul[@class='tags']/li/a/text()", doc)
    )
  }

  def getLastPostID(mainPage : String) : Int = {

    val tagNode = htmlCleaner.clean(mainPage)
    val doc = domSerializer.createDOM(tagNode)
    val seqPostId = for (curItem <- parseListString(
        "//div[@class='posts_list']/div/div[contains(@id, 'post_')]/@id", doc)
    ) yield curItem.split("_")(1).toInt
    seqPostId.max
  }

  private def parseListString(xPathStr : String, doc : Document) : List[String] = {
    val nodeList = xpath.evaluate(xPathStr, doc, XPathConstants.NODESET).asInstanceOf[NodeList]
    val listString = new mutable.MutableList[String]
    val seqString  = for (i <- 0 to nodeList.getLength - 1) yield nodeList.item(i).getNodeValue
    seqString.toList
  }
}
