import java.util.NoSuchElementException

trait Box[+T] {
  def isEmpty: Boolean

  def get: T

  def getOrElse[A >: T](default: A): A

  def map[A](f: T => A): Box[A]

  def flatMap[A](f: (T) => Box[A]): Box[A]
}

case class Full[+T](value: T) extends Box[T] {
  override def isEmpty: Boolean = false

  override def get: T = value

  def getOrElse[A >: T](default: A): A = value

  override def map[A](f: (T) => A): Box[A] = Full[A](f(value))

  override def flatMap[A](f: (T) => Box[A]): Box[A] = f(value)
}


object Empty extends Box[Nothing]{
  def isEmpty: Boolean = true

  def get: Nothing = throw new NoSuchElementException()

  def getOrElse[A >: Nothing](default: A): A = default

  def map[A](f: (Nothing) => A): Box[A] = Empty

  def flatMap[A](f: (Nothing) => Box[A]): Box[A] = Empty
}
