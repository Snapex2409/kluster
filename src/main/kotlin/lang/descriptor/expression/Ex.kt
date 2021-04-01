package lang.descriptor.expression

import lang.analysis.Analyzable
import lang.analysis.Report
import lang.descriptor.DataType
import lang.descriptor.Operator

open class Ex<D : DataType>(private val lOp : Ex<D>?, private val op : Operator<*>?, private val rOp : Ex<*>?) : Analyzable {
    /**
     * Builder class to dynamically create an Expression
     * */
    class Builder<D : DataType>{
        var bLeftOp : Ex<D>? = null
        var bOp : Operator<D>? = null
        var bRightOp : Ex<D>? = null

        /**
         * Creates the left Expression
         * */
        inline fun leftEx(exFun : Ex.Builder<D>.() -> Ex<D>){
            bLeftOp = exFun()
        }

        /**
         * Creates the operator
         * */
        inline fun op(opFun : () -> Operator<D>){
            bOp = opFun()
        }

        /**
         * Creates the right Expression
         * */
        inline fun rightEx(exFun : Ex.Builder<D>.() -> Ex<D>){
            bRightOp = exFun()
        }

        /**
         * Creates a constant for the left expression
         * */
        fun lConst(type : D, value : String){
            bLeftOp = Const(type, value)
        }

        /**
         * Creates a constant for the right expression
         * */
        fun rConst(type : D, value : String){
            bRightOp = Const(type, value)
        }

        /**
         * Creates a constant for the left expression
         * */
        fun lVar(type : D, value : String){
            bLeftOp = Var(type, value)
        }

        /**
         * Creates a constant for the right expression
         * */
        fun rVar(type : D, value : String){
            bRightOp = Var(type, value)
        }

        /**
         * Starts the building process
         * */
        fun build() : Ex<D>{
            return Ex(bLeftOp, bOp, bRightOp)
        }
    }

    override fun analyze(): Report {
        TODO("Not yet implemented")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toString(): String {
        return "Ex(lOp=$lOp, op=$op, rOp=$rOp)"
    }
}

class Const<D : DataType>(val type : D, val value : String) : Ex<D>(null, null, null){
    override fun toString(): String {
        return "Expression: Constant=$value"
    }
}

class Var<D : DataType>(val type : D, val name : String) : Ex<D>(null, null, null){
    override fun toString(): String {
        return "Expression: Access to var=$name"
    }
}

class Empty : Ex<DataType>(null, null, null){
    override fun toString(): String {
        return "Expression: Empty"
    }
}

fun <D : DataType> expr( buildFun : Ex.Builder<D>.() -> Unit) : Ex<D> {
    return Ex.Builder<D>().apply(buildFun).build()
}