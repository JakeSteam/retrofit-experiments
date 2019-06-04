package uk.co.jakelee.retrofitexperiments.vogella.vogella

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
class Article {

    @Element(name = "title")
    var title: String? = null

    @Element(name = "link")
    var link: String? = null
}