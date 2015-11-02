package pureio

class SquareNumber extends PureIOProgram {
  override def main = {
    OutputLine("Enter a number: ", () => {
      InputLine(line => {
        OutputLine("The square of the number is " + (line.toInt *line.toInt), () => Exit)
      })
    })
  }
}
