import lang.descriptor.DataType
import lang.descriptor.Program
import lang.descriptor.expression.Ex
import lang.descriptor.expression.expr

fun main() {
    test()
}

fun test(){
    val p = Program.create {
        body {
            +alloc("v0", DataType.INT)
            +alloc("v1", DataType.INT)
            +alloc("res", DataType.INT)
            +assign("res", DataType.INT) { expr{
                lVar(DataType.INT, "v0")
                op { DataType.Integer.Operators.add()}
                rVar(DataType.INT, "v1")
            }}

            +ifStmt(expr { lConst(DataType.BOOL, "true") }) {
                thenBody {
                    +alloc("ifVar0", DataType.FLOAT)
                    +allocA("ifVar1", 100, DataType.INT)
                }
                elseBody {
                    +alloc("ifVar2", DataType.FLOAT)
                    +allocA("ifVar3", 100, DataType.INT)
                }
            }
        }
    }

    println(p.toString())
}