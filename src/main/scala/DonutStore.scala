package pack.classes

class DonutStore {
  def favoriteDonut (): String = "Vanilla Donut"

  def donuts(): Seq[String] = Seq("Vanilla Donut", "Plain Donut", "Glazed Donut")

  def printName(): Unit = { throw new IllegalStateException("Some Error")}

  def donutPrice(donut: String): Option[Double] = {
    val prices = Map (
      "Vanilla Donut" -> 2.0,
      "Plain Donut" -> 1.0,
      "Glazed Donut" -> 3.0
    )
    val priceOfDonut = prices.get(donut)
    priceOfDonut.map {price => price * (1 - discountByDonut(donut))}
  }

  private def discountByDonut(donut: String): Double = {
    val discounts = Map(
      "Vanilla Donut" -> 0.2,
      "Plain Donut" -> 0.1,
      "Glazed Donut" -> 0.3
    )
    discounts.getOrElse(donut, 0)
  }

}