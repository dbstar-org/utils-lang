package test.io.github.dbstarll.utils.lang.line;

import io.github.dbstarll.utils.lang.line.AbstractLineOperateExecutor;
import io.github.dbstarll.utils.lang.line.MultiThreadLineOperateExecutor;
import io.github.dbstarll.utils.lang.test.LineType;
import io.github.dbstarll.utils.lang.test.LineTypeOperator;

public class TestMultiThreadLineOperateExecutor extends TestLineOperateExecutor {
    @Override
    protected AbstractLineOperateExecutor<LineType> buildLineOperateExecutor(
            LineTypeOperator operator) {
        return MultiThreadLineOperateExecutor.build(operator, 4, 50);
    }
}
