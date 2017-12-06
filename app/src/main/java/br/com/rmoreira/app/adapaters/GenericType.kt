package br.com.rmoreira.app.adapaters

/**
 * Created by robsonmoreira on 03/12/17.
 */
enum class GenericType(private var mAbbreviation: String) {

    LOADING("L"),
    USER_DATA("U");

    fun getAbbreviation(): String {
        return mAbbreviation
    }

}