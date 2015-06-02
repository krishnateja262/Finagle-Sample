import com.google.gson.Gson
import com.voodoo.server.services.impl.SearchServiceImpl
import io.undertow.server.{HttpHandler, HttpServerExchange}
import io.undertow.{Handlers, Undertow}
import services.AsyncProductClient

/**
 * Created by krishna on 5/27/15.
 */
object Server extends App{

  val server: Undertow = Undertow.builder().addHttpListener(8080, "localhost").setHandler(Handlers.path()
    .addPrefixPath("/api/products", new JsonHandler)).setWorkerThreads(200).build()

  val gson = new Gson()

  class JsonHandler extends HttpHandler{
    override def handleRequest(exchange: HttpServerExchange): Unit = {
      if(exchange.isInIoThread){
        exchange.dispatch(this)
        return
      }

      val queryParams = exchange.getQueryParameters

      if(!queryParams.containsKey("title") && !queryParams.containsKey("merchant")){
        exchange.getResponseSender.send("{\"status\":\"400\",\"error\":\"Requires title and merchant query parameters\"}")
      }

      val searchService = new SearchServiceImpl
      val asyncProductClient = new AsyncProductClient(searchService)

      val title:String = queryParams.get("title").getFirst
      val merchant:String = queryParams.get("merchant").getFirst
      exchange.getResponseSender.send(gson.toJson(asyncProductClient.classifyRequest(title,merchant)))
    }
  }

  server.start()
}