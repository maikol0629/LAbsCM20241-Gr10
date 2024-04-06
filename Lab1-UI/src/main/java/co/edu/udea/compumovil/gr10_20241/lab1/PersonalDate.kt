package co.edu.udea.compumovil.gr10_20241.lab1

class PersonalDate {
    var name:String=""
    var lastName: String =""
    var sex: String=""
    var education: String =""
    var birthdatDate: String = ""
}
fun PersonalDate.hasNullOrEmptyFields(): Boolean {
    return name.isNullOrEmpty() || lastName.isNullOrEmpty() ||
             birthdatDate.isNullOrEmpty()
}