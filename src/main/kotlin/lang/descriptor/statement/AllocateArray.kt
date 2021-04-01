package lang.descriptor.statement

import lang.analysis.Analyzable
import lang.analysis.Report
import lang.descriptor.Body
import lang.descriptor.DataType
import lang.descriptor.Statement

/**
 * Allocates a variable for an array.
 * [name] Name of the array
 * [len] Length of the array
 * [type] Type of the array
 * */
class AllocateArray(val name : String, val len : Int, val type : DataType) : Statement(Body()), Analyzable{
    /**
     * Analyze function
     * */
    override fun analyze(): Report {
        return Report("Alloc Array")
    }

    /**
     * String representation of the array
     * */
    override fun toString(): String {
        return "Variable Array: name=$name length=$len type=$type"
    }
}