package pattern.strategyPattern;

import org.assertj.core.util.Lists;

import java.util.List;

public class StrategyContext {

    private Strategy strategy;

    public StrategyContext(Strategy strategy){
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }

    public static void main(String[] args) {
        StrategyContext context = new StrategyContext(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new StrategyContext(new OperationSubtract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new StrategyContext(new OperationMultiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));

        System.out.println("----------");

        List<Strategy> strategyList = Lists.newArrayList(new OperationAdd(), new OperationSubtract(), new OperationMultiply());

        for (Strategy strategy : strategyList) {
            System.out.println("10/+/-/* 5 = " + strategy.doOperation(10, 5));
        }

    }
}



//优点： 1、算法可以自由切换。 2、避免使用多重条件判断。 3、扩展性良好。

//缺点： 1、策略类会增多。 2、所有策略类都需要对外暴露。

// 使用场景：1、如果在一个系统里面有许多类，它们之间的区别仅在于它们的行为，那么使用策略模式可以动态地让一个对象在许多行为中选择一种行为。
// 2、一个系统需要动态地在几种算法中选择一种。
// 3、如果一个对象有很多的行为，如果不用恰当的模式，这些行为就只好使用多重的条件选择语句来实现。