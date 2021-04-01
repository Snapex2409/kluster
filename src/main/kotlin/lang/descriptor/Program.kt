package lang.descriptor

import lang.analysis.Analyzable
import lang.analysis.Report

/**
 * Represents a dynamically built program.
 * Will be parsed and transmitted to available processing units.
 * */
class Program(private val body: Body) : Analyzable{
    companion object{
        /**
         * API entry point to create a program.
         * [buildFun] Elements: body{ }
         * */
        fun create(buildFun : Builder.() -> Unit) : Program {
            return Builder().apply(buildFun).build()
        }
    }

    /**
     * Builder class to dynamically create a program instance.
     * */
     class Builder{
        /**
         * Buffer for body creation.
         * */
        var bBody : Body? = null

        /**
         * Function with lambda to dyn. create a body.
         * [buildFun] Elements: +statement{ }
         * */
        inline fun body(buildFun : Body.Builder.() -> Unit){
            bBody = Body.Builder().apply(buildFun).build()
        }

        /**
         * Starts the building process
         * */
        fun build() : Program {
            return Program(bBody ?: Body())
        }

    }

    /**
     * Analyzes the body
     * */
    override fun analyze() : Report {
        return body.analyze()
    }

    /**
     * String representation of the program.
     * */
    override fun toString(): String {
        return "Program:\n\n$body"
    }
}