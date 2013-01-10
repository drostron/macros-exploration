import EvalMacro._
import SimpleMacro._
import IfMacro._

object MacrosUsage {
  val i = List(
    parse("1 + 41 * 2 / 2"),
    eval("1 + 41 * 2"),
    eval("""println("boz?")"""),
    eval("""(new java.util.Date()).toString"""),
    printAST(42),
    ify(false, 1, 2)
  )
}