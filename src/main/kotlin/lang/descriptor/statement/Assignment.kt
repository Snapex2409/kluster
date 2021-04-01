package lang.descriptor.statement

import lang.analysis.Analyzable
import lang.analysis.Report
import lang.descriptor.Body
import lang.descriptor.DataType
import lang.descriptor.Statement
import lang.descriptor.expression.*

class Assignment<D : DataType>(val varName : String, private val expr : Ex<D>) : Analyzable, Statement(Body()) {
    /**
     * Builder class to dynamically create a program instance.
     * */
    class Builder<D : DataType>{
        /**
         * Buffer for statement creation
         * */
        var bExpr : Ex<D>? = null

        /**
         * Function with lambda to dyn. create a body.
         * [buildFun] Element: +body{ }
         * */
        inline fun expr(buildFun: Ex.Builder<D>.() -> Unit) {
            bExpr = Ex.Builder<D>().apply(buildFun).build()
        }

        /**
         * Starts the building process
         * */
        fun build(name : String) : Assignment<*> {
            return Assignment(name, bExpr ?: Empty())
        }
    }

    override fun analyze(): Report {
        return Report("Assignment: to=$varName with=$expr")
    }

    override fun toString(): String {
        return "Assignment: to=$varName with=$expr"
    }
}