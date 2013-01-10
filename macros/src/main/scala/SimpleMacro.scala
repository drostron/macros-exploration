import language.experimental.macros
import reflect.macros.Context
import reflect.runtime.universe._

object SimpleMacro {
  def printAST[T](i: T) = macro printASTImpl[T]

  def printASTImpl[T](c: Context)(i: c.Expr[T]): c.Expr[Unit] = {
    import c.universe._

    println("running printASTImpl macro")

    println(s"printlnASTImpl: ${showRaw(i)}")

    c.Expr[Unit](Literal(Constant()))
  }
}