package io.github.dbstarll.utils.lang.line;

/**
 * 带返回操作结果的行处理器接口.
 *
 * @param <E> 行处理返回的类型
 * @author dbstar
 */
public interface LineOperator<E> {
    /**
     * 处理一行数据，并返回结果.
     *
     * @param line 待处理的行
     * @return 处理结果
     */
    E operate(String line);
}
