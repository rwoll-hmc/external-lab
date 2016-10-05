package calculator.parser

import scala.util.parsing.combinator._
import calculator.ir._

/**
 * -----------
 * Grammar
 * -----------
 * 
 *                   n ∈ 𝒵 
 * 
 *       e ∈ Expr ::= e + t | e - t | t
 *       t ∈ Term ::= t * f | t / f | f
 *       f ∈ Fact ::= n | ( e )
 *  
 */

object CalcParser extends JavaTokenParsers with PackratParsers{

    // parsing interface
    def apply(s: String): ParseResult[Expr] = parseAll(expr, s)

    // expressions
    lazy val expr: PackratParser[Expr] = 
      (   expr~"+"~term ^^ {case l~"+"~r ⇒ Plus(l, r)}
        | expr~"-"~term ^^ {case l~"-"~r ⇒ Sub(l, r)}
        | term )
        
    // term 
    lazy val term: PackratParser[Expr] = 
      (   term~"*"~factor ^^ {case l~"*"~r ⇒ Mult(l,r)}
        | term~"*"~factor ^^ {case l~"/"~r ⇒ Div(l,r)}
        | factor)
        
    // factors
    lazy val factor: PackratParser[Expr] =
      (   "("~expr~")" ^^ {case "("~expr~")" ⇒ expr}
        | number)
      
    // numbers
    def number: Parser[Num] = wholeNumber ^^ {s ⇒ Num(s.toInt)}
    
 }
