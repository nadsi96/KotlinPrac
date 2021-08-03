data class Person(var name: String){
    var age: Int = 0
    var school: Age? = null

    fun showInfo(){
        println("name = $name, age = $age, school = $school")
    }

    fun changAge(f: (Int) -> Int){
        age = f(age)
    }

}

data class Data(val a: Int, val b: Int)
