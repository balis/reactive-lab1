
class Cat(val name: String)
class Dog(val name: String)

trait Hello {
  def sayHello(): Unit
}

class HelloCat(name: String) extends Cat(name) with Hello {
  def sayHello() = println(s"$name, Miau, Hello!")
}

class HelloDog(name: String) extends Dog(name) with Hello {
  def sayHello() = println(s"$name, Bark, Hello!")
}

def sayHello(h: Hello): Unit = {
  h.sayHello()
}

sayHello(new HelloCat("Bonifacy"))
sayHello(new HelloDog("Reksio"))

trait HelloT[A] {
  def sayHello(a: A): Unit
}

def sayHelloT[A](a: A, helloT: HelloT[A]): Unit = {
  helloT.sayHello(a)
}

implicit object CatHelloT extends HelloT[Cat] {
  def sayHello(cat: Cat) = println(s"${cat.name}, Miau, Hello!")
}

sayHelloT(new Cat("Bonifacy"), CatHelloT)

def sayHelloTImplicit[A](a: A)(implicit hello: HelloT[A]) = {
  hello.sayHello(a)
}

sayHelloTImplicit(new Cat("Bonifacy"))

def sayHelloTImplictB[A: HelloT](a: A): Unit = {
  implicitly[HelloT[A]].sayHello(a)
}

implicit object DogHelloT extends HelloT[Dog] {
  def sayHello(dog: Dog) =  println(s"${dog.name}, Bark, Hello!")
}

sayHelloTImplictB(new Dog("Reksio"))
