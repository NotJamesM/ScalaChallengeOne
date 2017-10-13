import org.scalatest.FunSuite
import org.scalatest.Matchers._

class BoxTest extends FunSuite {

  test("When a box has a value, isEmpty returns false") {
    val testBox = Full[Int](1)
    testBox.isEmpty shouldBe false
  }
  test("When a box has no value, isEmpty returns true") {
    val testBox = Empty
    testBox.isEmpty shouldBe true
  }

  test("Calling get on a full box returns the value stored in it") {
    val testBox = Full[Int](1)
    testBox.get shouldBe 1
  }

  test("Calling get on an empty box throws a NoSuchElementException") {
    val testBox = Empty
    assertThrows[NoSuchElementException] {
      testBox.get
    }
  }

  test("Calling getOrElse on a Full should return the value") {
    val testBox = Full[Int](1)
    testBox.getOrElse(0) shouldBe 1
  }

  test("Calling getOrElse on an Empty should return the default value passed") {
    val testBox = Empty
    testBox.getOrElse(0) shouldBe 0
  }

  test("Calling map on a Full returns a new Full with a transformed value") {
    val testBox = Full[Int](1)
    testBox.map{ x => x + 1} shouldBe Full[Int](2)
  }

  test("Calling map on a Empty returns an Empty") {
    val testBox = Empty
    testBox.map{x=>x} shouldBe Empty
  }

  test("Calling flatMap on a Full returns a new box containing the transformed value removed from the nested box") {
    val testBox = Full[Int](1)
    testBox.flatMap{ x => Full[Int](x + 1)} shouldBe Full[Int](2)
  }

  test("Calling flatMap on a Empty returns a new Empty") {
    val testBox = Empty
    testBox.flatMap{x=>Empty} shouldBe Empty
  }
}

