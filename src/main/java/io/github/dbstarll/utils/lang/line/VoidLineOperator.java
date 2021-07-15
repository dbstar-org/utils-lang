package io.github.dbstarll.utils.lang.line;

/**
 * 无返回处理结果的行处理器接口.
 *
 * @author dbstar
 */
public abstract class VoidLineOperator implements LineOperator<String> {
    private static final String OPERATE_RESULT = "COUNT";

    @Override
    public final String operate(String line) {
        operateWithoutReturn(line);
        return OPERATE_RESULT;
    }

    protected abstract void operateWithoutReturn(String line);
}
