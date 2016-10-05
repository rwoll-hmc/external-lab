package calculator.ir

sealed abstract class Expr

case class Num(n: Int) extends Expr
case class Plus(left: Expr, right: Expr) extends Expr
case class Sub(left: Expr, right: Expr) extends Expr
case class Div(left: Expr, right: Expr) extends Expr
case class Mult(left: Expr, right: Expr) extends Expr