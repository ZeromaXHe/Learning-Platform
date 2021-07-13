import io.vavr.Lazy;
import io.vavr.Predicates;
import io.vavr.collection.CharSeq;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static io.vavr.API.$;
import static io.vavr.API.Match;
import static io.vavr.API.Case;

/**
 * @Author: zhuxi
 * @Time: 2021/7/13 15:02
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class ValuesTest {

    @Test
    public void values_OptionTest() {
        /// Option
        // In Optional, a call to .map that results in a null will result in an empty Optional.
        // In Vavr, it would result in a Some(null) that can then lead to a NullPointerException.
        Optional<String> maybeFoo = Optional.of("foo");
        Assert.assertEquals("foo", maybeFoo.get());
        Optional<String> maybeFooBar = maybeFoo.map(s -> (String) null).map(s -> s.toUpperCase() + "bar");
        Assert.assertFalse(maybeFooBar.isPresent());

        // This seems like Vavr’s implementation is broken, but in fact it’s not
        // - rather, it adheres to the requirement of a monad to maintain computational context when calling .map.
        // In terms of an Option, this means that calling .map on a Some will result in a Some,
        // and calling .map on a None will result in a None.
        // In the Java Optional example above, that context changed from a Some to a None.
        Option<String> maybeFoo2 = Option.of("foo");
        Assert.assertEquals("foo", maybeFoo2.get());
        try {
            maybeFoo2.map(s -> (String) null).map(s -> s.toUpperCase() + "bar");
            Assert.fail();
        } catch (NullPointerException e) {
            // this is clearly not the correct approach
            System.out.println("NPE");
        }

        Option<String> maybeFoo3 = Option.of("foo");
        Assert.assertEquals("foo", maybeFoo3.get());
        Option<String> maybeFooBar3 = maybeFoo3.map(s -> (String) null)
                .flatMap(s -> Option.of(s).map(t -> t.toUpperCase() + "bar"));
        Assert.assertTrue(maybeFooBar3.isEmpty());

        Option<String> maybeFoo4 = Option.of("foo");
        Assert.assertEquals("foo", maybeFoo4.get());
        Option<String> maybeFooBar4 = maybeFoo4.flatMap(s -> Option.of((String) null))
                .map(s -> s.toUpperCase() + "bar");
        Assert.assertTrue(maybeFooBar4.isEmpty());
    }

    @Test
    public void values_TryTest() {
        String other = "other";
        Try.of(this::bunchOfWork)
                .recover(x -> Match(x).of(
                        Case($(Predicates.instanceOf(IllegalStateException.class)),
                                this::somethingWithException),
                        Case($(Predicates.instanceOf(IllegalArgumentException.class)),
                                this::somethingWithException),
                        Case($(Predicates.instanceOf(RuntimeException.class)),
                                this::somethingWithException)
                ))
                .getOrElse(other);
    }

    private String somethingWithException(Exception t) {
        return null;
    }

    private String bunchOfWork() {
        return null;
    }

    @Test
    public void values_LazyTest() {
        Lazy<Double> lazy = Lazy.of(Math::random);
        // false
        System.out.println(lazy.isEvaluated());
        Double get1 = lazy.get();
        System.out.println(get1);
        // true
        System.out.println(lazy.isEvaluated());
        // memoized
        Double get2 = lazy.get();
        System.out.println(get2);
        Assert.assertEquals(get1, get2);
    }

    @Test
    public void values_EitherTest() {
        Either<String, Integer> value = compute().right().map(i -> i * 2).toEither();
        System.out.println(value);
    }

    private Either<String, Integer> compute() {
//        return Either.right(1);
        return Either.left("error");
    }

    @Test
    public void values_ValidationTest() {
        PersonValidator personValidator = new PersonValidator();
        Validation<Seq<String>, Person> valid = personValidator.validatePerson("John Doe", 30);
        Validation<Seq<String>, Person> invalid = personValidator.validatePerson("John? Doe!4", -4);
        // Valid(Person(John Doe, 30))
        System.out.println(valid);
        // Invalid(List(Name contains invalid characters: '!4?', Age must be at least 0))
        System.out.println(invalid);
    }

    class PersonValidator {

        private static final String VALID_NAME_CHARS = "[a-zA-Z ]";
        private static final int MIN_AGE = 0;

        public Validation<Seq<String>, Person> validatePerson(String name, int age) {
            return Validation.combine(validateName(name), validateAge(age)).ap(Person::new);
        }

        private Validation<String, String> validateName(String name) {
            return CharSeq.of(name).replaceAll(VALID_NAME_CHARS, "").transform(seq -> seq.isEmpty()
                    ? Validation.valid(name)
                    : Validation.invalid("Name contains invalid characters: '"
                    + seq.distinct().sorted() + "'"));
        }

        private Validation<String, Integer> validateAge(int age) {
            return age < MIN_AGE
                    ? Validation.invalid("Age must be at least " + MIN_AGE)
                    : Validation.valid(age);
        }

    }

    class Person {

        public final String name;
        public final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person(" + name + ", " + age + ")";
        }

    }

}
