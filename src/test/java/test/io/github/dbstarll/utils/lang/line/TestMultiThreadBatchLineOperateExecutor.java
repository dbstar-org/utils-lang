package test.io.github.dbstarll.utils.lang.line;

import io.github.dbstarll.utils.lang.line.AbstractLineOperateExecutor;
import io.github.dbstarll.utils.lang.line.MultiThreadBatchLineOperateExecutor;
import io.github.dbstarll.utils.lang.test.LineType;
import io.github.dbstarll.utils.lang.test.LineTypeOperator;

public class TestMultiThreadBatchLineOperateExecutor extends TestBatchLineOperateExecutor {
    @Override
    protected AbstractLineOperateExecutor<LineType> buildLineOperateExecutor(
            LineTypeOperator operator) {
        return MultiThreadBatchLineOperateExecutor.build(operator, 5, 2, 20);
    }
}
