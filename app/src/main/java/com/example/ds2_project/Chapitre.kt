package com.example.ds2_project

class Chapitre {

    val CHAPITRE1 = 1
    val CHAPITRE2 = 2
    val CHAPITRE3 = 3
    public  var id:Int=0

    public var name:String

    constructor(){
        id=0
        name=""
    }
    constructor(name: String){
        this.name=name
    }

    override fun toString(): String {
        return name
    }
}