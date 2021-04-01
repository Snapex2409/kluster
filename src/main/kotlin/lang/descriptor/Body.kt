package lang.descriptor

import lang.analysis.Analyzable
import lang.analysis.Report
import lang.descriptor.controll.If
import lang.descriptor.expression.Ex
import lang.descriptor.statement.Allocate
import lang.descriptor.statement.AllocateArray
import lang.descriptor.statement.Assignment
import java.lang.StringBuilder

/**
 * Body of something.
 * */
class Body(private vararg val stmts : Statement) : Analyzable{
    /**
     * Builder class to dynamically create a program instance.
     * */
     class Builder{
        /**
         * Buffer for statement creation
         * */
        private val bStatements = mutableListOf<Statement>()

        /**
         * Adds the statement to this Body
         * */
        operator fun Statement.unaryPlus(){
            bStatements.add(this)
        }

        /**
         * Function with lambda to dyn. create a statement.
         * [buildFun] Elements: body{ }
         * */
        inline fun statement(buildFun : Statement.Builder.() -> Unit) : Statement{
            return Statement.Builder().apply(buildFun).build()
        }

        /**
         * Function with lambda to dyn. create an allocation.
         * [name] Name of the variable
         * [type] Type of the variable
         * */
        fun alloc(name : String, type : DataType) : Statement{
            return Allocate(name, type)
        }

        /**
         * Function with lambda to dyn. create an allocation for array
         * [name] Name of the array
         * [len] Length of the array
         * [type] Type of the array
         * */
        fun  allocA(name : String, len : Int, type : DataType) : Statement{
            return AllocateArray(name, len, type)
        }

        /**
         * Function with lambda to dyn. create an allocation for array
         * [name] Name of the var
         * [buildFun] Specifies the assignment
         * */
        fun <D : DataType> assign(name : String, type : D, buildFun : Assignment.Builder<D>.() -> Unit) : Statement{
            return Assignment.Builder<D>().apply(buildFun).build(name)
        }

        /**
         * Function with lambda to dyn. create if statement
         * [cond] Condition for block selection
         * [buildFun] Specifies the blocks
         * */
        fun ifStmt(cond : Ex<DataType.Bool>, buildFun : If.Builder.() -> Unit) : Statement{
            return If.Builder().apply(buildFun).build(cond)
        }

        /**
         * Starts the building process
         * */
        fun build() : Body {
            return Body(*bStatements.toTypedArray())
        }
    }

    /**
     * Analyzes the body
     * */
    override fun analyze() : Report {
        return stmts.iterator().asSequence()
                .map { it.analyze() }
                .reduce {r0 , r1 -> Report.combine (r0, r1)}
    }

    /**
     * String representation of the body
     * */
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("Body: \n")
        stmts.forEach { sb.append("\t").append(it.toString()).append("\n") }
        sb.append("\n")
        return sb.toString()
    }
}