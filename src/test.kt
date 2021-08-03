fun main(){
    println("hello")

    val seperater = { -> Unit
        println("===================")
    }

    var lst: MutableList<Int> = mutableListOf(1,2,3)
    lst.add(4)


    // 배열의 인덱스 i에 위치한 항목
    val callItem = {i: Int, lst: MutableList<Int> -> Int
        for(item in lst){
            print("$item ")
        }
        println()
        lst[i]
    }

    println(callItem.invoke(0, lst))
    println(callItem(0, lst))

    seperater()

    // lambda 형식 사용해서 배열에 있는 값 더하기
    val itemPlus = {a: Int, b: Int -> Int
        println("a = $a, b = $b")
        a + b
    }
    // '' 곱하기
    val itemMul = {a: Int, b: Int -> Int
        println("a = $a, b = $b")
        a * b
    }

//    println("result = ${testFunc(showItems)}")

    println("람다식 사용해서 배열에 있는 값 더하기")
    println("itemPlus")
    testFunc(itemPlus)
    val lst2 = listOf(1,2,3,4,5)
//    println("itemPlus2")
//    println(itemPlus2(0, lst2))
//    println("itemPlus3")
//    println(itemPlus3(lst2.size-1, lst2))
    println("itemPlus4") // fold 없이
    println(itemPlus4(0, lst2))
    seperater()

    println("람다식 사용해서 배열에 있는 값 곱")
    println(lst)
    println(testFunc2(lst, itemMul))

    seperater()

    println("같은 동작, 빈 배열인 경우")
    val emptyLst = listOf<Int>()
    println(emptyLst.fold(0, {a: Int, b: Int -> a + b}))
    println(emptyLst.fold(10) { a: Int, b: Int -> a + b })

    seperater()

    // data class와 scope 함수
    var dd = Person("DDD").apply{
        age = 4
    }
    var people = listOf(Person("AAA"),
                        Person("BBB"),
                        Person("CCC"))
    for(i in 1 .. 3){
        people[i-1].apply{
            age = i
        }
    }
    for(person in people){
        person.showInfo()
    }
    seperater()

    for(person in people){
        person.run{
            name = name.toLowerCase()
            person.showInfo()
        }
    }

    seperater()

    val changeAge1 = {age: Int -> Int
        age + 10
    }
    val anotherPeople = listOf<Person>(Person(""),
        Person(""),
        Person(""))

    // 같은 크기의 배열 묶어서
    for((p1, p2) in people.zip(anotherPeople)){
        with(p1){
            p2.name = "!${name}A"
            p2.age = age
            p2.changAge(changeAge1)
        }
        p1.showInfo()
        p2.showInfo()
    }

    seperater()

    val person1 = Person("111").apply{
        age = 111
    }
    println(person1)
    val person1_1 = Person("111")
    person1_1.apply{
        name="333"
    }
    val person2 = Person("")
    person2.run{
        name = "222"
    }
    val person2_1 = Person("").run{
        name = "222"
        // person2_1 == Unit
    }

    val person3 = person1.also{
        it.name = "333"
        // person1 객체 반환
        // person1 === person3
    }
    var person4 = null
    val person5 = person4.let{
        // Unit 반환
    }
    val person5_1 = person4.also{
        // null 반환
    }
    val person6 = person1.let{
//        it.name = "444"
        // 값 반환되는 게 없다면 Unit 반환
        Person(it.name)
//        1
    }

    println("person1 = $person1")
    println("person1_1 = $person1_1")
    println("person2 = $person2")
    println("person2_1 = $person2_1")
    println("person3 = $person3")
    println("person1 === person3 ${person1 === person3}")
    println("person4 = $person4")
    println("person5 = $person5")
    println(person5_1)
    println(person6)
    println(person4===person5)
    println(person1===person6)

    seperater()
    for((p, s) in people.zip(Age.values())){
        p.run{
            p.school = s
            with(s){
                p.age = s.age
            }
            p.showInfo()
        }
    }

    seperater()
//    val pName = person1.component1()
    val helloName = {name: String -> println("hello $name")}
    println(person1)
    helloName(person1.component1())

    val data1 = Data(1, 2)
    val (data1_a, data1_b) = data1
    val showData = {a: Int, b: Int ->
        println("$a, $b")
    }
//    showData(data1)
}

fun testFunc(f: (Int, Int) -> Int){
    val lst2 = listOf(1, 2, 3, 4, 5)
    println(lst2.fold(0, f))
    println(lst2)
}
fun testFunc2(lst: List<Int>, f: (Int, Int) -> Int) =
    lst.fold(lst[0], f)


fun itemPlus2(idx: Int, lst: List<Int>): Int {
    if(idx < lst.size){
        val a = itemPlus2(idx+1, lst)
        val b = lst[idx]
        println("a = $a, b = $b")
        return a+b
    }else{
        return 0
    }
}
fun itemPlus3(idx: Int, lst: List<Int>): Int{
    if(idx == 0){
        return lst[0]
    }else{
        val a = itemPlus3(idx-1, lst)
        val b = lst[idx]
        println("a = $a, b = $b")
        return a + b
    }
}

fun itemPlus4(init: Int, lst: List<Int>): Int{
    val lam = {a: Int, b: Int -> Int
        a + b
    }
    var result = init

    for(item in lst){
        println("a = $result, b = $item")
        result = lam(result, item)
    }
    return result
}
