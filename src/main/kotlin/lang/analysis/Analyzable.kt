package lang.analysis

/**
 * Analyses the component.
 * */
interface Analyzable {

    /**
     * Analyze function
     * */
    fun analyze() : Report
}