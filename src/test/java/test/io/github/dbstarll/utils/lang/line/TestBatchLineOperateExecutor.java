package test.io.github.dbstarll.utils.lang.line;

import io.github.dbstarll.utils.lang.line.AbstractLineOperateExecutor;
import io.github.dbstarll.utils.lang.line.BatchLineOperateExecutor;
import io.github.dbstarll.utils.lang.test.AbstractLineOperateExecutorTestCase;
import io.github.dbstarll.utils.lang.test.LineType;
import io.github.dbstarll.utils.lang.test.LineTypeOperator;

public class TestBatchLineOperateExecutor extends AbstractLineOperateExecutorTestCase {
    @Override
    protected AbstractLineOperateExecutor<LineType> buildLineOperateExecutor(
            LineTypeOperator operator) {
        return BatchLineOperateExecutor.build(operator, 7);
    }
}
