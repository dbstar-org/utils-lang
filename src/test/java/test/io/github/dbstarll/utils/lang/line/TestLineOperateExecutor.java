package test.io.github.dbstarll.utils.lang.line;

import io.github.dbstarll.utils.lang.line.AbstractLineOperateExecutor;
import io.github.dbstarll.utils.lang.line.LineOperateExecutor;
import io.github.dbstarll.utils.lang.test.AbstractLineOperateExecutorTestCase;
import io.github.dbstarll.utils.lang.test.LineType;
import io.github.dbstarll.utils.lang.test.LineTypeOperator;

public class TestLineOperateExecutor extends AbstractLineOperateExecutorTestCase {
    @Override
    protected AbstractLineOperateExecutor<LineType> buildLineOperateExecutor(
            LineTypeOperator operator) {
        return LineOperateExecutor.build(operator);
    }
}
