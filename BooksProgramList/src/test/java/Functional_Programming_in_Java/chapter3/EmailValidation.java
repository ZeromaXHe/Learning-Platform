package Functional_Programming_in_Java.chapter3;

import Functional_Programming_in_Java.chapter2.Function;

import java.util.regex.Pattern;

/**
 * @Author: zhuxi
 * @Time: 2021/7/5 13:58
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class EmailValidation {
    static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    static Effect<String> success = s -> System.out.println("Mail sent to " + s);

    static Effect<String> failure = s -> System.err.println("Error message logged:" + s);

    static Function<String, Result<String>> emailChecker = s -> Case.match(
            Case.mcase(() -> new Result.Success<>(s)),
            Case.mcase(() -> s == null, () -> new Result.Failure<>("email must not be null")),
            Case.mcase(() -> s.length() == 0, () -> new Result.Failure<>("email must not be empty")),
            Case.mcase(() -> !emailPattern.matcher(s).matches(), () -> new Result.Failure<>("email " + s + " is invalid."))
    );

    public static void main(String... args) {
        emailChecker.apply("this.is@my.email").bind(success, failure);
        emailChecker.apply(null).bind(success, failure);
        emailChecker.apply("").bind(success, failure);
        emailChecker.apply("john.doe@acme.com").bind(success, failure);
    }
}