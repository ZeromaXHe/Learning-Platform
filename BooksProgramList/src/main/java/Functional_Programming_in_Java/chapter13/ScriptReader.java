package Functional_Programming_in_Java.chapter13;

import Functional_Programming_in_Java.chapter2.Tuple;
import Functional_Programming_in_Java.chapter5.List;
import Functional_Programming_in_Java.chapter7.Result;

/**
 * @Author: zhuxi
 * @Time: 2021/7/14 18:21
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class ScriptReader implements Input {
    private final List<String> commands;

    public ScriptReader(List<String> commands) {
        super();
        this.commands = commands;
    }

    public ScriptReader(String... commands) {
        super();
        this.commands = List.list(commands);
    }

    @Override
    public Result<Tuple<String, Input>> readString() {
        return commands.isEmpty() ?
                Result.failure("Not enough entries in script") :
                Result.success(new Tuple<>(commands.headOption().getOrElse(""), new ScriptReader(commands.drop(1))));
    }

    @Override
    public Result<Tuple<Integer, Input>> readInt() {
        try {
            return commands.isEmpty() ?
                    Result.failure("Not enough entries in script") :
                    Integer.parseInt(commands.headOption().getOrElse("")) >= 0 ?
                            Result.success(new Tuple<>(
                                    Integer.parseInt(commands.headOption().getOrElse("")),
                                    new ScriptReader(commands.drop(1)))) :
                            Result.empty();
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
