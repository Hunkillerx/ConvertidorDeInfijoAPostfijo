/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad EAN (Bogotá - Colombia)
 * Departamento de Sistemas
 * Faculta de Ingeniería
 * <p>
 * Proyecto Taller con las Pilas
 * Autor: Universidad EAN - Marzo 23, 2018
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package ean.programacionavanzada.taller

import ean.collections.IList
import ean.collections.Utils
import ean.collections.Utils.join
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ConvertidorDeInfijoAPostfijoTest {
    //-------------------------------------
    // Métodos
    //-------------------------------------
    @Test
    fun probarBalanceador() {
        // Ejemplo de expresión bien balanceada
        assertTrue {
            checkBalance("[a {b / (c-d) + e/(f+g)}-h]")
        }

        // Ejemplo de expresión mal balanceada
        assertFalse {
            checkBalance("a {b [c - d ] e] ) f")
        }

        // Ejemplo de expresión mal balanceada
        assertFalse {
            checkBalance("{a ( b * c ) / [d + e] / f )- g}")
        }
    }

    @Test
    fun testReplaceDelimiters() {
        val first = replaceDelimiters("x [ {a ( b * c ) / [d + e] / f }- g]")
        val secnd = dividirExpresion("x ( (a ( b * c ) / (d + e) / f )- g)")
        assertTrue(first == secnd)
    }

    @Test
    fun probarConvertirInfijoPostfijo() {
        var postfijo: String

        // 1. Primera prueba
        postfijo = convertir("40 + 30 - a")
        assertEquals("40 30 + a -", postfijo)

        // 2. Prueba
        postfijo = convertir("a + b * c")
        assertEquals("a b c * +", postfijo)

        // 3. Prueba
        postfijo = convertir("(a - b) * c")
        assertEquals("a b - c *", postfijo)

        // 4. Prueba
        postfijo = convertir("a ^ b ^ c")
        assertEquals("a b c ^ ^", postfijo)

        // 5. Prueba
        postfijo = convertir("a / b * (c + (d - 5))")
        assertEquals("a b / c d 5 - + *", postfijo)

        // 6. Prueba
        postfijo = convertir("a / (b - c) * d")
        assertEquals("a b c - / d *", postfijo)

        // 7. Prueba
        postfijo = convertir("a - (b / (c - d) * e + f ) ^ g")
        assertEquals("a b c d - / e * f + g ^ -", postfijo)

        // 8. Prueba
        postfijo = convertir("(a - b * c) / (d * e ^ f % g + h)")
        assertEquals("a b c * - d e f ^ * g % h + /", postfijo)

        // 9. Prueba
        postfijo = convertir("a * (b + c * d) + e")
        assertEquals("a b c d * + * e +", postfijo)
    }

    @Test
    fun pruebaFinal() {
        val aEvaluar = dividirExpresion("{[2 * 3] / (4 - 2)} + {5 * 6}}")
        if (Evaluator.checkBalance(aEvaluar)) {
            Evaluator.replaceDelimiters(aEvaluar)
            val expresiónFinal = Evaluator.convertToPostfix(aEvaluar)
            val valorFinal = Evaluator.evaluatePostfix(expresiónFinal)
            assertEquals(33, valorFinal)
        }
        else {
            fail("Algo malo ocurrió por aquí")
        }
    }
}

//-----------------------------------------------------------------------------------------------------------

/**
 * Función de utilidad para realizar la conversión
 */
fun convertir(expresión: String): String {
    val anExpression = Utils.parse(expresión)
    return join(Evaluator.convertToPostfix(anExpression))
}

/**
 */
fun dividirExpresion(expresión: String): IList<String> = Utils.parse(expresión)

fun checkBalance(expresión: String): Boolean {
    return Evaluator.checkBalance(dividirExpresion(expresión))
}

fun replaceDelimiters(expresión: String): IList<String> {
    val expr = dividirExpresion(expresión)
    Evaluator.replaceDelimiters(expr)
    return expr
}