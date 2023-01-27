package io.github.dbstarll.utils.lang.test;

import io.github.dbstarll.utils.lang.StandardCharsets;
import io.github.dbstarll.utils.lang.line.AbstractLineOperateExecutor;
import io.github.dbstarll.utils.lang.line.BatchLineOperateExecutor;
import io.github.dbstarll.utils.lang.line.Lines;
import junit.framework.TestCase;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map.Entry;

public abstract class AbstractLineOperateExecutorTestCase extends TestCase {
    private static final LineTypeOperator LINE_TYPE_OPERATOR = new LineTypeOperator();

    private AbstractLineOperateExecutor<LineType> executor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.executor = buildLineOperateExecutor(LINE_TYPE_OPERATOR);
    }

    @Override
    protected void tearDown() throws Exception {
        this.executor = null;
        super.tearDown();
    }

    protected abstract AbstractLineOperateExecutor<LineType> buildLineOperateExecutor(
            LineTypeOperator operator);

    /**
     * 测试 {@link AbstractLineOperateExecutor#operate(Iterable)}.
     *
     * @throws Exception Exception
     */
    public void testOperate() throws Exception {
        Collection<String> subs = new LinkedList<>();
        for (String line : Lines.open(AbstractLineOperateExecutorTestCase.class, "/lines.txt",
                StandardCharsets.UTF_8, null)) {
            subs.add(line);
        }

        Collection<String> lines = new LinkedList<>();
        lines.add("exception");
        lines.add("exception");
        lines.add("exception");
        for (int i = 0; i < 10010; i++) {
            lines.addAll(subs);
        }
        lines.add("exception");
        lines.add("exception");

        executor.operate(lines);

        if (!(executor instanceof BatchLineOperateExecutor)) {
            for (Entry<LineType, Long> entry : executor) {
                switch (entry.getKey()) {
                    case line:
                        assertEquals(30030, entry.getValue().longValue());
                        break;
                    case blank:
                        assertEquals(20020, entry.getValue().longValue());
                        break;
                    case comment:
                        assertEquals(10010, entry.getValue().longValue());
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
