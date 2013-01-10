import language.experimental.macros
import reflect.macros.Context

object IfMacro {

  def ify[A, B](b: Boolean, trueExpr: A, falseExpr: B) = macro ifyImpl[A, B]

  def ifyImpl[A, B](c: Context)(b: c.Expr[Boolean], trueExpr: c.Expr[A], falseExpr: c.Expr[B]): c.universe.Expr[Any] = {
    import c.universe._

    println("running ifyImpl macro")

    c.Expr[Any](If(reify { b.splice }.tree, reify { trueExpr.splice }.tree, reify { falseExpr.splice }.tree ))
  }
}