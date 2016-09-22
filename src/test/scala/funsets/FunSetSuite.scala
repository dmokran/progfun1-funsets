package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
   test("string take") {
     val message = "hello, world"
     assert(message.take(5) == "hello")
   }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
   test("adding ints") {
     assert(1 + 1 === 2)
   }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => (x == 1), 1))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val ss1 = singletonSet(1)
    val ss2 = singletonSet(2)
    val ss3 = singletonSet(3)
    val ss4 = singletonSet(4)
    val ss5 = singletonSet(5)
    val ss6 = singletonSet(6)
    val ss12 = union(ss1, ss2)
    val ss123 = union(ss12, ss3)
    val ss23 = union(ss2, ss3)
    val ss234 = union(ss23, ss4)
    val ss34 = union(ss3, ss4)
    val ss345 = union(ss34, ss5)
    val ss45 = union(ss4, ss5)
    val ss456 = union(ss45, ss6)

  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSets contains X") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "1 IS in 1")
      assert(!contains(s1, 2), "2 NOT in 1")
      assert(contains(s2, 2), "2 IS in 2")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3 - not in (1, 2)")
      assert(contains(ss12, 2), "2 in (1,2)")
      assert(!contains(ss12, 3), "3 NOT in (1,2)")
      assert(!contains(ss456, 2), "2 NOT in (4,5,6)")
    }
  }

  test("intersect contains only common elements between 2 sets") {
    new TestSets {
      val in234_345 = intersect(ss234, ss345)
      assert(contains(in234_345, 3), "3 intersects (2,3,4) and (3,4,5)")
      assert(contains(in234_345, 4), "4 intersects (2,3,4) and (3,4,5)")
      assert(!contains(in234_345, 5), "5 does NOT intersect (2,3,4) and (3,4,5)")
      assert(!contains(in234_345, 2), "2 does NOT intersect (2,3,4) and (3,4,5)")
    }
  }

  test("difference between 2 sets yields elements from 1st set not in 2nd set") {
    new TestSets {
      val di234_345 = diff(ss234, ss345)
      assert(contains(di234_345, 2), "2 IS in (2,3,4) and NOT in (3,4,5)")
      assert(!contains(di234_345, 3), "3 IS in (2,3,4) and IN (3,4,5)")
      assert(!contains(di234_345, 5), "5 IS NOT in (2,3,4)")
      assert(!contains(di234_345, 6), "6 IS NOT in (2,3,4) nor (3,4,5)")
    }
  }

  test("filter out 3's from the set") {
    new TestSets {
      val fil3 = filter(ss234, x => x != 3)
      assert(contains(fil3, 2), "2 IS in (2,3,4) and IS NOT 3")
      assert(!contains(fil3, 3), "3 IS in (2,3,4) and IN 3")
      assert(contains(fil3, 4), "4 IS in (2,3,4) and IS NOT 3")
      assert(!contains(fil3, 6), "6 IS NOT in (2,3,4)")
    }
  }

}
