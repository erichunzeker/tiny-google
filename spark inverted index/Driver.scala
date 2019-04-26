import org.apache.spark.{ SparkConf, SparkContext }
import org.apache.spark.sql._
import org.apache.spark.sql.SQLContext._
import org.apache.spark.sql.hive.HiveContext

object Driver {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("inverted index")

    // Read input file and filter all stopwords
    sc.wholeTextFiles("/user/chatree/project/docs/").flatMap {
      case (path, text) =>
        text.replaceAll("[^\\w\\s]|('s|ly|ed|ing|ness) ", " ")
          .split("""\W+""")
          .map {
            word => (word, path)
          }
    }.map {
      case (w, p) => ((w, p.split("/")(6)), 1)
    }.reduceByKey {
      case (n1, n2) => n1 + n2
    }.map {
      case ((w, p), n) => (w, (p, n))
    }.groupBy {
      case (w, (p, n)) => w
    }.map {
      case (w, seq) =>
        val seq2 = seq map {
          case (_, (p, n)) => (p, n)
        }
        (w, seq2.mkString(", "))
    }.saveAsTextFile("./sparkoutput")
  }
}
