package io.github.dbstarll.utils.lang.line;

/**
 * 带返回操作结果的行批处理器接口.
 *
 * @param <E> 行处理返回的类型
 * @author dbstar
 */
public interface BatchLineOperator<E> {
    /**
     * 批量处理多行数据，并返回结果.
     *
     * @param lines 待处理的多行
     * @return 处理结果数组，每个元素对应一行的处理结果
     */
    E[] operate(String... lines);
}
