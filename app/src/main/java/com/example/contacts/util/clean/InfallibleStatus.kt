package com.example.contacts.util.clean

/**
 * Manage the status of the execution.
 * This can be [Loading] when the execution is in process and [Done] when the execution
 * concluded successfully.
 *
 * @see Loading
 * @see Done
 */
sealed class InfallibleStatus<R> {

    /**
     * Data type created when the execution is in process.
     */
    class Loading<R> : InfallibleStatus<R>()

    /**
     * Data type created when the execution was successfully.
     */
    data class Done<R>(val value: R) : InfallibleStatus<R>()

}