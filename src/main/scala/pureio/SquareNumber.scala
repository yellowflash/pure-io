package pureio

class SquareNumber extends PureIOProgram {
  override def main(arg: String): String = (arg.toInt * arg.toInt).toString
}
