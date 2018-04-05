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
import ean.collections.IStack
import ean.collections.LinkedList
import ean.collections.LinkedStack

/**
 * Objeto que permite convertir una expresión infija normal a una expresión en notación
 * postfija. Utiliza pilas para realizar la conversión.
 */
object Evaluator {

    //-------------------------------------
    // Métodos
    //-------------------------------------

    /**
     * Verifica que la expresión tiene los símbolos de agrupación bien balanceados
     * @return true si la expresión está balanceados
     */
    fun checkBalance(expression: IList<String>): Boolean {
        val pilaSimbolos: IStack<String> = LinkedStack()

        TODO("Verifica que la expresión tenga los símbolos de agrupación balanceados")
    }

    /**
     * Transforma la expresión, cambiando los simbolos de agrupación [] y {} por ()
     */
    fun replaceDelimiters(expression: IList<String>): Unit {
        TODO("Cambia los simbolos de agrupación por paréntesis")
    }

    /**
     * Realiza la conversión de la notación infija a postfija
     * @return la expresión convertida a postfija
     */
    fun convertToPostfix(expression: IList<String>): IList<String> {
        val pila: IStack<String> = LinkedStack()
        val lista: IList<String> = LinkedList()

        TODO("Realizar la conversión de expressión a postfija")

        return lista
    }

    fun evaluatePostfix(expression: IList<String>): Int {
        val pila: IStack<Int> = LinkedStack()

        TODO("Evaluar la expresión en notación postfija")

        return pila.peek()
    }
}