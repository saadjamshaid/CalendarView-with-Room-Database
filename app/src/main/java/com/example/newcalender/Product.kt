package com.example.newcalender

class Product{

    var id: Int = 0
    var date: String? = null
    var event: String? = null

    constructor(currentdate: String, currentevent: String)
    {
        this.date = currentdate
        this.event = currentevent
        
    }


}