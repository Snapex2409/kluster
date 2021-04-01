package lang.descriptor

/**
 * Listing of all possible data types.
 * */
 abstract class DataType {
    protected val base : BaseType
    protected val subType : DataType?

    private constructor(base: BaseType, subType : DataType? = null){
        this.base = base
        this.subType = subType
    }

    /**
     * Base types
     * */
    enum class BaseType {
        INT,
        FLOAT,
        BOOL,
        ARRAY
    }

    /**
     * Singletons
     * */
    companion object{
        val INT = Integer()
        val FLOAT = Float()
        val BOOL = Bool()

        @JvmStatic
        fun <D : DataType> ARRAY(subType: D) : Array<D>{
            return Array(subType)
        }
    }

    class Integer : DataType(BaseType.INT){
        class Operators{
            companion object{
                fun add() : Operator<Integer>{
                    return Add()
                }
                fun sub() : Operator<Integer>{
                    return Sub()
                }
                fun mul() : Operator<Integer>{
                    return Mul()
                }
                fun div() : Operator<Integer>{
                    return Div()
                }
                fun shl() : Operator<Integer>{
                    return Shl()
                }
                fun shr() : Operator<Integer>{
                    return Shr()
                }
                fun ashr() : Operator<Integer>{
                    return Ashr()
                }
            }
        }

        override fun toString(): String {
            return "Type: Int"
        }
    }

    class Float : DataType(BaseType.FLOAT){
        class Operators{
            companion object{
                fun add() : Operator<Float>{
                    return Add()
                }
                fun sub() : Operator<Float>{
                    return Sub()
                }
                fun mul() : Operator<Float>{
                    return Mul()
                }
                fun div() : Operator<Float>{
                    return Div()
                }
            }
        }

        override fun toString(): String {
            return "Type: Float"
        }
    }

    class Bool : DataType(BaseType.BOOL){
        class Operators{
            companion object{
                fun and() : Operator<Bool>{
                    return And()
                }
                fun or() : Operator<Bool>{
                    return Or()
                }
            }
        }

        override fun toString(): String {
            return "Type: Bool"
        }
    }

    class Array<D : DataType>(subType: D) : DataType(BaseType.ARRAY, subType){
        class Operators{

        }

        override fun toString(): String {
            return "Type: Array of ${this.subType}"
        }
    }
}