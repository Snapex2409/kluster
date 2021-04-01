package lang.descriptor.statement

import lang.analysis.Analyzable
import lang.analysis.Report
import lang.descriptor.Body
import lang.descriptor.DataType
import lang.descriptor.Statement

/**
 * Creates a simple variable.
 * [name] Name of the variable
 * [type] Type of the variable
 * */
class Allocate (val name : String, val type : DataType) : Statement(Body()), Analyzable {

    /**
     * Analyze function
     * */
    override fun analyze() : Report {
        return Report("Alloc")
    }

    /**
     * String representation of the variable
     * */
    override fun toString(): String {
        return "Variable: name=$name type=$type"
    }
}