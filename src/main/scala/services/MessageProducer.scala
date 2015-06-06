package services

import crawlers.BeanStalkProducer

/**
 * Created by krishna on 6/3/15.
 */
trait MessageProducer{

  val beanStalkProducer:BeanStalkProducer

  def generateMessages()
}
