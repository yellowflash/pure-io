package pureio
import IO._

class SquareNumber extends PureIOProgram {
  override def main = {
    for(
      _ <- printLine("Enter a number:");
      line <- getLine;
      _ <- printLine("The square of the number is " + (line.toInt * line.toInt))
    ) yield ()
  }
}
