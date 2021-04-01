package lang.descriptor.controll

import lang.analysis.Analyzable
import lang.analysis.Report
import lang.descriptor.Body
import lang.descriptor.DataType
import lang.descriptor.Statement
import lang.descriptor.expression.Ex

class If(val cond : Ex<DataType.Bool>, val thenBody : Body, val elseBody : Body) : Analyzable, Statement(Body()) {
    /**
     * Builder class to dynamically create an if instance
     * */
    class Builder{
        var bThen : Body? = null
        var bElse : Body? = null

        inline fun thenBody(buildFun : Body.Builder.() -> Unit){
            bThen = Body.Builder().apply(buildFun).build()
        }

        inline fun elseBody(buildFun: Body.Builder.() -> Unit){
            bElse = Body.Builder().apply(buildFun).build()
        }

        fun build(cond : Ex<DataType.Bool>) : If {
            return If(cond, bThen ?: Body(), bElse ?: Body())
        }
    }

    override fun analyze(): Report {
        return Report("If-Statement: then=$thenBody else=$elseBody")
    }

    override fun toString(): String {
        return "If-Statement:\n cond=$cond \nthen=$thenBody \nelse=$elseBody"
    }
}