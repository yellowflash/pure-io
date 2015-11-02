package pureio

import java.util.Scanner

trait PureIOProgram {
  def main(arg:String):String
}

class Runtime {
  def run(program:PureIOProgram): Unit = {
    println(program.main(new Scanner(System.in).nextLine()))
  }
}

object Runtime {
  def main(args:Array[String]): Unit = {
    new Runtime().run(new SquareNumber())
  }
}
