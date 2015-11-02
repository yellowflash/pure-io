package pureio

import java.util.Scanner

sealed trait IO {}

case class InputLine(fn:String => IO) extends IO
case class OutputLine(value:String, fn:() => IO) extends IO
case object Exit extends IO


trait PureIOProgram {
  def main:IO
}

class Runtime {
  val scanner = new Scanner(System.in)
  def run(program:PureIOProgram): Unit = {
    exec(program.main)
  }

  def exec:PartialFunction[IO, Unit] = {
    case InputLine(fn) => exec(fn(scanner.nextLine()))
    case OutputLine(line, fn) => println(line); exec(fn())
    case Exit =>
  }
}

object Runtime {
  def main(args:Array[String]): Unit = {
    new Runtime().run(new SquareNumber())
  }
}
