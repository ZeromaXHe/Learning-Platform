package Functional_Programming_in_Java.chapter6;

import Functional_Programming_in_Java.chapter7.Result;

import java.io.IOException;

/**
 * @Author: zhuxi
 * @Time: 2021/7/6 17:18
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String, Toon> toons = new Map<String, Toon>()
                .put("Mickey", new Toon("Mickey", "Mouse", "mickey@disney.com"))
                .put("Minnie", new Toon("Minnie", "Mouse"))
                .put("Donald", new Toon("Donald", "Duck", "donald@disney.com"));

//        Option<String> mickey = toons.get("Mickey").flatMap(Toon::getEmail);
//        Option<String> minnie = toons.get("Minnie").flatMap(Toon::getEmail);
//        Option<String> goofy = toons.get("Goofy").flatMap(Toon::getEmail);
//
//        System.out.println(mickey.getOrElse(() -> "No data"));
//        System.out.println(minnie.getOrElse(() -> "No data"));
//        System.out.println(goofy.getOrElse(() -> "No data"));

        Result<String> result = getName().flatMap(toons::get).flatMap(Toon::getEmail);
        System.out.println(result);
    }

    /**
     * getName方法模拟了一个输入，可能导致一个Failure
     *
     * @return
     */
    public static Result<String> getName() {
//        return Result.success("Mickey");
//        return Result.failure(new IOException("Input error"));
        return Result.success("Minnie");
//        return Result.success("Goofy");
    }

    static class Toon {
        private final String firstName;
        private final String lastName;
        //        private final Option<String> email;
        private final Result<String> email;

        public Toon(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
//            this.email = Option.none();
            this.email = Result.empty();
        }

        public Toon(String firstName, String lastName, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
//            this.email = Option.some(email);
            this.email = Result.success(email);
        }

//        public Option<String> getEmail() {
//            return email;
//        }

        public Result<String> getEmail() {
            return email;
        }
    }
}
