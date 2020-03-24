import pack.classes.DonutStore
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}

class DonutStoreSpec extends FlatSpec with Matchers with ScalaFutures {

  behavior of "DonutStore class"

  "Dummy boolean test" should "be true" in {
    "DonutStore" shouldEqual "DonutStore"
  }

  val donutStore = new DonutStore()


  "favorite donut" should "match vanilla donut" in {
    donutStore.favoriteDonut() shouldEqual "Vanilla Donut"
    donutStore.favoriteDonut() === "vanilla donut"
    donutStore.favoriteDonut() should not equal "plain donut"
    donutStore.favoriteDonut() should not be "plain donut"
    donutStore.favoriteDonut() !== "Plain donut"
    donutStore.favoriteDonut() shouldEqual "Vanilla Donut"
  }


  "Length and size of donuts" should "be equal to 3" in {
    val donuts = donutStore.donuts()
    donuts should have size 3
    donuts should have length 3
  }


  "Examples of boolean assertions" should "be valid" in {
    val donuts = donutStore.donuts()
    donuts.nonEmpty shouldEqual true
    donuts.size === 3
    donuts.contains("chocolate donut") shouldEqual false
   // donuts.contains("chocolate donut") shouldEqual true
    donuts should not be empty
    donutStore.favoriteDonut() should not be empty
  }


  "Examples of collection assertions" should "be valid" in {
    val donuts = donutStore.donuts()
    donuts should contain ("Plain Donut")
    donuts should not contain "chocolate donut"
    donuts shouldEqual Seq("Vanilla Donut", "Plain Donut", "Glazed Donut")
  }


  "Examples of type assertions" should "be valid" in {
    donutStore shouldBe a [DonutStore]
    donutStore.favoriteDonut() shouldBe a [String]
    donutStore.donuts() shouldBe a [Seq[_]]
  }


  "Method DonutStore.printName()" should "throw IllegalStateException" in {
    intercept[java.lang.IllegalStateException] {
      donutStore.printName()
    }
  }


  "Exception thrown by method printName()" should "contain message Some Error" in {
    val exception = the [java.lang.IllegalStateException] thrownBy {
      donutStore.printName()
    }
    // here we verify that the exception class and the internal message
    exception.getClass shouldEqual classOf[java.lang.IllegalStateException]
    exception.getMessage should include ("Some Error")
  }


  "Exception thrown by method printName()" should "contain message Some Error using ScalaTest should have message methods" in {
    the [java.lang.IllegalStateException] thrownBy {
      donutStore.printName()
    } should have message "Some Error"
  }


  an [java.lang.IllegalStateException] should be thrownBy {
    donutStore.printName()
  }


  "Example of testing private method" should "be valid" in {
    val priceWithDiscount = donutStore.donutPrice("Vanilla Donut")
    priceWithDiscount shouldEqual Some(1.6)

    // test the private method discountByDonut()
    import org.scalatest.PrivateMethodTester._
    val discountByDonutMethod = PrivateMethod[Double](Symbol("discountByDonut"))
    val vanillaDonutDiscount = donutStore invokePrivate discountByDonutMethod("Vanilla Donut")
    vanillaDonutDiscount shouldEqual 0.2
  }

/*
  implicit override val patienceConfig: PatienceConfig =
    PatienceConfig(timeout = Span(5, Seconds), interval = Span(500, Millis))

  "Example of testing asynchronous futures" should "be valid" in {
    val donutSalesTaxFuture = donutStore.donutSalesTax("vanilla donut")

    whenReady(donutSalesTaxFuture) { salesTaxForVanillaDonut =>
      println(salesTaxForVanillaDonut)
      salesTaxForVanillaDonut shouldEqual 0.15
    }
  }

 */
}