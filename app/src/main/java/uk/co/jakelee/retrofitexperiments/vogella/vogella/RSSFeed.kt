package uk.co.jakelee.retrofitexperiments.vogella.vogella

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path


class RSSFeed {

    @Element(name = "title")
    @Path("channel")
    var channelTitle: String? = null

    @ElementList(name = "item", inline = true)
    @Path("channel")
    var articleList: List<Article>? = null

}