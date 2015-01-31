package com.github.spikevlg.habraparserscala

object Application {


  def main (args: Array[String]): Unit = {
    val hp = new HabraParser
    val lastPostId = hp.getLastPostId
    val habraItem = hp.parse(lastPostId)
    println(habraItem)
  }
}
