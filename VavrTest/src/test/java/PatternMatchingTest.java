import io.vavr.Tuple;
import io.vavr.Tuple1;
import io.vavr.control.Option;
import io.vavr.match.annotation.Patterns;
import io.vavr.match.annotation.Unapply;
import org.junit.Test;

import static io.vavr.API.*;
import static io.vavr.Patterns.$None;
import static io.vavr.Patterns.$Some;
import static io.vavr.Predicates.*;

/**
 * @Author: zhuxi
 * @Time: 2021/7/13 15:45
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class PatternMatchingTest {

    @Test
    public void patternMatching_BasicTest() {
        int i = 1;
        String s = Match(i).of(
                Case($(1), "one"),
                Case($(2), "two"),
                Case($(), "?")
        );
        System.out.println(s);

        /// Exhaustiveness
        Option<String> s2 = Match(i).option(
                Case($(0), "zero")
        );
        System.out.println(s2);

        /// Syntactic Sugar
        String s3 = Match(i).of(
                Case($(is(1)), "one"),
                // We use the isIn predicate to check multiple conditions:
                Case($(isIn(2, 3, 4)), "two"),
                Case($(), "?")
        );

        /// Performing Side-Effects
        String arg = "-h";
        Match(arg).of(
                // run is used to get around ambiguities and because void isn’t a valid return value in Java.
                // [Wrong use]: Case($(isIn("-h", "--help")), run(this::displayHelp))
                // As we can see, run is error prone if not used right. Be careful.
                // We consider deprecating it in a future release
                // and maybe we will also provide a better API for performing side-effects.
                Case($(isIn("-h", "--help")), o -> run(this::displayHelp)),
                Case($(isIn("-v", "--version")), o -> run(this::displayVersion)),
                Case($(), o -> run(() -> {
                    throw new IllegalArgumentException(arg);
                }))
        );

        Object obj = 1;
        Number plusOne = Match(obj).of(
                Case($(instanceOf(Integer.class)), i2 -> i2 + 1),
                Case($(instanceOf(Double.class)), d -> d + 1),
                Case($(), o -> {
                    throw new NumberFormatException();
                })
        );
    }

    private void displayVersion() {
        System.out.println("Version");
    }

    private void displayHelp() {
        System.out.println("Help");
    }

    @Test
    public void patternMatching_PatternsTest() {
        Option option = Option.of("haha");
        Match(option).of(
                Case($Some($()), "defined"),
                Case($None(), "empty")
        );
    }

}
