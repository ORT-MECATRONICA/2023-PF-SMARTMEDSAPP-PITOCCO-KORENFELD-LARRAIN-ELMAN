package com.smartmeds.smartmeds.fragments

data class Horarios(var pastilla:String,
                    var horario:String,
                    var lunes:Boolean,
                    var martes:Boolean,
                    var miercoles:Boolean,
                    var jueves:Boolean,
                    var viernes:Boolean,
                    var sabado:Boolean,
                    var domingo:Boolean
                    ) {

}