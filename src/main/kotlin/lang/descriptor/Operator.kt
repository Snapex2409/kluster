package lang.descriptor

open class Operator<D : DataType>{
    protected constructor()
}

class Add<D : DataType> : Operator<D>(){
    override fun toString(): String {
        return "Operator: Add"
    }
}
class Sub<D : DataType> : Operator<D>(){
    override fun toString(): String {
        return "Operator: Sub"
    }
}
class Div<D : DataType> : Operator<D>(){
    override fun toString(): String {
        return "Operator: Div"
    }
}
class Mul<D : DataType> : Operator<D>(){
    override fun toString(): String {
        return "Operator: Mul"
    }
}
class Shl<D : DataType> : Operator<D>(){
    override fun toString(): String {
        return "Operator: Shl"
    }
}
class Shr<D : DataType> : Operator<D>(){
    override fun toString(): String {
        return "Operator: Shr"
    }
}
class Ashr<D : DataType> : Operator<D>(){
    override fun toString(): String {
        return "Operator: Ashr"
    }
}
class And<D : DataType> : Operator<D>(){
    override fun toString(): String {
        return "Operator: And"
    }
}
class Or<D : DataType> : Operator<D>(){
    override fun toString(): String {
        return "Operator: Or"
    }
}