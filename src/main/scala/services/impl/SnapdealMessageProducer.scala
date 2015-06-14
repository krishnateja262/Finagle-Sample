package services.impl

import com.google.gson.Gson
import com.mashape.unirest.http.Unirest
import crawlers.BeanStalkProducer
import services.MessageProducer

import scala.xml.Elem

/**
 * Created by krishna on 6/9/15.
 */
class SnapdealMessageProducer extends MessageProducer{

  case class CategoryModel(name:String,url:String)

  override val beanStalkProducer: BeanStalkProducer = new BeanStalkProducer(14711,"localhost")

  val gson = new Gson()

  val parseXML = (response:String, tag:String,f:String => Elem,nameExtractor:String=>String)=>{
    val locSequence = f(response)\tag\"loc"
    locSequence.map(url => gson.toJson(new CategoryModel(nameExtractor(url.text),url.text))).toArray
  }

  override def generateMessages(): Unit = {
    val response = Unirest.get("http://www.snapdeal.com/sitemap/sitemap.xml").asString().getBody
    val categoryUrlModels = parseXML(response,
                            "sitemap",
                            (response:String)=>scala.xml.XML.loadString(response),
                            (urlText:String)=>urlText.substring(urlText.lastIndexOf("/")+1,urlText.lastIndexOf(".xml")))
    beanStalkProducer.pushMessages(categoryUrlModels,"snapdeal")
  }
}
