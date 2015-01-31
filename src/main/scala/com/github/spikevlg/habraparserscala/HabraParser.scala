package com.github.spikevlg.habraparserscala

class HabraParser {
  val grab = new HtmlClientGrab
  val parser = new HtmlHabraParser

  def parse(postId : Int) : HabraItem = {
    val page = grab.go(s"http://habrahabr.ru/post/$postId")
    parser.parsePost(postId, page)
  }

  def getLastPostId : Int = {
    val page = grab.go("http://habrahabr.ru/")
    parser.getLastPostID(page)
  }
}
