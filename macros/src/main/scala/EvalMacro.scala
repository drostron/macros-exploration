import language.experimental.macros
import reflect.macros.Context
import scala.reflect.runtime.universe.runtimeMirror
import scala.tools.reflect.ToolBox

// compile time injection attacks for the masses
object EvalMacro {
  def parse(s: String): Any = macro parseImpl

  def parseImpl(c: Context)(s: c.Expr[String]): c.universe.Expr[Any] = {
    import c.universe._

    println("running eval macro")

    val Literal(Constant(evalString: String)) = s.tree

    val parsed = c.Expr(c.parse(evalString))

    println(s"eval macro evaluated to: $parsed")

    parsed
  }

  def eval(s: String) = macro evalImpl

  def evalImpl(c: Context)(s: c.Expr[String]): c.universe.Expr[Any] = {
    import c.universe._

    println("running eval macro")

    val tb = runtimeMirror(this.getClass.getClassLoader).mkToolBox()

    val Literal(Constant(evalString: String)) = s.tree

    val parsed: tb.u.Tree = tb.parse(evalString)
    val evaluated = tb.eval(parsed)

    val parsedAndEvaled = c.Expr(Literal(Constant(evaluated)))

    println(s"eval macro evaluated to: $parsedAndEvaled")

    parsedAndEvaled
  }
}
