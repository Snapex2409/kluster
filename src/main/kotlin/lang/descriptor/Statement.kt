package lang.descriptor

import lang.analysis.Analyzable
import lang.analysis.Report

/**
 * Conceptual representation of a statement in programming languages.
 * To be used a SAM.
 * Statements do not have a return value.
 * */
open class Statement(private val body : Body) : Analyzable{
    /**
     * Builder class to dynamically create a program instance.
     * */
     class Builder{
        /**
         * Buffer for statement creation
         * */
        var bBody : Body? = null

        /**
         * Function with lambda to dyn. create a body.
         * [buildFun] Element: +statement{ }
         * */
        inline fun body(buildFun: Body.Builder.() -> Unit) {
            bBody = Body.Builder().apply(buildFun).build()
        }

        /**
         * Starts the building process
         * */
        fun build() : Statement {
            return Statement(bBody ?: Body())
        }
    }

    /**
     * Analyze function
     * */
    override fun analyze() : Report {
        return body.analyze()
    }

    /**
     * String representation of the statement
     * */
    override fun toString(): String {
        return "Statement: \n$body "
    }
}