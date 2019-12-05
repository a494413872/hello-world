package dynamicProxy.statictype;

public class PetProxy implements Pet {

    private Pet pet;

    PetProxy(){
        pet = new Cat();
    }
    @Override
    public void eat() {
            pet.eat();
    }

    @Override
    public void run() {
        pet.run();
    }
}
