import org.jsoup.Jsoup
import org.jsoup.nodes.Document
class Printer{
    fun print(str : String){
        val doc :Document = Jsoup.connect("https://example.com/").get()
    }
}

