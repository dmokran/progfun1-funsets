package funsets

object Main extends App {
  import FunSets._
  println(contains(singletonSet(1), 1))
  val ss3 = singletonSet(3)
  val ss4 = singletonSet(4)
  val ss5 = singletonSet(5)
  val ss34 = union(ss3, ss4)
  val ss345 = union(ss34, ss5)
  printSet(ss345)
  val mapped = map(ss345, x => x*x)
  printSet(mapped)
}
