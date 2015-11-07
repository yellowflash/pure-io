package pureio

import java.util.Scanner

object IO {
  trait IO [T] {
    def flatMap[L](fn: T => IO[L]):IO[L] = {
      Sequence(this, fn)
    }
    def map[L](fn: T => L):IO[L] = Sequence(this, {v:T => Return(fn(v))})
    def exec:IOResult[T]
  }
  private case object InputLine extends IO[String]  {
    val scanner = new Scanner(System.in)
    override def exec: IOResult[String] = IOResult(scanner.nextLine())
  }
  private case class Sequence[T,K](action:IO[T], fn: T => IO[K]) extends IO[K] {
    override def exec: IOResult[K] = fn(action.exec.value).exec
  }
  private case class OutputLine(line:String) extends IO[Unit] {
    override def exec: IOResult[Unit] = {
      println(line)
      IOResult(Unit)
    }
  }
  private case class Return[T](value:T) extends IO[T] {
    override def exec: IOResult[T] = IOResult(value)
  }

  def printLine(line:String):IO[Unit] = OutputLine(line)
  def getLine:IO[String] = InputLine
}
case class IOResult[T](value:T)

trait PureIOProgram {
  import IO.IO
  def main:IO[_]
}

class Runtime {
  def run(program:PureIOProgram): Unit = {
    program.main.exec
  }
}

object Runtime {
  def main(args:Array[String]): Unit = {
    new Runtime().run(new SquareNumber())
  }
}
