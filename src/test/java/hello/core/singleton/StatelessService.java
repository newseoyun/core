package hello.core.singleton;

public class StatelessService {

    // 상태유지 안함

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }

}
