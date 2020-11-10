package com.zerox.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author: zhuxi
 * @Time: 2020/11/10 14:11
 * @Description: 测试实体类
 * IType 为 入参类型，OType 为出参类型
 * @Modified By: zhuxi
 */
public class Tester<IType, OType> {

    /**
     * 测试案例
     *
     * @param <IType> 入参类型
     * @param <OType> 出参类型
     */
    class TestCase<IType, OType> {
        /**
         * 预设的入参案例
         */
        private IType input;
        /**
         * 预设的出参案例
         */
        private OType output;

        public TestCase(IType input, OType output) {
            this.input = input;
            this.output = output;
        }
    }

    /**
     * 预设的案例队列
     */
    private List<TestCase<IType, OType>> caseList = new LinkedList<>();

    /**
     * 调用的方法
     */
    private Function<IType, OType> function;

    public void addCase(IType input, OType output) {
        TestCase<IType, OType> testCase = new TestCase<>(input, output);
        caseList.add(testCase);
    }

    public Function<IType, OType> getSupplierFunction() {
        return function;
    }

    public void setFunction(Function<IType, OType> function) {
        this.function = function;
    }

    public String testAll() {
        int count = 0;
        if(function==null){
            return "请设置function（使用setFunction方法）";
        }
        //TODO: 考虑一下Streams的实现？
        for (TestCase<IType, OType> testCase : caseList) {
            OType output = function.apply(testCase.input);
            if (!(output.equals(testCase.output))) {
                return "case" + count + " 错误!\n" +
                        "输入为：" + testCase.input + " 理论输出为：" + testCase.output + "\n" +
                        "实际输出为：" + output + " 不符合理论输出，请检查";
            } else {
                System.out.println("case" + count + " 测试通过");
                count++;
            }
        }
        return "所有测试已通过";
    }
}
