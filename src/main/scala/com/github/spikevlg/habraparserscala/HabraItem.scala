package com.github.spikevlg.habraparserscala


case class HabraItem(
                      id : Int
                      , title : String
                      , countStars : Int
                      , author : String
                      , pageView : Int
                      , isTranslate : Boolean
                      , score : Option[Double]
                      , hubs : List[String]
                      , tags : List[String]
)